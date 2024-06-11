package com.jsp.wms.exception;

public class AdminNotFoundByEmailException  extends RuntimeException{
private String message;

public AdminNotFoundByEmailException(String message) {
	
	this.message = message;
}

@Override
public String getMessage() {
	return super.getMessage();
}

}
