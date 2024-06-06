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
					.setRootcase(rootCause));
		}
	 
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>>handleSuperAdminAlreadyExist(SuperAdminAlreadyExistException ex){
		return errorResponse(HttpStatus.ALREADY_REPORTED, ex.getMessage(), "Only one super admin can exist");
	}
}
	
