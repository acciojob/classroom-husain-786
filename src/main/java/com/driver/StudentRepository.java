package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
	private Map<String, Student> studentDB;
	private Map<String, Teacher> teacherDB;
	private Map<Teacher, Set<Student>> studentTeacherDB;
	private Map<String, Set<String>> studentTeacherPairDB;

	public StudentRepository() {
		studentDB = new HashMap<>();
		teacherDB = new HashMap<>();
		studentTeacherDB = new HashMap<>();		
		studentTeacherPairDB = new HashMap<>();		
	}
	
	// adding new Student into database....
	public void addStudent(Student student) {
		studentDB.put(student.getName(), student);
	}

	// adding new Teacher into database....	
	public void addTeacher(Teacher teacher) {
		teacherDB.put(teacher.getName(), teacher);	
		studentTeacherDB.put(teacher, new HashSet<>());
		studentTeacherPairDB.put(teacher.getName(), new HashSet<>());
	}

	// adding new Student under Teacher into database....	
	public Optional<String> addStudentAndTeacher(String student, String teacher) {		
		if (studentDB.containsKey(student) && teacherDB.containsKey(teacher)) {
			studentTeacherPairDB.get(teacher).add(student);
			Student s = studentDB.get(student); Teacher t = teacherDB.get(teacher);
			studentTeacherDB.get(t).add(s); 
			String str = "Student '" + student + "' is added successfuly under Teacher '" + teacher + "'!!!!"; 
			return Optional.of(str);
		}
		return Optional.empty();		 
	}

	// getting student by name...
	public Optional<Student> getStudentByName(String name) {
		if (studentDB.containsKey(name)) {
			Student student = studentDB.get(name);
			return Optional.of(student);
		}
		return Optional.empty();
	}

	// getting techer by name...	
	public Optional<Teacher> getTeacherByName(String name) {
		if (teacherDB.containsKey(name)) {
			Teacher teacher = teacherDB.get(name);
			return Optional.of(teacher);
		}
		return Optional.empty();
	}
	
	// getting all students under the particular teacher.....
	public Optional<List<String>> getStudentsByTeacherName(String teacher) {
		Set<String> studentNames = studentTeacherPairDB.get(teacher);
		if (studentNames.size() == 0) {
			return Optional.empty();
		}
		return Optional.of(new ArrayList<>(studentNames));
	}

	public Optional<String> deleteTeacherByName(String teacher) {
		if (!teacherDB.containsKey(teacher)) {
			return Optional.empty();
		}
		teacherDB.remove(teacher);
		return Optional.of("Deleted");
	}

	public void deleteAllTeachers() {
		teacherDB.clear();
	}

	public Optional<List<String>> getAllStudents() {
		if (studentDB.size() != 0) {
			Set<String> students = studentDB.keySet();
			return Optional.of(new ArrayList<>(students));
		}
		return Optional.empty();
	}

}
