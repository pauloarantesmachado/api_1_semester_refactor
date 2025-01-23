package com.assessment360.app.utils;

public enum SemesterEnum {
	FIRST("First"),
	SECOND("Second"),
	THIRD("Third"),
	FOURTH("Fourth"),
	FIFTH("Fifth"),
	SIXTH("Sixth");
	
	private String valueSemester;
	
	private SemesterEnum(String value) {
		this.valueSemester = value;
	}
	
    public String getValue() {
        return valueSemester;
    }
	
}
