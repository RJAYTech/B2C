package com.Seller.Model;

import com.Seller.Enum.Role;
import com.Seller.Enum.Status;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sellerEcommerce")
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "emailId")
	private String emailid;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	private String contactNo;

	private String location;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "seller_user_id")
	private Seller seller;

}
