package com.Seller.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Seller.Model.Product;
import com.Seller.Model.Review;
import com.Seller.Repository.ReviewDao;
import com.Seller.Repository.productDao;



@Component
public class CommonResource {
	@Autowired
	productDao pd;
	
	@Autowired
	ReviewDao rd;
	
	public Product getProductById(int productId) {

		Optional<Product> optionalProduct = pd.findById(productId);

		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}

	}
	
	public Review byid(int id) {
		return rd.findById(id).get();
	}

}
