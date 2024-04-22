package com.Seller.Service;

import org.springframework.http.ResponseEntity;

import com.Seller.DTO.AddReviewRequest;
import com.Seller.ResponseRequest.CommonApiResponse;
import com.Seller.ResponseRequest.ProductReviewResponse;

public interface ReviewService {

	ResponseEntity<CommonApiResponse> addReview(AddReviewRequest review);

	ResponseEntity<ProductReviewResponse> getReview(int id);
 
}
