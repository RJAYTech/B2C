package com.seller.dto;

import org.springframework.beans.BeanUtils;

import com.seller.enity.UserSeller;

import lombok.Data;

@Data
public class RegistersellerRequestDto {

	private String firstName;

	private String lastName;

	private String emailId;

	private String password;

	private String phoneNo;

	private String role;

	private String street;

	private String city;

	private int pincode;

	private int sellerId; // seller id for delivery person

	private String status;

	public static UserSeller toUserEntity(RegistersellerRequestDto registersellerRequestDto) {
		UserSeller user = new UserSeller();
		BeanUtils.copyProperties(registersellerRequestDto, user);
		return user;
	}

}
