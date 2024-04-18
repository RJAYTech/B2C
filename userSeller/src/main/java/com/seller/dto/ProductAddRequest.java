package com.seller.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.seller.enity.product;

import lombok.Data;

@Data
public class ProductAddRequest {

	private int id;

	private String name;

	private String description;

	private BigDecimal price;

	private int quantity;

	private int sellerId;
	
	private String status;
	

	private MultipartFile image1;

	private MultipartFile image2;

	private MultipartFile image3;

	public static product toEntity(ProductAddRequest dto) {
		product entity = new product();
		BeanUtils.copyProperties(dto, entity, "image1", "image2", "image3", "sellerId");
		return entity;
	}

}
