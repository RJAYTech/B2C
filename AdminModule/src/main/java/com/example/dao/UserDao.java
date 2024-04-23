package com.example.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer>{


List<User> findByEmailId(String emailId);
List<User> findByFirstNameAndLastName(String firstname, String lastName);

List<User> findByPhoneNo(String phoneNo);
}
