package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Milestone;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.MilestoneRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MilestoneRepository milestoneRepository;
	
	@Override
	public Student checkStudentLogin(String email, String password) {
		Student student = studentRepository.checkStudentLogin(email, password);
		return student;
	}

	@Override
	public String addMilestone(Milestone milestone)
	{
		milestoneRepository.save(milestone);
		return "Milestone Added Successfully";
	}

	@Override
	public String updateMilestone(Milestone milestone) {
		
		milestoneRepository.findByName(milestone.getName());
		
		Milestone m = new Milestone();

		m.setDescription(milestone.getDescription());
		m.setDueDate(milestone.getDueDate());
		m.setCompletedAt(milestone.getCompletedAt());	
		return "Milestone Updated Successfully";
	}
	
	@Override
	public String updateMilestoneStatus(Milestone milestone) {
		
		milestoneRepository.findByName(milestone.getName());
		
		Milestone m = new Milestone();
		m.setStatus(milestone.getStatus());

	
		return "Milestone Status Updated Successfully";
	}

	@Override
	public String deleteMilestone(int id) {
		
		return "Milestone Deleted Successfully";
	}

	@Override
	public List<Milestone> viewAllMilestones() {
	
		return milestoneRepository.findAll();
	}

	@Override
	public String viewMilestone(String name) 
	{
		return (String) milestoneRepository.findByName(name).toString();
		
	}

}
