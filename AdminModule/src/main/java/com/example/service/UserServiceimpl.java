package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.entity.User;


@Service
public class UserServiceimpl implements UserService{
	@Autowired
	UserDao dao;
	@Override
	public User add(User u) {
		// TODO Auto-generated method stub
		return dao.save(u) ;
	}
	@Override
	public List<User> findbyemail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmailId(email);
	}
	@Override
	public List<User> findbyfirstnameAndlastname(String first, String last) {
		// TODO Auto-generated method stub
		return dao.findByFirstNameAndLastName(first, last);
	}
	@Override
	public List<User> findbyphoneNo(String phoneNo) {
		
		return dao.findByPhoneNo(phoneNo);
	}
	

}
