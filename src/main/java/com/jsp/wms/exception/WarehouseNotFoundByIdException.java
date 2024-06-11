package com.jsp.wms.exception;

public class WarehouseNotFoundByIdException extends RuntimeException {
	private String message;

	public WarehouseNotFoundByIdException(String message) {
	
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.getMessage();
	}
	

}
