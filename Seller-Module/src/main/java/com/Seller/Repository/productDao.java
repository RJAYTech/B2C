package com.Seller.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.Seller.DTO.productImageDto;
import com.Seller.Model.Product;

@Repository
public interface productDao extends JpaRepository<Product, Integer> {

	
	List<Product> findByProductNameAndImage1(String productName, MultipartFile image1);

	void save(productImageDto pi);

	void save(int id);
	

}
