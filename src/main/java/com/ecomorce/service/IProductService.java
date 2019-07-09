package com.ecomorce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecomorce.dto.ProductDetailsDto;
import com.ecomorce.model.ProductDetails;

public interface IProductService {
	
	public ResponseEntity<String> addProduct( ProductDetailsDto productDetailsDto);
	public List<ProductDetails> getProducts();
	public ResponseEntity<List<ProductDetailsDto>> getProductsByCategory(String productCategoryName);
	public ResponseEntity<List<ProductDetailsDto>> getProductsByName(String productCategoryName);


}
