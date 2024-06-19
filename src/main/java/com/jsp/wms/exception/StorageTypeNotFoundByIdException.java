package com.jsp.wms.exception;

public class StorageTypeNotFoundByIdException extends RuntimeException {
private String message;

public StorageTypeNotFoundByIdException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}


}
