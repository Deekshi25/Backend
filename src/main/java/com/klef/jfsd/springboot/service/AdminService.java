package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Student;

public interface AdminService 
{
	public Admin checkadminlogin(String username,String password);
	public String addStudent(Student s);
	public String updateStudent(Student s);
	public List<Student> viewAllStudents();
	public Student displayStudentByID(int sid);
	public String deleteStudent(int sid);
}
