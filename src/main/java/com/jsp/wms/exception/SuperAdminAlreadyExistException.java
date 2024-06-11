package com.jsp.wms.exception;

@SuppressWarnings("serial")
public class SuperAdminAlreadyExistException extends RuntimeException {
 private String message;
	public SuperAdminAlreadyExistException(String message) {
		this.message=message;
	}
	@Override
	public String getMessage() {
		return this.getMessage();
	}

}
