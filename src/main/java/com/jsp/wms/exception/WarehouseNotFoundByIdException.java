package com.jsp.wms.exception;

public class WarehouseNotFoundByIdException extends RuntimeException {
	private String message;

	public WarehouseNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	

}
