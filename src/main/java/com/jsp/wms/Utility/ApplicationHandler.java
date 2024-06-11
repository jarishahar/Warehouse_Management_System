package com.jsp.wms.Utility;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.wms.exception.SuperAdminAlreadyExistException;


@RestControllerAdvice
public class ApplicationHandler {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })

	 public ResponseEntity<ErrorStructure<String>>errorResponse(HttpStatus status,String message , String rootCause){
			return ResponseEntity.status(status).body(new ErrorStructure()
					.setStatus(status.value())
					.setMessage(message)
					.setRootcause(rootCause));
		}
	 
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>>handleSuperAdminAlreadyExist(SuperAdminAlreadyExistException ex){
		return errorResponse(HttpStatus.ALREADY_REPORTED, ex.getMessage(), "Only one super admin can exist");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure< Map<String, String>>>handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
    	List<ObjectError>errors=ex.getAllErrors();

		Map<String, String>allErrors = new HashMap<>();
		errors.forEach(error ->{
			FieldError fieldError=(FieldError) error;
			String field =fieldError.getField();
			String message = fieldError.getDefaultMessage();
			allErrors.put(field, message);
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorStructure<Map<String,String>>()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage("Invalid input")
				.setRootcause(allErrors));
	}
	
}
	
