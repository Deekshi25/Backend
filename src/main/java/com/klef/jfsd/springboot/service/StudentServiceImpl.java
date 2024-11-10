package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Student;

import com.klef.jfsd.springboot.repository.ProjectRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public Student checkStudentLogin(String email, String password) {
		Student student = studentRepository.checkStudentLogin(email, password);
		return student;
	}

	
	@Override
	public String createProject(Project p) {
		projectRepository.save(p);
		return "Project Created Successfully";
	}

	@Override
	public Project viewProjectByID(int pid) {
		return projectRepository.findById(pid).get();	
	}

	@Override
	public List<Project> viewAllProjects() {
		
		return projectRepository.findAll();
		
	}
}
