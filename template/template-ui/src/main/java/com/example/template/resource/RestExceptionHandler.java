package com.example.template.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.template.service.ServiceException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handleServiceException(ServiceException e) {
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
