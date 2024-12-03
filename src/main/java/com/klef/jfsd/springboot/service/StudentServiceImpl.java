package com.klef.jfsd.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	   
	    @Override
	    public List<Project> viewAllProjects(int studentId) {
	        // Fetch projects that belong to the specific student
	        return projectRepository.findByStudentId(studentId);
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
	    public String updateProject(Project p) {
	        Optional<Project> obj = projectRepository.findById(p.getProjectId());
	        String msg = null;

	        if (obj.isPresent()) {
	            Project project = obj.get();

	            // Update only the title, description, and phase fields
	            project.setTitle(p.getTitle());
	            project.setDescription(p.getDescription());
	            project.setPhase(p.getPhase());
	            project.setCheckpoint(p.getPercentage());
	            project.setCheckStatus(p.isCheckStatus());;

	            projectRepository.save(project);
	            msg = "Project Updated Successfully";
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

		    System.out.println(projects.get(0).getStudentId());
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
