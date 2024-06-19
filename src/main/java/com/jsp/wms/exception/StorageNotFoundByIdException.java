package com.jsp.wms.exception;

public class StorageNotFoundByIdException extends RuntimeException {
private String message;

public StorageNotFoundByIdException(String message) {
	super();
	this.message = message;
}
@Override
public String getMessage() {
	return super.getMessage();
}
}
