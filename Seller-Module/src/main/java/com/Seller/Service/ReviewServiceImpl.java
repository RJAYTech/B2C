package com.Seller.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Seller.DTO.AddReviewRequest;
import com.Seller.Model.Product;
import com.Seller.Model.Review;
import com.Seller.Repository.ReviewDao;
import com.Seller.ResponseRequest.CommonApiResponse;
import com.Seller.ResponseRequest.ProductReviewResponse;
import com.Seller.resource.CommonResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	CommonResource cr;

	@Autowired
	ReviewDao rd;

	@Override
	public ResponseEntity<CommonApiResponse> addReview(AddReviewRequest re) {
		log.info("Request Received for AddReviews");
		CommonApiResponse ae = new CommonApiResponse();

		Product product = cr.getProductById(re.getProductId());
		Review r = new Review();
		if (product != null) {
			r.setId(re.getId());
			r.setProduct(product);
			r.setReview(re.getReview());
			r.setStar(re.getStar());
			Review save = rd.save(r);

			if (save == null) {
				ae.setResponse("Review not added");
				ae.setResult("Failed");
				return new ResponseEntity<CommonApiResponse>(ae, HttpStatus.BAD_REQUEST);
			}

		}
		ae.setResponse("Review added");
		ae.setResult("Success");
		return new ResponseEntity<CommonApiResponse>(ae, HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<ProductReviewResponse> getReview(int id) {
		ProductReviewResponse prr = new ProductReviewResponse();
		Review byid = cr.byid(id);
		if (byid != null) {
			prr.setReview(byid);
			prr.setResponse("success");
			prr.setResult("success");

			return new ResponseEntity<ProductReviewResponse>(prr, HttpStatus.OK);
		} else {

			prr.setResponse("Failed");
			prr.setResult("Failed");

			return new ResponseEntity<ProductReviewResponse>(prr, HttpStatus.OK);
		}
	}
}
