package com.ecomorce.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomorce.dto.ProductDetailsDto;
import com.ecomorce.model.ProductDetails;
import com.ecomorce.service.ProductService;

@RestController
@RequestMapping("/ecomorse")
public class ProductController {
	
	@Autowired ProductService productService;
	
	
	@PostMapping("/product")
	public ResponseEntity<String> addProduct( @RequestBody ProductDetailsDto productDetailsDto) {
		return productService.addProduct(productDetailsDto);
	}
	
	
	  @GetMapping("/products") 
	  public List<ProductDetails> getProducts() { 
		  return productService.getProducts();
	  
	  }
	  
	  
	  
	 
	
	
	@GetMapping("/products/category/{productCategory}/")
	public ResponseEntity<List<ProductDetailsDto>> getProductByCategory(@PathVariable("productCategory") String productCategory) {
		
		return productService.getProductsByCategory(productCategory);
		
	}
	
	@GetMapping("/products/{productName}/")
	public ResponseEntity<List<ProductDetailsDto>> getProductByname(@PathVariable("productName") String productName) {
		
		return productService.getProductsByName(productName);
		
	}
	
	
	

}
