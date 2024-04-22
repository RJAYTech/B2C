package com.Seller.ResponseRequest;

import java.util.ArrayList;
import java.util.List;
import com.Seller.Model.Product;

public class ProductResponse extends CommonApiResponse {

	private List<Product> products = new ArrayList<>();

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
