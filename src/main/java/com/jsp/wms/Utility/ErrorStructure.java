package com.jsp.wms.Utility;



public class ErrorStructure <T> {
	private int status ;
	private String message;
	private T rootcase;
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public T getRootcase() {
		return rootcase;
	}
	public ErrorStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	public ErrorStructure<T> setMessage(String message) {
		this.message = message;
		return  this;
	}
	public ErrorStructure<T> setRootcase(T rootcase) {
		this.rootcase = rootcase;
		return this;
	}
}
