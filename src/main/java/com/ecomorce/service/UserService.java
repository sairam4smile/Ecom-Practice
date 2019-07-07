package com.ecomorce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecomorce.dto.UserDeatailsDto;
import com.ecomorce.exceptions.EcomorseException;
import com.ecomorce.model.Role;
import com.ecomorce.model.UserDeatails;
import com.ecomorce.repository.UserDeatailsRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired UserDeatailsRepository userDeatailsRepository;
	@Autowired UserService userServiceobj;
	
	@Override
	public ResponseEntity<String> registration(UserDeatailsDto userDeatailsDto) {
		
		if(!userDeatailsRepository.findByUserName(userDeatailsDto.getUserName()).isEmpty()) {
			throw new EcomorseException(userDeatailsDto.getUserName()+" is already existed");
		}
			 userDeatailsDto.setUserId(0l);
			 
			 userDeatailsRepository.save(userRegistration(userDeatailsDto));		     
		     return new ResponseEntity<>("user succsessfully created",HttpStatus.CREATED);
  
	}

	@Override
	public ResponseEntity<UserDeatails> updateProfile(UserDeatailsDto userDeatailsDto) {

		if(!userDeatailsRepository.findById(userDeatailsDto.getUserId()).isPresent()) {
			throw new EcomorseException(userDeatailsDto.getUserId()+" is not existed");
		}	
		List<UserDeatails> userDeatails = userDeatailsRepository.findByUserName(userDeatailsDto.getUserName());
		if(!userDeatails.isEmpty() && (!userDeatails.get(0).getUserId().equals(userDeatailsDto.getUserId()))) {
			throw new EcomorseException(userDeatailsDto.getUserName()+" is already existed");

		}

		
		return new ResponseEntity<>(userDeatailsRepository.save(userRegistration(userDeatailsDto)),HttpStatus.CREATED);
	}
	
	
	@Override
	public ResponseEntity<String> deleteProfile(Long userId) {
		
		if(!userDeatailsRepository.findById(userId).isPresent()) {
			throw new EcomorseException(userId+" is not existed");
		}	
		 userDeatailsRepository.deleteById(userId);
		 return new ResponseEntity<>(userId+"  deleted successfully",HttpStatus.ACCEPTED);
	}
	
	
	@Override
	public ResponseEntity<String> login(String userName, String password) {
		 if(userDeatailsRepository.findByUserNameAndPassword(userName,  password).isEmpty())
			 throw new EcomorseException("credintials are incorrect");
			 
			 return new ResponseEntity<>(userName+" login succesesfully",HttpStatus.ACCEPTED);
		 

	}

	@Override
	public  ResponseEntity<List<UserDeatails>> getProfiles(String role) {
		return  new ResponseEntity<>(userDeatailsRepository.findByRole(role.toUpperCase()),HttpStatus.OK);
	}
	
	
	
	public static  UserDeatails userRegistration(UserDeatailsDto userDeatailsDto) {
		
		
		if((!userDeatailsDto.getRole().equalsIgnoreCase((Role.BUYER).toString())) && (!userDeatailsDto.getRole().equalsIgnoreCase((Role.SELLER).toString()))) {
			throw new EcomorseException(userDeatailsDto.getRole()+" role is not valid");
			}
		UserDeatails  userDeatails=new UserDeatails();
		
		userDeatails.setUserId(userDeatailsDto.getUserId());
		userDeatails.setPassword(userDeatailsDto.getPassword());
		userDeatails.setRole(userDeatailsDto.getRole().toUpperCase());
		userDeatails.setUserName(userDeatailsDto.getUserName());
		return userDeatails;		
		  
	}

	

	

}
