package com.jsp.wms.exception;

public class BatchNotFoundException  extends RuntimeException{
private String message;

public BatchNotFoundException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}

}
