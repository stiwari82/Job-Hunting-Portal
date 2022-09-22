package com.jobhunting.exception;

public class RecruiterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecruiterNotFoundException(String message) {
		super(message);
	}
	
}
