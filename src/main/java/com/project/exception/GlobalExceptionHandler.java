package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.util.CommonUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(exception = IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
