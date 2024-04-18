package com.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seller.dto.CommonApiResponse;
import com.seller.dto.RegistersellerRequestDto;
import com.seller.dto.UserResponseDto;
import com.seller.resource.sellerResource;

//import io.swagger.v3.oas.annotations.Operation;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/userSeller")
public class UserController {

	@Autowired
	private sellerResource sellerresource;

	/*
	 * @Autowired private AuthenticationManager authenticationManager;
	 */

	@PostMapping("register")
	// @Operation(summary = "Api to register customer or seller user")
	public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegistersellerRequestDto request) {
		return this.sellerresource.registerUser(request);
	}

	@GetMapping("/fech/role")
	public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role)
			throws JsonProcessingException {
		return sellerresource.getUsersByRole(role);
	}
	/*
	 * @PostMapping("/login") public ResponseEntity<String> loginseller(@RequestBody
	 * sellerLoginRequest userLoginRequest) { Authentication authentication =
	 * authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(userLoginRequest.getEmailId(),
	 * userLoginRequest.getPassword()));
	 * SecurityContextHolder.getContext().setAuthentication(authentication); return
	 * new ResponseEntity<>(" user logged  sucessfully", HttpStatus.OK); }
	 */

	
	  @DeleteMapping("/delete") 
	  
	  public ResponseEntity<CommonApiResponse>deleteSeller(@RequestParam(value = "id", required = true)Integer sellerId) { 
		  return sellerresource.deleteSeller(sellerId); }
	 

}
