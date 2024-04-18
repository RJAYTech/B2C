package com.seller.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.seller.repo.UserSellerDao;

import lombok.Data;

@Data
public class sellerLoginRequest {

	@Autowired
	private UserSellerDao sellerRepository;

	private String emailId;

	private String role;
	private String password;

}
