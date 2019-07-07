package com.ecomorce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecomorce.dto.ProductCategoryDto;
import com.ecomorce.dto.ProductDetailsDto;
import com.ecomorce.exceptions.EcomorseException;
import com.ecomorce.model.ProductCategory;
import com.ecomorce.model.ProductDetails;
import com.ecomorce.model.UserDeatails;
import com.ecomorce.repository.ProductCategoryRepository;
import com.ecomorce.repository.ProductDetailsRepository;
import com.ecomorce.repository.UserDeatailsRepository;

@RunWith(SpringRunner.class)
public class TestProductService {
	
	ProductCategory productCategory;
	ProductCategoryDto productCategoryDto;
	
	ProductDetails productDetails;
	ProductDetailsDto productDetailsDto;
	
	UserDeatails userDeatails;
	List<ProductDetails> productDetailsl;

	
	@Mock ProductDetailsRepository productDetailsRepository;
	@Mock ProductCategoryRepository productCategoryRepository;
	
	@InjectMocks
	ProductService productService;

	@Mock
	UserDeatailsRepository userDeatailsRepository;

	
	@Before
	public void init() {
		productCategoryDto=new ProductCategoryDto();
		productCategoryDto.setProductCategoryId(3L);
		productCategoryDto.setProductCategoryName("SPORTS");
		
		productCategory=new ProductCategory();
		productCategory.setProductCategoryId(3L);
		productCategory.setProductCategoryName("SPORTS");
		productCategory.setProductDetails(productDetailsl);

		productDetails=new ProductDetails();
		productDetails.setPrice(100);
		productDetails.setProductId(1L);
		productDetails.setProductName("bat");
		productDetails.setUserId(2L);
		productDetails.setProductCategory(productCategory);
		
		productDetailsDto=new ProductDetailsDto();
		productDetailsDto.setPrice(100);
		productDetailsDto.setProductId(1L);
		productDetailsDto.setProductName("bat");
		productDetailsDto.setUserId(2L);
		productDetailsDto.setProductCategoryId(3L);
		
		userDeatails=new UserDeatails();
		userDeatails.setPassword("1234");
		userDeatails.setRole("BUYER");
		userDeatails.setUserId(2L);
		userDeatails.setUserName("sairam");
		
		productDetailsl=new ArrayList<>();
		productDetailsl.add(productDetails);
		
	}
	
	 /////// addProduct test cases start////
	
	@Test
	public void TestAddProduct() {
		userDeatails.setRole("SELLER");
		productDetails.setProductCategory(productCategory);


		
		Mockito.when(productCategoryRepository.findById(productCategory.getProductCategoryId())).thenReturn(Optional.of(productCategory));
		Mockito.when(userDeatailsRepository.findById(productDetails.getUserId())).thenReturn(Optional.of(userDeatails));
		Mockito.when(productDetailsRepository.save(productDetails)).thenReturn(productDetails);
		 ResponseEntity<String> actualvalue = productService.addProduct(productDetailsDto);
		Assert.assertEquals(201,actualvalue.getStatusCodeValue());
		
		
	}
	
	
	@Test(expected = EcomorseException.class)
	public void TestAddProductBuyer() {

		
		Mockito.when(productCategoryRepository.findById(productCategory.getProductCategoryId())).thenReturn(Optional.of(productCategory));
		Mockito.when(userDeatailsRepository.findById(productDetails.getUserId())).thenReturn(Optional.of(userDeatails));
		Mockito.when(productDetailsRepository.save(productDetails)).thenReturn(productDetails);
		 ResponseEntity<String> actualvalue = productService.addProduct(productDetailsDto);
		Assert.assertEquals(201,actualvalue.getStatusCodeValue());
		
		
	}
	
	@Test(expected = EcomorseException.class)
	public void TestAddProductUser() {

		productDetails.setUserId(5L);
		
		Mockito.when(productCategoryRepository.findById(productCategory.getProductCategoryId())).thenReturn(Optional.of(productCategory));
		Mockito.when(userDeatailsRepository.findById(productDetails.getUserId())).thenReturn(Optional.of(userDeatails));
		Mockito.when(productDetailsRepository.save(productDetails)).thenReturn(productDetails);
		 ResponseEntity<String> actualvalue = productService.addProduct(productDetailsDto);
		Assert.assertEquals(201,actualvalue.getStatusCodeValue());
		
		
	}
	
	 /////// addProduct test cases end////
	
	 
	 /////// getProducts test cases start////
	 
	@Test
	public void TestGetProducts() {

		
		Mockito.when(productDetailsRepository.findAll()).thenReturn(productDetailsl);
		 List<ProductDetails> actualvalue = productService.getProducts();
		Assert.assertEquals(productDetailsl,actualvalue);
		
		
	}
	 /////// getProducts test cases end////
	  
///////  getProductsByCategory  test cases start////
	
	@Test
	public void TestGetProductsByCategory() {
		productCategory.setProductDetails(productDetailsl);


//		Mockito.when(productDetailsRepository.findById(productDetails.getProductId())).thenReturn(Optional.of(productDetails));
		Mockito.when(productCategoryRepository.findById(productCategory.getProductCategoryId())).thenReturn(Optional.of(productCategory));
		ResponseEntity<List<ProductDetailsDto>> actualvalue = productService.getProductsByCategory(productCategory.getProductCategoryId());
		Assert.assertEquals(202,actualvalue.getStatusCodeValue());
		
		
	}
	
	
	@Test(expected = EcomorseException.class)
	public void TestGetProductsByCategoryException() {

		
		Mockito.when(productDetailsRepository.findById(productDetails.getProductId())).thenReturn(Optional.of(productDetails));
		 ResponseEntity<List<ProductDetailsDto>> actualvalue = productService.getProductsByCategory(productDetails.getProductId());
		Assert.assertEquals(200,actualvalue.getStatusCodeValue());
		
		
	}
	 
///////  getProductsByCategory  test cases end////

	 
/////// getProductsByName test cases start////
	
	@Test
	public void TestGetProductsByName() {

		
		Mockito.when(productDetailsRepository.findByProductName(productDetails.getProductName())).thenReturn(productDetailsl);
		 ResponseEntity<List<ProductDetailsDto>> actualvalue = productService.getProductsByName(productDetails.getProductName());
		Assert.assertEquals(200,actualvalue.getStatusCodeValue());
		
		
	}
	
	
	@Test
	public void TestGetProductsByNameException() {
		productDetailsl=new ArrayList<>();
		
		Mockito.when(productDetailsRepository.findByProductName("KKK")).thenReturn(productDetailsl);
		 ResponseEntity<List<ProductDetailsDto>> actualvalue = productService.getProductsByName("KKK");
		Assert.assertEquals(productDetailsl.size(),actualvalue.getBody().size());
		
		
	}
	 
	 
/////// getProductsByName test cases end////



}
