package com.seller.enity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String orderId;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private UserSeller user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private product product;

	private int quantity;

	private String orderTime;

	private String status; // Processing, Delivered, On the Way // this will be for customer

	// delivery properties

	// updated by seller
	@ManyToOne
	@JoinColumn(name = "delivery_person_id")
	private UserSeller deliveryPerson;

	// updated by delivery person
	private String deliveryTime; // Evening, Morning, Afternoon, Night

	private String deliveryDate;

	private String deliveryStatus; // Delivered, Pending // this will be for actual delivery status

	

}
