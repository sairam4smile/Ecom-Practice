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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecomorce.dto.UserDeatailsDto;
import com.ecomorce.exceptions.EcomorseException;
import com.ecomorce.model.Role;
import com.ecomorce.model.UserDeatails;
import com.ecomorce.repository.UserDeatailsRepository;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
public class TestUserService {
	
	
	@Mock
	 UserDeatailsDto userDeatailsDto;
	
	
	
	 UserDeatails userDeatails;

	 ResponseEntity<UserDeatails> userDeatailsresponseEntity;

	
	@Mock
	UserDeatailsRepository userDeatailsRepository;
	
	@InjectMocks
	UserService customerService;
	
	List<UserDeatails> userDetailsList;

	@Before
	public void init() {
		
		userDeatailsDto=new UserDeatailsDto();
		userDeatailsDto.setPassword("1234");
		userDeatailsDto.setRole("BUYER");
		userDeatailsDto.setUserId(1L);
		userDeatailsDto.setUserName("sairam");
		
		userDeatails=new UserDeatails();
		userDeatails.setPassword("1234");
		userDeatails.setUserType("BUYER");
		userDeatails.setUserId(1L);
		userDeatails.setUserName("sairam");
		
		userDetailsList=new ArrayList<>();
		
		
	}
	

	   ///////////  registration test cases start   ////////////////////
	
	@Test
	public void TestRegistration() {
		
		
		Mockito.when(userDeatailsRepository.findByUserName(userDeatailsDto.getUserName())).thenReturn(userDetailsList);
		Mockito.when(userDeatailsRepository.save(userDeatails)).thenReturn(userDeatails);

		 ResponseEntity<String> actualvalue = customerService.registration(userDeatailsDto);
		Assert.assertEquals(201,actualvalue.getStatusCodeValue());
		
		
	}
	
	
	@Test(expected = EcomorseException.class)
	public void TestRegistrationException() {
		
		userDetailsList.add(userDeatails);
		
		Mockito.when(userDeatailsRepository.findByUserName(userDeatailsDto.getUserName())).thenReturn(userDetailsList);
		Mockito.when(userDeatailsRepository.save(userDeatails)).thenReturn(userDeatails);

		ResponseEntity<String> actualvalue = customerService.registration(userDeatailsDto);
		Assert.assertEquals(201,actualvalue.getStatusCodeValue());

		
	}
	
	
	  @Test(expected = EcomorseException.class)
	  public void TestRegistrationExceptionRole() {
	  
		  userDeatailsDto.setRole("admin");
	  
	  Mockito.when(userDeatailsRepository.findByUserName(userDeatailsDto.getUserName())).thenReturn(userDetailsList);
	  Mockito.when(userDeatailsRepository.save(userDeatails)).thenReturn(userDeatails);
	  ResponseEntity<String> actualvalue=customerService.registration(userDeatailsDto);
	  Assert.assertEquals(201,actualvalue.getStatusCodeValue());
	  
	  
	  }
	  
	  
	   ///////////  registration test cases end   ////////////////////

	  
	   ///////////  updateProfile test cases start   ////////////////////
	  
	  
	  @Test
	  public void TestUpdateProfile() {
		  
	  
	  Mockito.when(userDeatailsRepository.findById(userDeatails.getUserId())).thenReturn(Optional.of(userDeatails));
	  Mockito.when(userDeatailsRepository.save(userDeatails)).thenReturn(userDeatails);
	  ResponseEntity<UserDeatails> actualvalue=customerService.updateProfile(userDeatailsDto);
	  Assert.assertEquals(201,actualvalue.getStatusCodeValue());
	  
	  
	  }
	  
	  @Test(expected = EcomorseException.class)
	  public void TestUpdateProfileUseNot() {
		  
		  userDeatailsDto.setRole("admin");
	  
	  Mockito.when(userDeatailsRepository.findByUserName(userDeatailsDto.getUserName())).thenReturn(userDetailsList);
	  Mockito.when(userDeatailsRepository.save(userDeatails)).thenReturn(userDeatails);
	  ResponseEntity<UserDeatails> actualvalue=customerService.updateProfile(userDeatailsDto);
	  Assert.assertEquals(201,actualvalue.getStatusCodeValue());
	  
	  
	  }
	  
	   ///////////  updateProfile test cases end   ////////////////////

	  
	  ///////////  deleteProfile test cases start   ////////////////////
	  
	  @Test
	  public void TestDeleteProfile() {
		  
	  
		  Mockito.when(userDeatailsRepository.findById(userDeatails.getUserId())).thenReturn(Optional.of(userDeatails));
	  Mockito.when(userDeatailsRepository.save(userDeatails)).thenReturn(userDeatails);
	  ResponseEntity<String> actualvalue=customerService.deleteProfile(userDeatailsDto.getUserId());
	  Assert.assertEquals(202,actualvalue.getStatusCodeValue());
	  
	  
	  }
	  
	  
	  @Test(expected = EcomorseException.class)
	  public void TestDeleteProfileException() {
		  
	  
	  Mockito.when(userDeatailsRepository.findByUserName(userDeatailsDto.getUserName())).thenReturn(userDetailsList);
	  Mockito.when(userDeatailsRepository.save(userDeatails)).thenReturn(userDeatails);
	  ResponseEntity<String> actualvalue=customerService.deleteProfile(userDeatailsDto.getUserId());
	  Assert.assertEquals(201,actualvalue.getStatusCodeValue());
	  
	  
	  }
	   ///////////  deleteProfile test cases end   ////////////////////

	  
      ///////////  login test cases start   ////////////////////
	  
	  
	  @Test
	  public void TestLogin() {
		  
		  userDetailsList.add(userDeatails);
	  Mockito.when(userDeatailsRepository.findByUserNameAndPassword(userDeatailsDto.getUserName(),userDeatailsDto.getPassword())).thenReturn(userDetailsList);
	  ResponseEntity<String> actualvalue=customerService.login(userDeatailsDto.getUserName(),userDeatailsDto.getPassword());
	  
	  Assert.assertEquals(202,actualvalue.getStatusCodeValue());
	  
	  
	  }
	  
	  @Test(expected = EcomorseException.class)
	  public void TestLoginException() {
		  
		  Mockito.when(userDeatailsRepository.findByUserNameAndPassword(userDeatailsDto.getUserName(),userDeatailsDto.getPassword())).thenReturn(userDetailsList);
		  ResponseEntity<String> actualvalue=customerService.login(userDeatailsDto.getUserName(),userDeatailsDto.getPassword());
		  
		  
	  
	  }
	  
   ///////////  login test cases end   ////////////////////
	  
	  
	  
///////////  getProfiles test cases end   ////////////////////
	  
	  @Test
	  public void TestGetProfiles() {
		  
		  userDetailsList.add(userDeatails);
	  Mockito.when(userDeatailsRepository.findByUserType(userDeatails.getUserType())).thenReturn(userDetailsList);
	  ResponseEntity<List<UserDeatails>> actualvalue=customerService.getProfiles(userDeatails.getUserType());
	  
	  Assert.assertEquals(userDetailsList,actualvalue.getBody());
	  
	  
	  }
	  
	  
///////////  getProfiles test cases end   ////////////////////

	 
}
