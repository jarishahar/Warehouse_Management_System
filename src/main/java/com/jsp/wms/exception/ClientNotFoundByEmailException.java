package com.jsp.wms.exception;

public class ClientNotFoundByEmailException extends RuntimeException{
private String message;

public ClientNotFoundByEmailException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}

}