package com.klef.jfsd.springboot.service;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.GradeProject;
import com.klef.jfsd.springboot.model.PortfolioFeedback;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Project.ProjectPhase;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.FacultyRepository;
import com.klef.jfsd.springboot.repository.FacultyStudentMapperRepository;
import com.klef.jfsd.springboot.repository.PortfoliFeedbackRepository;
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
	private PortfoliFeedbackRepository portfoliFeedbackRepository;
	
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
	
	
	public String allowProject(int projectId) {
      
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        
        if (!projectOptional.isPresent()) {
            return "Project not found";
        }
   
        Project project = projectOptional.get();
       
        ProjectPhase currentPhase = project.getPhase();
        ProjectPhase nextPhase = getNextPhase(currentPhase);

        if (currentPhase == ProjectPhase.NOT_STARTED) {
        	project.setCheckStatus(false);
            project.setPhase(nextPhase); // Move to next phase
            projectRepository.save(project); // Save updated project phase
        }

        return "Accepted the Project";
    }


	public String gradeProject(GradeProject pfDTO) {
        Optional<Project> projectOptional = projectRepository.findById(pfDTO.getProjectId());
        Optional<Faculty> facultyOptional = facultyRepository.findById(pfDTO.getFacultyId());

        if (!projectOptional.isPresent()) {
            return "Project not found";
        }
        if (!facultyOptional.isPresent()) {
            return "Faculty not found";
        }

        Project project = projectOptional.get();
        Faculty faculty = facultyOptional.get();

        project.setCheckStatus(false);
        ProjectPhase currentPhase = project.getPhase();
        ProjectPhase nextPhase = getNextPhase(currentPhase);

        
        String grade = pfDTO.getGrade();
        if (grade != null) {
          
            project.getPhaseGrades().put(currentPhase, grade);  
            projectRepository.save(project);
        }

        if(pfDTO.getFeedback()!=null)
        {
        	ProjectFeedback feedback = new ProjectFeedback();
        
        feedback.setProjectid(pfDTO.getProjectId());
        feedback.setFacultyId(pfDTO.getFacultyId());
        feedback.setComments(pfDTO.getFeedback());
        feedback.setDateSubmitted(new Date());


        projectFeedbackRepository.save(feedback); 
        }
     
        if (grade != null) {
            project.setPhase(nextPhase); 
            projectRepository.save(project); 
        }

        return "Grade and feedback submitted successfully";
    }

    // Helper method to get the next phase based on current phase
    private ProjectPhase getNextPhase(ProjectPhase currentPhase) {
        switch (currentPhase) {
            case NOT_STARTED:
                return ProjectPhase.IDEA;
            case IDEA:
                return ProjectPhase.DESIGN;
            case DESIGN:
                return ProjectPhase.BUILD;
            case BUILD:
                return ProjectPhase.TESTING;
            case TESTING:
                return ProjectPhase.DEPLOYMENT;
            case DEPLOYMENT:
                return ProjectPhase.COMPLETED;
            default:
                return ProjectPhase.COMPLETED; // No next phase after COMPLETED
        }
    }




	@Override
	public String reviewPortfolio(PortfolioFeedback portfolioFeedback) {
		portfoliFeedbackRepository.save(portfolioFeedback);
		return "Portfolio reviewed Successfully";
	}
	
	

}
