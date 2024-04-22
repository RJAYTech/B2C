package com.Seller.ResponseRequest;

import com.Seller.Model.Review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReviewResponse extends CommonApiResponse{
   
	private Review review;
}
