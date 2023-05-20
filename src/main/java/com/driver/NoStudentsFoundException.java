package com.driver;

public class NoStudentsFoundException extends RuntimeException {
	public NoStudentsFoundException(String teacher) {
		super("No student found under the teacher '" + teacher + "'!!!!");
	}
}
