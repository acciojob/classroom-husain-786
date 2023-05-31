package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRepository {
	private List<Student> studentList =new ArrayList<>();
	private   List<Teacher> teacherList =new ArrayList<>();

	private HashMap<Teacher,List<Student>> teacherWithStudentMap=new HashMap<>();
	public void addStudent(Student student) {
		studentList.add(student);
	}

	public void addTeacher(Teacher teacher) {
		teacherList.add(teacher);
	}

	public void addStudentTeacherPair(String student, String teacher) {
		Teacher tempTeacher=getTeacherByName(teacher);
		Student student1=getStudentByName(student);
		List<Student> tempList=teacherWithStudentMap.getOrDefault(tempTeacher, new ArrayList<Student>());
		tempList.add(student1);
		teacherWithStudentMap.put(tempTeacher,tempList);
	}

	public Student getStudentByName(String name) {
		for(Student student:studentList){
			if(student.getName().equals(name))return student;
		}
		return new Student();
	}

	public Teacher getTeacherByName(String name) {
		for (Teacher teacher:teacherList){
			if(teacher.getName().equals(name))return teacher;
		}
		return new Teacher();
	}

	public List<String> getStudentsByTeacherName(String teacher) {
		List<String> allStudentByTeacher=new ArrayList<>();
		Teacher teacher1=getTeacherByName(teacher);
		List<Student> studentList=teacherWithStudentMap.getOrDefault(teacher1,new ArrayList<>());
		for(Student student:studentList){
			allStudentByTeacher.add(student.getName());
		}
		return allStudentByTeacher;
	}

	public List<String> getAllStudents() {
		List<String> allStudent=new ArrayList<>();
		for (Student student:studentList){
			allStudent.add(student.getName());
		}
		return allStudent;
	}

	public void deleteTeacherByName(String teacher) {
		Teacher teacher1=getTeacherByName(teacher);
		List<Student> listOfRemoveStudent=teacherWithStudentMap.getOrDefault(teacher1,new ArrayList<>());
		for(Student student:listOfRemoveStudent){
			studentList.remove(student);
		}
		teacherWithStudentMap.remove(teacher1);
		teacherList.remove(teacher1);
	}

	public void deleteAllTeachers() {
		for(Teacher teacher: new ArrayList<>(teacherList)) deleteTeacherByName(teacher.getName());
		teacherList.clear();
		teacherWithStudentMap.clear();
	}
}