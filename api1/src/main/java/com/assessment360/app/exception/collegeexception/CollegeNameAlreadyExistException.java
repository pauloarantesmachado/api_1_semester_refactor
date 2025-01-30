package com.assessment360.app.exception.collegeexception;

public class CollegeNameAlreadyExistException extends RuntimeException {
	public CollegeNameAlreadyExistException() {
		super("College Name Already Exist");
	}

}
