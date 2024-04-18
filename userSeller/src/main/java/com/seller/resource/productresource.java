package com.seller.resource;

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
import com.seller.dto.ProductAddRequest;
import com.seller.dto.ProductDetailUpdateRequest;
import com.seller.dto.ProductResponseDto;
import com.seller.enity.UserSeller;
import com.seller.enity.product;
import com.seller.exception.ProductSaveFailedException;

import com.seller.service.ProductService;
import com.seller.service.StorageService;
import com.seller.service.sellerService;
import com.seller.untity.Constants.ProductStatus;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class productresource {

	private final Logger LOG = LoggerFactory.getLogger(productresource.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private sellerService userService;

	@Autowired
	private StorageService storageService;

	public ResponseEntity<CommonApiResponse> addProduct(ProductAddRequest productDto) {

		LOG.info("request received for Product add");

		CommonApiResponse response = new CommonApiResponse();

		if (productDto == null || productDto.getSellerId() == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		product product = ProductAddRequest.toEntity(productDto);
		product.setStatus(ProductStatus.ACTIVE.value());

		UserSeller seller = this.userService.getUserById(productDto.getSellerId());

		if (seller == null) {
			response.setResponseMessage("Seller not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		product.setSeller(seller);

		// store product image in Image Folder and give name to store in database
		String productImageName1 = storageService.store(productDto.getImage1());
		String productImageName2 = storageService.store(productDto.getImage2());
		String productImageName3 = storageService.store(productDto.getImage3());

		product.setImage1(productImageName1);
		product.setImage2(productImageName2);
		product.setImage3(productImageName3);

		product savedProduct = this.productService.addProduct(product);

		if (savedProduct == null) {
			throw new ProductSaveFailedException("Failed to save the Product");
		}

		response.setResponseMessage("Product added successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<CommonApiResponse> updateProductDetail(ProductDetailUpdateRequest request) {

		LOG.info("request received for update product");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		product product = this.productService.getProductById(request.getId());

		if (product == null) {
			response.setResponseMessage("product not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		product.setDescription(request.getDescription());
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		product.setQuantity(request.getQuantity());

		product updatedProduct = this.productService.updateProduct(product);

		if (updatedProduct == null) {
			throw new ProductSaveFailedException("Failed to update the Product details");
		}

		response.setResponseMessage("Product added successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	/* Delete the prodect */
	public ResponseEntity<CommonApiResponse> deleteProduct(int productId, int sellerId) {

		LOG.info("request received for deleting the product");

		CommonApiResponse response = new CommonApiResponse();

		if (productId == 0 || sellerId == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		product product = this.productService.getProductById(productId);

		if (product == null) {
			response.setResponseMessage("product not found, failed to delete the product");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (product.getSeller().getId() != sellerId) {
			response.setResponseMessage("Product not owned by Seller, Can't Delete");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		product.setStatus(ProductStatus.DEACTIVATED.value());

		product deletedProduct = this.productService.updateProduct(product);

		if (deletedProduct == null) {
			throw new ProductSaveFailedException("Failed to delete the Product");
		}

		response.setResponseMessage("Product Deleted Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}
	public ResponseEntity<CommonApiResponse> updateProductImage(ProductAddRequest request) {

		LOG.info("request received for update product images");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getId() == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getImage1() == null || request.getImage2() == null || request.getImage3() == null) {
			response.setResponseMessage("Image not selected");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		product product = this.productService.getProductById(request.getId());

		String existingImage1 = product.getImage1();
		String existingImage2 = product.getImage2();
		String existingImage3 = product.getImage3();

		// store updated product image in Image Folder and give name to store in
		// database
		String productImageName1 = storageService.store(request.getImage1());
		String productImageName2 = storageService.store(request.getImage2());
		String productImageName3 = storageService.store(request.getImage3());

		product.setImage1(productImageName1);
		product.setImage2(productImageName2);
		product.setImage3(productImageName3);

		product updatedProduct = this.productService.addProduct(product);

		if (updatedProduct == null) {
			throw new ProductSaveFailedException("Failed to update the Product image");
		}

		// deleting the existing image from the folder
		try {
			this.storageService.delete(existingImage1);
			this.storageService.delete(existingImage2);
			this.storageService.delete(existingImage3);

		} catch (Exception e) {
			LOG.error("Exception Caught: " + e.getMessage());

			throw new ProductSaveFailedException("Failed to update the Product image");
		}

		response.setResponseMessage("Product Image Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}
	public ResponseEntity<ProductResponseDto> fetchProductById(int productId) {

		LOG.info("request received for searching the seller products");

		ProductResponseDto response = new ProductResponseDto();

		if (productId == 0) {
			response.setResponseMessage("missing product id");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		product product = this.productService.getProductById(productId);

		if (product == null) {
			response.setResponseMessage("Product not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		response.setProducts(Arrays.asList(product));
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}
	public ResponseEntity<ProductResponseDto> fetchAllProducts() {

		LOG.info("request received for fetching all the products");

		ProductResponseDto response = new ProductResponseDto();

		List<product> products = this.productService
				.getAllProductByStatusIn(Arrays.asList(ProductStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(products)) {
			response.setResponseMessage("No products found");
			
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

		response.setProducts(products);
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}
	public ResponseEntity<ProductResponseDto> searchProductByName(String productName) {

		LOG.info("request received for searching the products");

		ProductResponseDto response = new ProductResponseDto();

		if (productName == null) {
			response.setResponseMessage("missing input, product name");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<product> products = this.productService.searchProductNameAndStatusIn(productName,
				Arrays.asList(ProductStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(products)) {
			response.setResponseMessage("No products found");
			response.setSuccess(false);

			return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
		}

		response.setProducts(products);
		response.setResponseMessage("Product fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductResponseDto>(response, HttpStatus.OK);
	}

}
