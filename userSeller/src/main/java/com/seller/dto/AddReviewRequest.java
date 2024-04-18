package com.seller.dto;

import lombok.Data;

@Data
public class AddReviewRequest {

	private int UserSeller_id;

	private int productId;

	private int star;

	private String review;



}
