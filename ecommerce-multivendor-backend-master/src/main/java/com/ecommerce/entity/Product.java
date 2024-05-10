package com.ecommerce.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob; 
 import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	private BigDecimal price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

	private int quantity;

	private String status;

	@ManyToOne
	@JoinColumn(name = "seller_user_id")
	private User seller;
	@Lob
	@Column(name = "image1",columnDefinition = "LONGBLOB")
	private byte[] image1;
	@Lob
	@Column(name = "image2",columnDefinition = "LONGBLOB")
	private byte[] image2;
	@Lob
	@Column(name = "image3",columnDefinition = "LONGBLOB")
	private byte[] image3;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

}
