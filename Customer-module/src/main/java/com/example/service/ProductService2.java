package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Exceptions.ApiError;
import com.example.Model.Product;
import com.example.repo.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService2 {

	@Autowired
	ProductRepo pr;

	public ResponseEntity<ApiError> addProduct(Product p) {

		log.info("Request Receieved for adding Product");

		ApiError ae = new ApiError();
		if (p == null) {
			ae.setResponse("Inputs Missings");
			ae.setSuccess(true);
			return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		}
		try {
			pr.save(p);
			ae.setResponse("Product Added Successfull");
			ae.setSuccess(true);
			return new ResponseEntity<ApiError>(ae, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.info("Something Went Wrong while Adding Product");
			ae.setResponse("Something Went Wrong try again...");
			ae.setSuccess(false);
			return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		}
	}
}
