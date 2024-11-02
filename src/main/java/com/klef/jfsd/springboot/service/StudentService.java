package com.klef.jfsd.springboot.service;


import java.util.List;

import com.klef.jfsd.springboot.model.Milestone;
import com.klef.jfsd.springboot.model.Student;


public interface StudentService
{
   public Student checkStudentLogin(String email ,String password);
   public String addMilestone(Milestone m);
	public String updateMilestone(Milestone m);
	public String deleteMilestone(int id);
	public List<Milestone> viewAllMilestones();
	public String viewMilestone(String name);
	String updateMilestoneStatus(Milestone m);
}
