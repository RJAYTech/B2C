package com.seller.service;

import java.util.List;


import com.seller.enity.UserSeller;
import com.seller.enity.product;



public interface ProductService {

	product addProduct(product product);

	product updateProduct(product product);

	product getProductById(int productId);

	Long countByStatusIn(List<String> status);

	Long countByStatusInAndSeller(List<String> status, UserSeller seller);

	List<product> getAllProductByStatusIn(List<String> status);

	List<product> getAllProductBySellerAndStatusIn(UserSeller Seller, List<String> status);





	List<product> updateAllProduct(List<product> products);

	List<product> searchProductNameAndStatusIn(String productName, List<String> status);

	List<product> searchProductNameAndSellerAndStatusIn(String productName, UserSeller seller, List<String> status);

}
