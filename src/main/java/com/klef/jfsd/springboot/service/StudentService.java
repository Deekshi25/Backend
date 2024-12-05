package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Certifications;
import com.klef.jfsd.springboot.model.Education;
import com.klef.jfsd.springboot.model.Internships;
import com.klef.jfsd.springboot.model.Media;
import com.klef.jfsd.springboot.model.Portfolio;
import com.klef.jfsd.springboot.model.PortfolioData;
import com.klef.jfsd.springboot.model.PortfolioProjects;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.ProjectDTO;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Skills;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.model.Testimonials;


public interface StudentService
{
    public Student checkStudentLogin(String email ,String password);
	public String createProject(Project p);
	public ProjectDTO viewProjectById(int pid);
	public String deleteProject(int pid);
	public String updateProject( ProjectDTO p);
	public List<ProjectDTO> viewAllProjects(int sid);
	  public void createPortfolio(
	            List<Certifications> certifications,
	            List<Education> education,
	            List<Internships> internships,
	            List<Portfolio> portfolios,
	            List<Skills> skills,
	            List<Testimonials> testimonials,
	            List<Integer> projectIds);
	 public void updatePortfolio(
	    	    int studentId, 
	    	    List<Certifications> certifications, 
	    	    List<Education> education, 
	    	    List<Internships> internships, 
	    	    List<Portfolio> portfolios, 
	    	    List<Skills> skills, 
	    	    List<Testimonials> testimonials
	    	) ;
	 public PortfolioData getPortfolioDataByStudentId(int studentId);
	 public String addMedia(Media media);
	 public Media viewMediaByID(int mid);
	 public long projectcount(int sid);
	public List<ProjectFeedback> viewmyfeedback(int sid);
			
	 public ProjectDTO toDTO(Project project) ;
	 
	public Project viewProjectByID(int projectId);
	public ProjectDTO trackProjectByID(int projectId);
	
//
	       

}
