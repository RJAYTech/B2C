package com.Seller.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Seller.DTO.LoginDTO;
import com.Seller.Model.Seller;
import com.Seller.Repository.SellerDao;
import com.Seller.ResponseRequest.CommonApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	SellerDao sd;

	@Override
	public ResponseEntity<CommonApiResponse> register(Seller s) {
		log.info("Request Received for Registration");
		CommonApiResponse cr = new CommonApiResponse();
		Seller byEmailId = sd.findByEmailid(s.getEmailid());

		if (byEmailId != null) {
			log.info("Email Already Registered, Please Login");
			cr.setResponse("Email Already Registered, Please Register with new EmailId");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);

		} else if (s.getRole() == null) {
			log.info("Role Missing");
			cr.setResponse("Role is Missing");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);
		} else {
			log.info("Registration Successfull...");
			sd.save(s);
			cr.setResponse("Registration Successfull...");
			cr.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("unused")
	@Override
	public ResponseEntity<CommonApiResponse> login(LoginDTO s) {
		System.out.println("S VALUE:" + s);
		CommonApiResponse cr = new CommonApiResponse();

		log.info("Request Received for Login");

		Seller byEmailidAndPassword = sd.findByEmailidAndPassword(s.getEmailid(), s.getPassword());

		List<Seller> findbyRole = sd.findByRole(s.getRole());

		if (s == null) {
			log.info("Missing Inputs");
			cr.setResponse("Missings Inputs");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);
		} else if (s.getRole() == null) {

			log.info("Role Missing");
			cr.setResponse("Role is Missing");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);

		} else if (findbyRole.size() == 0) {

			log.info("No user registered with selected role");
			cr.setResponse("No user registered with selected role");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);

		}

		else if (byEmailidAndPassword == null) {

			log.info("Invalid Email or Password");
			cr.setResponse("Invalid Email or Password");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.NOT_ACCEPTABLE);

		} else {

			log.info("Login Success");
			cr.setResponse("Login Success");
			cr.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.ACCEPTED);

		}
	}

}
