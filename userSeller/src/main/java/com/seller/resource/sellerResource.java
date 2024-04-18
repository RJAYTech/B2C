package com.seller.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.seller.dto.CommonApiResponse;
import com.seller.dto.RegistersellerRequestDto;
import com.seller.dto.UserResponseDto;
import com.seller.dto.UserSellerDto;
import com.seller.enity.Address;
import com.seller.enity.UserSeller;
import com.seller.enity.product;
import com.seller.exception.UserSaveFailedException;
import com.seller.service.AddressService;
import com.seller.service.ProductService;
//import com.seller.service.ProductService;
import com.seller.service.sellerService;
import com.seller.untity.Constants.ProductStatus;
import com.seller.untity.Constants.UserRole;
import com.seller.untity.Constants.UserStatus;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class sellerResource {

	private final Logger LOG = LoggerFactory.getLogger(sellerResource.class);

	@Autowired
	private sellerService userService;

	@Autowired
	private AddressService addressService;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@Autowired
	private ProductService productService;

	public ResponseEntity<CommonApiResponse> registerUser(RegistersellerRequestDto request) {

		LOG.info("Received request for register user");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("user is null");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		UserSeller existingUser = userService.getUserByEmailAndStatus(request.getEmailId(), UserStatus.ACTIVE.value());

		if (existingUser != null) {
			response.setResponseMessage("User with this Email Id already resgistered!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getRole() == null) {
			response.setResponseMessage("bad request ,Role is missing");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		UserSeller user = RegistersellerRequestDto.toUserEntity(request);

		// String encodedPassword = passwordEncoder.encode(user.getPassword());

		user.setStatus(UserStatus.ACTIVE.value());
		// user.setPassword(encodedPassword);

		// delivery person is for seller, so we need to set Seller
		if (user.getRole().equals(UserRole.ROLE_DELIVERY.value())) {

			UserSeller seller = userService.getUserById(request.getSellerId());

			if (seller == null) {
				response.setResponseMessage("Seller not found,");
				response.setSuccess(false);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			user.setSeller(seller);

		}

		Address address = new Address();
		address.setCity(request.getCity());
		address.setPincode(request.getPincode());
		address.setStreet(request.getStreet());

		Address savedAddress = this.addressService.addAddress(address);

		if (savedAddress == null) {
			throw new UserSaveFailedException("Registration Failed because of Technical issue:(");
		}

		user.setAddress(savedAddress);
		existingUser = userService.addUser(user);

		if (existingUser == null) {
			throw new UserSaveFailedException("Registration Failed because of Technical issue:(");
		}
		response.setResponseMessage("User registered Successfully");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> deleteSeller(int sellerId) {

		UserResponseDto response = new UserResponseDto();

		if (sellerId == 0) {
			response.setResponseMessage("missing seller id");
			response.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		UserSeller seller = this.userService.getUserById(sellerId);

		if (seller == null) {
			response.setResponseMessage("Seller not found");
			response.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<UserSeller> deliveryPersons = new ArrayList<>();

		List<product> products = new ArrayList<>();

		deliveryPersons = this.userService.getUserBySellerAndRoleAndStatusIn(seller, UserRole.ROLE_DELIVERY.value(),
				Arrays.asList(UserStatus.ACTIVE.value()));

		products = this.productService.getAllProductBySellerAndStatusIn(seller,
				Arrays.asList(ProductStatus.ACTIVE.value()));

		seller.setStatus(UserStatus.DEACTIVATED.value());
		UserSeller deletedSeller = this.userService.updateUser(seller);

		// deactivating the seller
		if (deletedSeller == null) {
			throw new UserSaveFailedException("Failed to deactivate the seller!!!");
		}

		// deactivating the all seller, delivery persons
		if (!deliveryPersons.isEmpty())

		{

			for (UserSeller deliveryPerson : deliveryPersons) {
				deliveryPerson.setStatus(UserStatus.DEACTIVATED.value());
			}

			List<UserSeller> deletedDeliveryPerons = this.userService.updateAllUser(deliveryPersons);

			if (CollectionUtils.isEmpty(deletedDeliveryPerons)) {
				throw new UserSaveFailedException("Failed to deactivate the seller!!!");
			}

		}

		// deactivating all the listed products by seller

		if (!products.isEmpty()) {

			for (product product : products) {
				product.setStatus(ProductStatus.DEACTIVATED.value());
			}

			List<product> deletedProducts = this.productService.updateAllProduct(products);

			if (CollectionUtils.isEmpty(deletedProducts)) {
				throw new UserSaveFailedException("Failed to deactivate the seller!!!");
			}

		}

		response.setResponseMessage("Seller Deactivated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<UserResponseDto> getUsersByRole(String role) {

		UserResponseDto response = new UserResponseDto();

		if (role == null) {
			response.setResponseMessage("missing role");
			response.setSuccess(false);
			return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<UserSeller> users = new ArrayList<>();

		users = this.userService.getUserByRoleAndStatus(role, UserStatus.ACTIVE.value());

		if (users.isEmpty()) {
			response.setResponseMessage("No Users Found");
			response.setSuccess(false);
		}

		List<UserSellerDto> userDtos = new ArrayList<>();

		for (UserSeller user : users) {

			UserSellerDto dto = UserSellerDto.toUserDtoEntity(user);

			if (role.equals(UserRole.ROLE_DELIVERY.value())) {

				UserSellerDto sellerDto = UserSellerDto.toUserDtoEntity(user.getSeller());
				dto.setSeller(sellerDto);

			}

			userDtos.add(dto);

		}

		response.setUsers(userDtos);
		response.setResponseMessage("User Fetched Successfully");
		response.setSuccess(true);

		return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
	}

}
