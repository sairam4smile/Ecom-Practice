package com.ecomorce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecomorce.dto.ProductDetailsDto;
import com.ecomorce.exceptions.EcomorseException;
import com.ecomorce.model.ProductCategory;
import com.ecomorce.model.ProductDetails;
import com.ecomorce.model.Role;
import com.ecomorce.model.UserDeatails;
import com.ecomorce.repository.ProductCategoryRepository;
import com.ecomorce.repository.ProductDetailsRepository;
import com.ecomorce.repository.UserDeatailsRepository;

@Service
public class ProductService  implements IProductService{
	
	@Autowired ProductDetailsRepository productDetailsRepository;
	@Autowired ProductCategoryRepository productCategoryRepository;
	@Autowired UserDeatailsRepository userDeatailsRepository;


	@Override
	public ResponseEntity<String> addProduct(ProductDetailsDto productDetailsDto) {
		
		if(!productCategoryRepository.findById(productDetailsDto.getProductCategoryId()).isPresent())
					throw new EcomorseException("inavlid product category");
		
		Optional<UserDeatails> userDeatails = userDeatailsRepository.findById(productDetailsDto.getUserId());
		if(!userDeatails.isPresent())
			        throw new EcomorseException("inavlid user category");
		
		if(!(userDeatails.get().getRole()).equalsIgnoreCase((Role.SELLER.toString())))
	        throw new EcomorseException("only seller can add the products ");
		
		
		ProductDetails productDetails=new ProductDetails();
		ProductCategory productCategory=new ProductCategory();
		productCategory.setProductCategoryId(productDetailsDto.getProductCategoryId());
		productDetails.setPrice(productDetailsDto.getPrice());
		productDetails.setUserId(productDetailsDto.getUserId());
		productDetails.setProductName(productDetailsDto.getProductName());
		productDetails.setProductCategory(productCategory);

		return new ResponseEntity<>(productDetailsRepository.save(productDetails).getProductName()+" succsessfully added",HttpStatus.CREATED);
		 
	}

	@Override
	public List<ProductDetails> getProducts() {
		return productDetailsRepository.findAll();
	}

	
	   @Override 
	 public ResponseEntity<List<ProductDetailsDto>> getProductsByCategory(Long productCategoryId) { 
		   Optional<ProductCategory> productCategory = productCategoryRepository.findById(productCategoryId);
		 
		   if(!productCategory.isPresent())
			   throw new EcomorseException(productCategoryId+" is not present");
	
		        return  new ResponseEntity<>(productDetailsToDto(productCategory.get().getProductDetails()),HttpStatus.ACCEPTED);
	  }
	 
	@Override
	public  ResponseEntity<List<ProductDetailsDto>> getProductsByName(String productCategoryName) {
	
	           return new ResponseEntity<>(productDetailsToDto(productDetailsRepository.findByProductName(productCategoryName)),HttpStatus.OK);
	
	}

	
	public static List<ProductDetailsDto> productDetailsToDto(List<ProductDetails> productDetails) {
		
		List<ProductDetailsDto> productDetailsDtos =new ArrayList<>();
		for(ProductDetails product: productDetails) {
			ProductDetailsDto productDetailsDto=new ProductDetailsDto();
			productDetailsDto.setPrice(product.getPrice());
			productDetailsDto.setProductId(product.getProductId());
			productDetailsDto.setProductName(product.getProductName());
			productDetailsDto.setUserId(product.getUserId());
			productDetailsDtos.add(productDetailsDto);
			
		}
	
		return productDetailsDtos;
		
	}
	
	
	

}
