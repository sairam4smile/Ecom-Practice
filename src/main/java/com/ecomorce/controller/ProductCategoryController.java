package com.ecomorce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomorce.dto.ProductCategoryDto;
import com.ecomorce.model.ProductCategory;
import com.ecomorce.repository.ProductCategoryRepository;

@RestController
@RequestMapping("/product")
public class ProductCategoryController {
	@Autowired ProductCategoryRepository productCategoryRepository;
	
	
	@PostMapping("/category")
	public ProductCategory addProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
		
		ProductCategory productCategory=new ProductCategory();
		productCategory.setProductCategoryId(productCategoryDto.getProductCategoryId());
		productCategory.setProductCategoryName(productCategoryDto.getProductCategoryName());
		return productCategoryRepository.save(productCategory);
		
	}

}
