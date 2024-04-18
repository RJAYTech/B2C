package com.seller.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.enity.UserSeller;



@Repository
public interface UserSellerDao extends JpaRepository<UserSeller, Integer> {


	Optional<UserSeller>findByEmailId(String emailId);
	
	UserSeller findByEmailIdAndStatus(String email, String status);

	UserSeller findByRoleAndStatusIn(String role, List<String> status);

	List<UserSeller> findByRole(String role);

	List<UserSeller> findBySellerAndRole(UserSeller seller, String role);
	
	List<UserSeller> findBySellerAndRoleAndStatusIn(UserSeller seller, String role, List<String> status);
	
	UserSeller findByEmailIdAndRoleAndStatus(String emailId, String role, String status);
	
	List<UserSeller> findByRoleAndStatus(String role, String status);

}
