package com.driver;

public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(String name) {
		super("Student Not Found with name: " + name);
	}
	public StudentNotFoundException() {
		super("No Student is present in the database!!!");
	}	
}
