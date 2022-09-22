package com.jobhunting.exception;

public class ProjectAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ProjectAlreadyExistException(String message) {
		super(message);
	}
	
}
