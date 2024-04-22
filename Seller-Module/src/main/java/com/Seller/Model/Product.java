package com.Seller.Model;

import com.Seller.Enum.Status;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String productName;

	private int price;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Lob
	@Column(name = "image1",columnDefinition = "LONGBLOB")
	private byte[] image1;
	
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	


	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "seller_user_id") private User seller;
	 */
	

}
