package com.seller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.seller.enity.UserSeller;
import com.seller.enity.product;
import com.seller.repo.productrepo;



@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private productrepo productDao;

	@Override
	public product addProduct(product product) {
		return productDao.save(product);
	}

	@Override
	public product updateProduct(product product) {
		return productDao.save(product);
	}

	@Override
	public product getProductById(int productId) {

		Optional<product> optionalProduct = productDao.findById(productId);

		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}

	}

	@Override
	public List<product> getAllProductByStatusIn(List<String> status) {
		return this.productDao.findByStatusIn(status);
	}

	@Override
	public Long countByStatusIn(List<String> status) {
		return this.productDao.countByStatusIn(status);
	}

	@Override
	public Long countByStatusInAndSeller(List<String> status, UserSeller seller) {
		return this.productDao.countByStatusInAndSeller(status, seller);
	}

	@Override
	public List<product> getAllProductBySellerAndStatusIn(UserSeller Seller, List<String> status) {
		return this.productDao.findBySellerAndStatusIn(Seller, status);
	}


	
	public List<product> updateAllProduct(List<product> products) {
		return this.productDao.saveAll(products);
	}

	@Override
	public List<product> searchProductNameAndStatusIn(String productName, List<String> status) {

		return this.productDao.findByNameContainingIgnoreCaseAndStatusIn(productName, status);
	}

	@Override
	public List<product> searchProductNameAndSellerAndStatusIn(String productName, UserSeller seller, List<String> status) {
		return this.productDao.findByNameContainingIgnoreCaseAndSellerAndStatusIn(productName, seller, status);
	}

}
