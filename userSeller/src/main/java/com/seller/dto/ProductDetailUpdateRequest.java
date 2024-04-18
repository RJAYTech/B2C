package com.seller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDetailUpdateRequest {

	private int id;

	private String name;

	private String description;

	private BigDecimal price;

	private int quantity;

}
