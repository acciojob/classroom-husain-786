package com.driver;

import java.util.List;
import java.util.Optional;

public class StudentService {

	private StudentRepository studentRepository = new StudentRepository();
	
	// adding student.....
	public String addStudent(Student student) {
		studentRepository.addStudent(student);
		return "Student added by name: " + student.getName();
	}

	public String addTeacher(Teacher teacher) {
		studentRepository.addTeacher(teacher);
		return "Teacher added by name: " + teacher.getName();
	}

	public String addStudentAndTeacher(String student, String teacher) {		
		Optional<String> message = studentRepository.addStudentAndTeacher(student, teacher);
		if (message.isEmpty()) {
			return "Either Student or Teacher is not present!!!";
		}
		return message.get();
	}

	public Student getStudentByName(String name) {
		Optional<Student> message = studentRepository.getStudentByName(name);
		if (message.isEmpty()) {
			throw new StudentNotFoundException(name);
		}
		return message.get();
	}

	public Teacher getTeacherByName(String name) {
		Optional<Teacher> message = studentRepository.getTeacherByName(name);
		if (message.isEmpty()) {
			throw new TeacherNotFoundException(name);
		}
		return message.get();
	}

	public List<String> getStudentsByTeacherName(String teacher) {
		Optional<List<String>> students = studentRepository.getStudentsByTeacherName(teacher);
		if (students.isEmpty()) {
			throw new NoStudentsFoundException(teacher);
		}
		return students.get();
	}

	public String deleteTeacherByName(String teacher) {
		Optional<String> message = studentRepository.deleteTeacherByName(teacher);
		if (message.isEmpty()) {
			throw new TeacherNotFoundException(teacher);
		}
		return message.get();
	}

	public void deleteAllTeachers() {
		studentRepository.deleteAllTeachers();
	}

	public List<String> getAllStudents() {
		Optional<List<String>> message = studentRepository.getAllStudents();
		if (message.isEmpty()) {
			throw new StudentNotFoundException();
		}
		return message.get();
	}
	
}
