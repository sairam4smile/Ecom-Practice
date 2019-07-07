package com.ecomorce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomorce.dto.UserDeatailsDto;
import com.ecomorce.model.UserDeatails;
import com.ecomorce.service.IUserService;

@RestController
@RequestMapping("/ecomorse")
public class UserController {
	
	@Autowired IUserService iUserService;
	
	
	@PostMapping("/user")
	public ResponseEntity<String> registration(@RequestBody UserDeatailsDto userDeatailsDto) {
		return  iUserService.registration(userDeatailsDto);
		
	}

	@PutMapping("/user")
	public ResponseEntity<UserDeatails> updateProfile(@RequestBody UserDeatailsDto userDeatailsDto) {
		return  iUserService.updateProfile(userDeatailsDto);
		
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteProfile(@PathVariable("userId") Long userId) {
		return  iUserService.deleteProfile(userId);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestHeader("userName") String userName, @RequestHeader("password") String password) {
		return  iUserService.login(userName, password);
		
	}
	
	
	@GetMapping("/user/role/{role}")
	public ResponseEntity<List<UserDeatails>> getProfiles(@PathVariable("role") String role) {
		return  iUserService.getProfiles(role);
		
	}
	
	
	

}
