package com.jobhunting.exception;

public class AlreadyAppliedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AlreadyAppliedException(String message) {
		super(message);
	}
	
}
