package com.seller.enity;

import java.math.BigDecimal;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	private BigDecimal price;
	
	 
	private int quantity;

	private String status;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private UserSeller seller;

	private String image1;

	private String image2;

	private String image3;

}
