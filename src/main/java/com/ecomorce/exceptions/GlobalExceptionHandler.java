package com.ecomorce.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecomorce.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception, WebRequest request) {
		 ErrorResponse errorResponse = new ErrorResponse();
		  errorResponse.setMessage(exception.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	
	  //This is for specific exception to handle
	  
	  @ExceptionHandler(EcomorseException.class) public
	  ResponseEntity<ErrorResponse> ecomorseExceptionHandler(EcomorseException ex,WebRequest request) {
		  ErrorResponse errorResponse = new ErrorResponse();
		  errorResponse.setMessage(ex.getMessage());
	  
	  return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	  
	  }
	 

}