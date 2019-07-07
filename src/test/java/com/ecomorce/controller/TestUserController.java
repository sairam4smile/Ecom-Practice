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

import com.ecomorce.dto.UserDeatailsDto;
import com.ecomorce.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class TestUserController {
	
	@InjectMocks
	UserController customerController;
	
	@Mock
	private UserService customerService;
	
	private MockMvc mockmvc;
	
	 UserDeatailsDto userDeatailsDto;

	
	@Before
	public void setUp() throws Exception {

		mockmvc = MockMvcBuilders.standaloneSetup(customerController).build();
		
		userDeatailsDto=new UserDeatailsDto();
		userDeatailsDto.setPassword("1234");
		userDeatailsDto.setRole("BUYER");
		userDeatailsDto.setUserId(1L);
		userDeatailsDto.setUserName("sairam");
	}
	@Test
	public void testRegistration() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.post("/ecomorse/user").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(userDeatailsDto))).andExpect(status().isOk());

	}
	
	
	@Test
	public void testLogin() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.post("/ecomorse/user").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(userDeatailsDto)).header("userName", "sai").header("password", "1234")).andExpect(status().isOk());

	}
	
	@Test
	public void testUpdateCustomer() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.put("/ecomorse/user").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(userDeatailsDto))).andExpect(status().isOk());

	}
	
	
	@Test
	public void testDeleteProfile() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.delete("/ecomorse/user/{userId}", 1).contentType(MediaType.ALL)
				.accept(MediaType.ALL).content(asJsonString(userDeatailsDto))).andExpect(status().isOk());

	}
	
	@Test
	public void testGetCustomer() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.post("/ecomorse/user").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(userDeatailsDto))).andExpect(status().isOk());

	}
	
	
	@Test
	public void testGetProfiles() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.get("/ecomorse/user/role/{role}","SELLER").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(userDeatailsDto))).andExpect(status().isOk());

	}
	
	
	
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
