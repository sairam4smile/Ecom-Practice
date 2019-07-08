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


@Entity
@Table(name = "USER_DETAILS")
public class UserDeatails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String userName;
	private String password;
	private String userType;
	
	/*
	 * @OneToMany(mappedBy = "userDeatails" , cascade = CascadeType.ALL)
	 * List<ProductDetails> productDetails;
	 */

	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/*
	 * public List<ProductDetails> getProductDetails() { return productDetails; }
	 * public void setProductDetails(List<ProductDetails> productDetails) {
	 * this.productDetails = productDetails; }
	 */

	
	
	

}
