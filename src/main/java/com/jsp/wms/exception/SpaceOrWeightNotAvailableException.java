package com.jsp.wms.exception;

public class SpaceOrWeightNotAvailableException extends RuntimeException {
private String message;

public SpaceOrWeightNotAvailableException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return super.getMessage();
}

}
