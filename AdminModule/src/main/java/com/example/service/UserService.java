package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {
User add(User u);

List<User>findbyemail(String email);

List<User> findbyfirstnameAndlastname(String first, String last);

List<User> findbyphoneNo(String phoneNo);
}
