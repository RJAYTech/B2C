package com.seller.service;

import java.util.List;


import com.seller.enity.UserSeller;



public interface sellerService {

 UserSeller addUser(UserSeller seller); 
	
	UserSeller updateUser(UserSeller seller);

	UserSeller getUserByEmailAndStatus(String emailId, String status);

	UserSeller getUserByEmailid(String emailId);

	List<UserSeller> getUserByRole(String role);
	
	UserSeller getUserById(int userId);
	
	List<UserSeller> getUserBySellerAndRoleAndStatusIn(UserSeller seller, String role, List<String> status);
	
	UserSeller getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status);
	
	List<UserSeller> updateAllUser(List<UserSeller> users);
	
	List<UserSeller> getUserByRoleAndStatus(String role, String status);
}
