package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exceptions.ApiError;
import com.example.service.ProductService2;

@RestController
public class Product {
	@Autowired
	ProductService2 ps;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ApiError> addProduct(@RequestBody Product ps){
		return ps.addProduct(ps);
	}

}
