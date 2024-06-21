package com.jsp.wms.exception;

public class InventoryNotFoundByIdException extends RuntimeException {
private String message;

public InventoryNotFoundByIdException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}

}
