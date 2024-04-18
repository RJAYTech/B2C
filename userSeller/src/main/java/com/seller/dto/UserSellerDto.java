package com.seller.dto;

//import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;

import com.seller.enity.Address;
import com.seller.enity.UserSeller;

import lombok.Data;

@Data
public class UserSellerDto {

	private int id;

	private String firstName;

	private String lastName;

	private String emailId;

	private String phoneNo;

	private String role;

	private Address address;

	private UserSellerDto seller;

	private String status;

	public static UserSellerDto toUserDtoEntity(UserSeller user) {
		UserSellerDto userSellerDto = new UserSellerDto();
		BeanUtils.copyProperties(user, userSellerDto, "seller");
		return userSellerDto;
	}

}
