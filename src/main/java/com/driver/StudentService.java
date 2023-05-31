package com.driver;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

//	private StudentRepository studentRepository = new StudentRepository();
	@Autowired
	private StudentRepository studentRepository;
	
	// adding student.....
	public String addStudent(Student student) {
		studentRepository.addStudent(student);
		return "SUCCESS";
	}

	public String addTeacher(Teacher teacher) {
		studentRepository.addTeacher(teacher);
		return "SUCCESS";
	}

//	public String addStudentAndTeacher(String student, String teacher) {
//		Optional<String> message = studentRepository.addStudentAndTeacher(student, teacher);
//		if (message.isEmpty()) {
//			return "Either Student or Teacher is not present!!!";
//		}
//		return message.get();
//	}
	public String addStudentAndTeacher(String student, String teacher) {
		return studentRepository.addStudentAndTeacher(student, teacher);
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

	public String deleteAllTeachers() {
		return studentRepository.deleteAllTeachers();
	}

	public List<String> getAllStudents() {
		Optional<List<String>> message = studentRepository.getAllStudents();
		if (message.isEmpty()) {
			throw new StudentNotFoundException();
		}
		return message.get();
	}
	
}
