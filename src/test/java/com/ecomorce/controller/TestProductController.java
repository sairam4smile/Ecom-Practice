package com.ecomorce.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecomorce.dto.ProductDetailsDto;
import com.ecomorce.dto.UserDeatailsDto;
import com.ecomorce.model.ProductDetails;
import com.ecomorce.service.UserService;
import com.ecomorce.service.IProductService;
import com.ecomorce.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class TestProductController {
	
	@InjectMocks
	ProductController productController;
	
	@Mock
	private IProductService iproductService;
	
	private MockMvc mockmvc;
	
	 UserDeatailsDto userDeatailsDto;
	 
	 ProductDetailsDto productDetailsDto;

	
	@Before
	public void setUp() throws Exception {

		mockmvc = MockMvcBuilders.standaloneSetup(productController).build();
		

		
		productDetailsDto=new ProductDetailsDto();
		productDetailsDto.setPrice(100);
		productDetailsDto.setProductId(1L);
		productDetailsDto.setProductName("bat");
		productDetailsDto.setUserId(2L);
		productDetailsDto.setProductCategoryId(3L);
		
	}
	@Test
	public void testAddProduct() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.post("/ecomorse/product").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(productDetailsDto))).andExpect(status().isOk());

	}
	
	@Test
	public void testGetProfiles() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.get("/ecomorse/products")
				.accept(MediaType.ALL)).andExpect(status().isOk());

	}
	
	@Test
	public void testGetProductByName() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.get("/ecomorse/products/{productName}","bat")
				.accept(MediaType.ALL)).andExpect(status().isOk());

	}
	
	@Test
	public void testGetProductByCategory() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.get("/ecomorse/products/category/{productCategoryId}/","SPORTS")
				.accept(MediaType.ALL)).andExpect(status().isOk());

	}
	
	
	
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
