package com.jobhunting.exception;

public class InvalidStatusUpdateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidStatusUpdateException(String message) {
		super(message);
	}
	
}