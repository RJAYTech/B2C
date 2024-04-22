package com.Seller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Seller.DTO.AddReviewRequest;
import com.Seller.ResponseRequest.CommonApiResponse;
import com.Seller.ResponseRequest.ProductReviewResponse;
import com.Seller.Service.ReviewService;

@RestController
@RequestMapping("/product/review")
public class ReviewController {
	
	@Autowired
	private ReviewService rs;
	
	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addProductReview(@RequestBody AddReviewRequest review) {
		return rs.addReview(review);
	}
	
	@GetMapping("/fetchReviews/{id}")
	public ResponseEntity<ProductReviewResponse> getProducts(@PathVariable ("id") int id){
		return rs.getReview(id);
	}
	
	

}
