package com.jsp.wms.exception;

public class IllegalOperationException extends RuntimeException {
private String message;

public IllegalOperationException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}

}
