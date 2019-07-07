package com.ecomorce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecomorce.dto.UserDeatailsDto;
import com.ecomorce.model.UserDeatails;

public interface IUserService {

	public ResponseEntity<String> registration(UserDeatailsDto userDeatailsDto);	
	public ResponseEntity<UserDeatails> updateProfile(UserDeatailsDto userDeatailsDto);	
	public ResponseEntity<String> deleteProfile(Long userId);	
	public ResponseEntity<String> login(String userName, String password);	
	public ResponseEntity<List<UserDeatails>> getProfiles(String role);	


}
