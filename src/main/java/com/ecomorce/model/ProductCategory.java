package com.ecomorce.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory implements Serializable{
	

	private static final long serialVersionUID = 1L;




	public ProductCategory() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productCategoryId;
	private String productCategoryName;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ProductDetails> productDetails;
	
	
	
	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	
	
	
	
	
}
