package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommonApiResponse;
import com.example.dto.RegisterUserRequestDto;
import com.example.resource.UserResource;


@RestController
@RequestMapping("/admin")
public class UserController {
	@Autowired
	UserResource resource;
	
	@PostMapping(value = "/register")
	
	public ResponseEntity<CommonApiResponse> reister(@RequestBody RegisterUserRequestDto usergerister){
		return resource.registerAdmin(usergerister);
	}

}
