package com.seller.dto;

import java.util.ArrayList;
import java.util.List;

import com.seller.enity.product;

public class ProductResponseDto extends CommonApiResponse {

	private List<product> products = new ArrayList<>();

	public List<product> getProducts() {
		return products;
	}

	public void setProducts(List<product> products) {
		this.products = products;
	}

}
