package com.Seller.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Seller.Enum.Role;
import com.Seller.Model.Seller;

@Repository
public interface SellerDao extends JpaRepository<Seller, Integer> {

	Seller findByEmailid(String emailid);

	 Seller findByEmailidAndPassword(String emailid, String password);
	//List<Seller> findByEmailidAndPassword(String e, String p);

	List<Seller> findByRole(Role role);

	/*
	 * @Query(value = "", nativeQuery = true) List<Seller> abc();
	 */}
