package com.klef.jfsd.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Certifications;
import com.klef.jfsd.springboot.model.Education;
import com.klef.jfsd.springboot.model.Internships;
import com.klef.jfsd.springboot.model.Media;
import com.klef.jfsd.springboot.model.Portfolio;
import com.klef.jfsd.springboot.model.PortfolioData;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Project.ProjectPhase;
import com.klef.jfsd.springboot.model.ProjectDTO;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Skills;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.model.Testimonials;
import com.klef.jfsd.springboot.repository.CertificationsRepository;
import com.klef.jfsd.springboot.repository.EducationRepository;
import com.klef.jfsd.springboot.repository.InternshipsRepository;
import com.klef.jfsd.springboot.repository.MediaRepository;
import com.klef.jfsd.springboot.repository.PortfolioProjectsRepository;
import com.klef.jfsd.springboot.repository.PortfolioRepository;
import com.klef.jfsd.springboot.repository.ProjectFeedbackRepository;
import com.klef.jfsd.springboot.repository.ProjectRepository;
import com.klef.jfsd.springboot.repository.SkillsRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;
import com.klef.jfsd.springboot.repository.TestimonialsRepository;

@Service
public class StudentServiceImpl implements StudentService
{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
    private CertificationsRepository certificationRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private InternshipsRepository internshipRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private SkillsRepository skillRepository;
    @Autowired
    private TestimonialsRepository testimonialRepository;
    @Autowired
    private PortfolioProjectsRepository portfolioProjectsRepository;
    
    @Autowired
    private MediaRepository mediaRepository;
	
