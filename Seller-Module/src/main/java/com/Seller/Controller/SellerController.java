package com.Seller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Seller.DTO.LoginDTO;
import com.Seller.Model.Seller;
import com.Seller.ResponseRequest.CommonApiResponse;
import com.Seller.Service.SellerService;

@RestController
public class SellerController {
	@Autowired
	SellerService ss;
	
	@PostMapping(value = "/registerSeller")
	public ResponseEntity<CommonApiResponse> registerSeller(@RequestBody Seller s){
		return ss.register(s);
	}
	
	@GetMapping(value = "/login")
	public ResponseEntity<CommonApiResponse> login(@RequestBody LoginDTO ldto){
		return ss.login(ldto);
	}
	

}
