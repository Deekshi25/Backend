package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Student;


public interface StudentService
{
    public Student checkStudentLogin(String email ,String password);
	public String createProject(Project p);
	public Project viewProjectByID(int id);
	public List<Project> viewAllProjects();
}
