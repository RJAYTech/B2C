package com.Seller.Service;

import org.springframework.http.ResponseEntity;

import com.Seller.DTO.LoginDTO;
import com.Seller.Model.Seller;
import com.Seller.ResponseRequest.CommonApiResponse;

public interface SellerService {

	ResponseEntity<CommonApiResponse> register(Seller s);

	ResponseEntity<CommonApiResponse> login(LoginDTO ldto);

}
