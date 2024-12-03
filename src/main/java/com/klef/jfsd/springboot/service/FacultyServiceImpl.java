package com.klef.jfsd.springboot.service;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Project.ProjectCheckpoint;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.FacultyRepository;
import com.klef.jfsd.springboot.repository.FacultyStudentMapperRepository;
import com.klef.jfsd.springboot.repository.ProjectFeedbackRepository;
import com.klef.jfsd.springboot.repository.ProjectRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service
public class FacultyServiceImpl  implements  FacultyService
{
	@Autowired
    private FacultyRepository facultyRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectFeedbackRepository projectFeedbackRepository;
	
	@Autowired
	private FacultyStudentMapperRepository facultyStudentMapperRepository;
	
	private ReportGenerator reportGenerator;
	@Override
	public Faculty checkfacultylogin(String username, String password)
	{
		Faculty faculty = facultyRepository.findByUsernameAndPassword(username, password);
		return faculty;

	}

	@Override
	public List<Student> viewStudentsByFaculty(int facultyId) {
	  
	    List<Student> mappedStudents = facultyStudentMapperRepository.findStudentsByFacultyId(facultyId);
	    return mappedStudents;
	}

	
	@Override
	public List<Project> viewProjectsByFaculty(int facultyId) {
		
	    List<Student> mappedStudents = facultyStudentMapperRepository.findStudentsByFacultyId(facultyId);
	    
	    
	    return projectRepository.findAll().stream()
	            .filter(project -> mappedStudents.stream()
	                    .anyMatch(student -> student.getId() == project.getStudentId()))
	            .collect(Collectors.toList());
	}
	@Override
	public String gradeProject(ProjectFeedback pf) {
		projectFeedbackRepository.save(pf);
		return "Feedback is given";
	}
	
	 public Blob generateReport(int projectId) {
	        Project project = projectRepository.findById(projectId).orElse(null);

	        if (project == null ) {
	            return null;
	        }
	   
	        return reportGenerator.generatePDFReport(projectId);
	    }
	 
	@Override
	public List<ProjectFeedback> fviewmyfeedback(int fid) 
	{
		return projectFeedbackRepository.findByFacultyId(fid);
	}

	
	

}
