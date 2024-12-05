package com.klef.jfsd.springboot.service;

import java.sql.Blob;
import java.util.List;

import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.GradeProject;
import com.klef.jfsd.springboot.model.PortfolioFeedback;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;

public interface FacultyService 
{
	public Faculty checkfacultylogin(String username,String password);
	
	public String gradeProject(GradeProject pfDTO);
	
	public String reviewPortfolio(PortfolioFeedback portfolioFeedback);
	
	public List<ProjectFeedback> fviewmyfeedback(int fid);

	List<Project> viewProjectsByFaculty(int facultyId);

	List<Student> viewStudentsByFaculty(int facultyId);
	
    public Blob generateReport(int projectId);
    
    public String allowProject(int projectId);
	 
	 


}
