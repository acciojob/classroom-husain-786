package com.driver;

public class TeacherNotFoundException extends RuntimeException {
	public TeacherNotFoundException(String name) {
		super("Teacher Not Found with name: " + name);
	}
}