    @Autowired
    private ProjectFeedbackRepository projectFeedbackRepository;
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
	        Optional<Project> projectOpt = projectRepository.findById(pid);
	        if(projectOpt.isPresent())
	        {
	            Project project = projectOpt.get();	         
	                return project;
	        }
	        else
	        	return null;
	    }

	   
	  // Fetch all projects for a specific student and map them to DTOs
	    public List<ProjectDTO> viewAllProjects(int studentId) {
	        List<Project> projects = projectRepository.findByStudentId(studentId);
	        return projects.stream()
	                       .map(this::toDTO)
	                       .collect(Collectors.toList());
	    }

	    // Fetch a single project by ID and map it to DTO
	    public ProjectDTO viewProjectById(int projectId) {
	        Optional<Project> optionalProject = projectRepository.findById(projectId);
	        if (optionalProject.isPresent()) {
	            return toDTO(optionalProject.get());
	        }
	        throw new RuntimeException("Project not found with ID: " + projectId);
	    }

	    public ProjectDTO toDTO(Project project) {
	        ProjectDTO projectDTO = new ProjectDTO();

	        // Set basic project info
	        projectDTO.setProjectId(project.getProjectId());
	        projectDTO.setTitle(project.getTitle());
	        projectDTO.setDescription(project.getDescription());
	        projectDTO.setPhase(project.getPhase().getShortTitle());

	        // Set the phase percentages as a map of phase names to percentages
	        Map<String, Integer> phasePercentages = new HashMap<>();
	        for (Project.ProjectPhase phase : Project.ProjectPhase.values()) {
	            phasePercentages.put(phase.name(), project.getPhasePercentages().get(phase));
	        }
	        projectDTO.setPhasePercentages(phasePercentages);

	        // Set the phase descriptions and grades using the ElementCollection maps
	        Map<String, String> phaseDescriptions = project.getPhaseDescriptions().entrySet().stream()
	            .collect(Collectors.toMap(entry -> entry.getKey().name(), Map.Entry::getValue));
	        projectDTO.setPhaseDescriptions(phaseDescriptions);

	        Map<String, String> phaseGrades = project.getPhaseGrades().entrySet().stream()
	            .collect(Collectors.toMap(entry -> entry.getKey().name(), Map.Entry::getValue));
	        projectDTO.setPhaseGrades(phaseGrades);

	        // Set additional project info
	        projectDTO.setCheckStatus(project.isCheckStatus());
	        projectDTO.setProjectLink(project.getProjectLink());
	        projectDTO.setTechnologiesUsed(project.getTechnologiesUsed());
	        projectDTO.setStudentId(project.getStudentId());

	        return projectDTO;
	    }

	

	    @Override
	    public ProjectDTO trackProjectByID(int pid) {
	        Optional<Project> projectOpt = projectRepository.findById(pid);
	        if (projectOpt.isPresent()) {
	            Project project = projectOpt.get();

	            // Create a ProjectDTO object to return
	            ProjectDTO projectDTO = new ProjectDTO();
	            projectDTO.setProjectId(project.getProjectId());
	            projectDTO.setTitle(project.getTitle());

	            // Set Phase Descriptions, Grades, and Percentages
	            Map<String, String> phaseDescriptions = new HashMap<>();
	            Map<String, String> phaseGrades = new HashMap<>();
	            Map<String, Integer> phasePercentages = new HashMap<>();

	            for (Project.ProjectPhase phase : Project.ProjectPhase.values()) {
	                // Using the project object to get the correct data
	                phaseDescriptions.put(phase.name(), project.getPhaseDescriptions().get(phase));
	                phaseGrades.put(phase.name(), project.getPhaseGrades().get(phase));
	                phasePercentages.put(phase.name(), project.getPhasePercentages().get(phase));
	            }

	            projectDTO.setPhaseDescriptions(phaseDescriptions);
	            projectDTO.setPhaseGrades(phaseGrades);
	            projectDTO.setPhasePercentages(phasePercentages);

	            return projectDTO;
	        } else {
	            // If no project is found, return null or handle as needed
	            return null;
	        }
	    }


	    // Delete a project - checks if the project belongs to the student before deletion
	    @Override
	    public String deleteProject(int id) {
	        Optional<Project> p = projectRepository.findById(id);

	        if (p.isPresent()) {
	            Project project = p.get();
	            projectRepository.delete(project);
	         
	              
	    }
	        return "Project Deleted Successfully";
	    }
	    
	    // Update a project - checks if the project belongs to the student before updating
	    @Override
	    public String updateProject(ProjectDTO p) {
	        Optional<Project> obj = projectRepository.findById(p.getProjectId());
	        String msg;

	        if (obj.isPresent()) {
	            Project project = obj.get();

	            // Update the title, description, and checkStatus fields
	            project.setTitle(p.getTitle());
	            project.setDescription(p.getDescription());
	            project.setCheckStatus(p.isCheckStatus());

	            // Convert String to ProjectPhase and update the phase field
	            try {
	                ProjectPhase phaseEnum = ProjectPhase.valueOf(p.getPhase());
	                project.setPhase(phaseEnum);
	            } catch (IllegalArgumentException e) {
	                return "Invalid project phase provided: " + p.getPhase();
	            }

	            // Update phase descriptions
	            Map<String, String> phaseDescriptions = p.getPhaseDescriptions();
	            if (phaseDescriptions != null) {
	                Map<ProjectPhase, String> updatedPhaseDescriptions = new HashMap<>();

	                for (Map.Entry<String, String> entry : phaseDescriptions.entrySet()) {
	                    try {
	                        // Convert the String phase to ProjectPhase enum
	                        ProjectPhase phaseEnum = ProjectPhase.valueOf(entry.getKey());
	                        updatedPhaseDescriptions.put(phaseEnum, entry.getValue());
	                    } catch (IllegalArgumentException e) {
	                        return "Invalid phase in phase descriptions: " + entry.getKey();
	                    }
	                }

	                // Update the phaseDescriptions in the project
	                project.setPhaseDescriptions(updatedPhaseDescriptions);
	            }

	            // Save the updated project back to the database
	            projectRepository.save(project);
	            msg = "Project and Descriptions Updated Successfully";
	        } else {
	            msg = "Project ID Not Found";
	        }

	        return msg;
	    }


	    

	    public void createPortfolio(
	            List<Certifications> certifications,
	            List<Education> education,
	            List<Internships> internships,
	            List<Portfolio> portfolios,
	            List<Skills> skills,
	            List<Testimonials> testimonials,
	            List<Integer> projectIds) {

	        // Save certifications
	        if (certifications != null && !certifications.isEmpty()) {
	            certificationRepository.saveAll(certifications);
	        }

	        // Save education details
	        if (education != null && !education.isEmpty()) {
	            educationRepository.saveAll(education);
	        }

	        // Save internships
	        if (internships != null && !internships.isEmpty()) {
	            internshipRepository.saveAll(internships);
	        }

	        // Save portfolios (without project associations initially)
	        if (portfolios != null && !portfolios.isEmpty()) {
	            portfolioRepository.saveAll(portfolios);
	        }

	        // Save skills
	        if (skills != null && !skills.isEmpty()) {
	            skillRepository.saveAll(skills);
	        }

	        // Save testimonials
	        if (testimonials != null && !testimonials.isEmpty()) {
	            testimonialRepository.saveAll(testimonials);
	        }

	        // Update a specific portfolio with project IDs
	    
	            // Convert project IDs into a comma-separated string
	            String projectIdsString = projectIds.stream()
	                    .map(String::valueOf) // Convert each project ID to a String
	                    .collect(Collectors.joining(",")); // Join them with commas

	            System.out.println("Project IDs as String: " + projectIdsString);

	            System.out.println(portfolios.get(0).getStudentId());
	            Portfolio portfolioToUpdate = portfolios.get(0); // Adjust logic for specific portfolio
	            portfolioToUpdate.setProjectIds(projectIdsString);

	            // Save the updated portfolio
	            portfolioRepository.save(portfolioToUpdate);
	        
	    }
	    public PortfolioData getPortfolioDataByStudentId(int studentId) {
	        PortfolioData data = new PortfolioData();

	        // Fetch all the basic details
	        data.setCertifications(certificationRepository.findByStudentId(studentId));
	        data.setEducation(educationRepository.findByStudentId(studentId));
	        data.setInternships(internshipRepository.findByStudentId(studentId));
	        data.setSkills(skillRepository.findByStudentId(studentId));
	        data.setTestimonials(testimonialRepository.findByStudentId(studentId));
	        data.setPortfolios(portfolioRepository.findByStudentId(studentId));

	        // Collect project IDs from all portfolios
	        List<String> projectIdsStrings = data.getPortfolios().stream()
	                .map(Portfolio::getProjectIds) // Extract the project IDs string
	                .filter(ids -> ids != null && !ids.trim().isEmpty()) // Exclude null/empty strings
	                .collect(Collectors.toList());

	        // Flatten all project IDs into a single list
	        List<Integer> projectIds = projectIdsStrings.stream()
	                .flatMap(ids -> Arrays.stream(ids.split(","))) // Split by commas
	                .map(String::trim) // Trim spaces
	                .map(Integer::parseInt) // Convert to Integer
	                .distinct() // Remove duplicates
	                .collect(Collectors.toList());

	        // Fetch all projects based on the collected project IDs
	        List<Project> projects = projectRepository.findAllById(projectIds);

	        // Set the fetched projects in PortfolioData
	        data.setProjects(projects);

	        return data;
	    }


	    public void updatePortfolio(
	    	    int studentId, 
	    	    List<Certifications> certifications, 
	    	    List<Education> education, 
	    	    List<Internships> internships, 
	    	    List<Portfolio> portfolios, 
	    	    List<Skills> skills, 
	    	    List<Testimonials> testimonials
	    	) {
	    	    // Update Certifications
	    	    if (certifications != null && !certifications.isEmpty()) {
	    	        List<Certifications> existingCertifications = certificationRepository.findByStudentId(studentId);
	    	        certificationRepository.deleteAll(existingCertifications); // Clear old records
	    	        certificationRepository.saveAll(certifications); // Save new records
	    	    }

	    	    // Update Education
	    	    if (education != null && !education.isEmpty()) {
	    	        List<Education> existingEducation = educationRepository.findByStudentId(studentId);
	    	        educationRepository.deleteAll(existingEducation); // Clear old records
	    	        educationRepository.saveAll(education); // Save new records
	    	    }

	    	    // Update Internships
	    	    if (internships != null && !internships.isEmpty()) {
	    	        List<Internships> existingInternships = internshipRepository.findByStudentId(studentId);
	    	        internshipRepository.deleteAll(existingInternships); // Clear old records
	    	        internshipRepository.saveAll(internships); // Save new records
	    	    }

	    	    // Update Portfolios
	    	    if (portfolios != null && !portfolios.isEmpty()) {
	    	        List<Portfolio> existingPortfolios = portfolioRepository.findByStudentId(studentId);
	    	        portfolioRepository.deleteAll(existingPortfolios); // Clear old records
	    	        portfolioRepository.saveAll(portfolios); // Save new records
	    	    }

	    	    // Update Skills
	    	    if (skills != null && !skills.isEmpty()) {
	    	        List<Skills> existingSkills = skillRepository.findByStudentId(studentId);
	    	        skillRepository.deleteAll(existingSkills); // Clear old records
	    	        skillRepository.saveAll(skills); // Save new records
	    	    }

	    	    // Update Testimonials
	    	    if (testimonials != null && !testimonials.isEmpty()) {
	    	        List<Testimonials> existingTestimonials = testimonialRepository.findByStudentId(studentId);
	    	        testimonialRepository.deleteAll(existingTestimonials); // Clear old records
	    	        testimonialRepository.saveAll(testimonials); // Save new records
	    	    }
	    	}



	    
		@Override
		public String addMedia(Media media) {
			mediaRepository.save(media);
			return "Media Uploaded Successfully";
		}



		@Override
		public Media viewMediaByID(int mid) {
			return mediaRepository.findById(mid).get();
		}


		@Override
		public List<ProjectFeedback> viewmyfeedback(int sid) {
		    // projects for the student
		    List<Project> projects = projectRepository.findByStudentId(sid);

		 
		    // feedbacks for these projects
		    List<ProjectFeedback> feedbackList = new ArrayList<>();
		    for (Project project : projects) {
		        List<ProjectFeedback> projectFeedbacks = projectFeedbackRepository.findByProjectid(project.getProjectId());
		        feedbackList.addAll(projectFeedbacks);
		    }
		   
		    return feedbackList;
		}

		public long projectcount(int sid)
		{
			return projectRepository.countByStudentId(sid);
		}
		
	
	    }
