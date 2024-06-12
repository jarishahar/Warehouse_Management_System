package com.jsp.wms.exception;

public class AddressNotFoundByIdException extends RuntimeException {
private String message;

public AddressNotFoundByIdException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}

}
