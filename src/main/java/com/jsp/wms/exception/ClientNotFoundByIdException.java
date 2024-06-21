package com.jsp.wms.exception;

public class ClientNotFoundByIdException extends RuntimeException{
private String message;

public ClientNotFoundByIdException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}

}
