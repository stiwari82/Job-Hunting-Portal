package com.jobhunting.exception;

public class DuplicateFreelancerFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DuplicateFreelancerFoundException(String message) {
		
		super(message);
	}

}
