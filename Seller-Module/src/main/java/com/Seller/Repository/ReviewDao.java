package com.Seller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Seller.Model.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, Integer> {

}
