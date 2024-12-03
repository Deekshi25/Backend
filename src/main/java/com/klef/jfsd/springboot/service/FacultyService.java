package com.klef.jfsd.springboot.service;

import java.sql.Blob;
import java.util.List;

import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;

public interface FacultyService 
{
	public Faculty checkfacultylogin(String username,String password);
	
//	public List<Project> viewallprojects();
	public String gradeProject(ProjectFeedback pf);
	
	public List<ProjectFeedback> fviewmyfeedback(int fid);

	List<Project> viewProjectsByFaculty(int facultyId);

	List<Student> viewStudentsByFaculty(int facultyId);
	
	 public Blob generateReport(int projectId);
	 


}
