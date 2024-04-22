package com.Seller.DTO;

import com.Seller.Enum.Role;

import lombok.Data;

@Data
public class LoginDTO {
	
	private String emailid;
	
	private String password;
	
	private Role role;

}
