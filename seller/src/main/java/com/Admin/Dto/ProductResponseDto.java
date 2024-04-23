package com.Admin.Dto;

import java.util.ArrayList;
import java.util.List;

import com.Admin.entity.Product;



public class ProductResponseDto extends CommonApiResponse{
	private List<Product> cat = new ArrayList<>();

	public List<Product> getCategories() {
	    return cat;
	}

	public void setproducts(List<Product> cat) {
	    this.cat = cat;
	}
}
