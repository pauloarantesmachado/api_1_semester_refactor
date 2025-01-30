package com.assessment360.app.exception.collegeexception;

public class CollegeCreateException extends RuntimeException {
	
	public  CollegeCreateException() {
		super("Error in creating a college, incorrect data or data not provided");
	}

}
