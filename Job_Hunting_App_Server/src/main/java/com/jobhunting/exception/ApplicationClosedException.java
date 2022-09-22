package com.jobhunting.exception;

public class ApplicationClosedException extends RuntimeException{

 private static final long serialVersionUID = 1L;

	public ApplicationClosedException(String message) {
		super(message);
     }
	
}