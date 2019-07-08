package com.ecomorce.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductDetails implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String productName;
	private double price;

	
	@ManyToOne
	@JoinColumn(name = "productCategoryId")
	private ProductCategory productCategory;
	
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserDeatails userDeatails;
	
	
	
	
	
	
	
	
	public ProductDetails() {
	}

	public UserDeatails getUserDeatails() {
		return userDeatails;
	}

	public void setUserDeatails(UserDeatails userDeatails) {
		this.userDeatails = userDeatails;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*
	 * public Long getUserId() { return userId; }
	 * 
	 * public void setUserId(Long userId) { this.userId = userId; }
	 */

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	

}
