package com.jobhunting.exception;

public class DuplicateRecruiterFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DuplicateRecruiterFoundException(String message) {
		super(message);
	}

}
