package com.klef.jfsd.springboot.controller;


import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.GradeProject;
import com.klef.jfsd.springboot.model.Media;
import com.klef.jfsd.springboot.model.PortfolioData;
import com.klef.jfsd.springboot.model.PortfolioRequest;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.ProjectDTO;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.service.StudentService;

@RestController
public class StudentController
{
	@Autowired
	private StudentService studentService;

	

	
	@PostMapping("/checkstudentlogin")
    public Student checkFacultyLogin(@RequestBody Map<String, String> formData) {
        String email = formData.get("email");
        String password = formData.get("password");
        
        
		return studentService.checkStudentLogin(email, password);
	}
	
	@GetMapping("/sprojectcount")
	public long count(@RequestParam int studentId)
	{
		return studentService.projectcount(studentId);
	}
	
	 @PostMapping("/createproject")
	    public String createProject(
	            @RequestParam("studentId") int studentId,
	            @RequestParam("title") String title,
	            @RequestParam("description") String description,
	            @RequestParam("technologiesUsed") String technologiesUsed,
	            @RequestParam("image") MultipartFile image,
	            @RequestParam("file") MultipartFile file) throws Exception {

		   Blob fileBlob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
	        Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(image.getBytes());


	        Project project = new Project();
	        project.setTitle(title);
	        project.setDescription(description);
	        project.setTechnologiesUsed(technologiesUsed);
	        project.setPhase(Project.ProjectPhase.NOT_STARTED);  
	        project.setImage(imageBlob);
	        project.setFile(fileBlob);
	        project.setStudentId(studentId);

	        // Save project via the service
	        return studentService.createProject(project);
	    }

	 @PostMapping("/addmedia")
	    public String addMedia(
	            @RequestParam Project project,
	            @RequestParam String type,
	            @RequestParam MultipartFile media) throws Exception {

		   Blob mediaBlob = new javax.sql.rowset.serial.SerialBlob(media.getBytes());
	    

	        Media mediaobj = new Media();
	        
	        mediaobj.setProject(project);
	        mediaobj.setMediaType(type);
	        mediaobj.setMedia(mediaBlob);
	   
	        return studentService.addMedia(mediaobj);
	    }
	 @GetMapping("displaymedia")
	 public ResponseEntity<?> displaymedia(@RequestParam("id") int id) throws Exception {
	     Media media = studentService.viewMediaByID(id);
	     byte[] fileBytes = media.getMedia().getBytes(1, (int) media.getMedia().length());
	     
	     
	     String mediaType = media.getMediaType();
	     
	     if ("Image".equalsIgnoreCase(mediaType)) {
	         return ResponseEntity.ok()
	                 .contentType(MediaType.IMAGE_JPEG)
	                 .body(fileBytes);
	     } else if ("pdf".equalsIgnoreCase(mediaType)) {
	         return ResponseEntity.ok()
	                 .contentType(MediaType.APPLICATION_PDF)
	                 .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"project.pdf\"")
	                 .body(fileBytes);
	     } else if ("Zip".equalsIgnoreCase(mediaType)) {
	         return ResponseEntity.ok()
	        		 .contentType(MediaType.APPLICATION_OCTET_STREAM)
	        		 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"project.zip\"")
	        		 .body(fileBytes);
	     } else if ("Video".equalsIgnoreCase(mediaType)) {
	         return ResponseEntity.ok()
	                 .contentType(MediaType.parseMediaType("video/mp4"))
	                 .header("Content-Disposition", "inline; filename=\"video.mp4\"")
	                 .body(fileBytes);
	     } else if ("Text".equalsIgnoreCase(mediaType)) {
	         return ResponseEntity.ok()
	                 .contentType(MediaType.TEXT_PLAIN)
	                 .header("Content-Disposition", "inline; filename=\"file.txt\"")
	                 .body(fileBytes);
	     } else {
	       
	         return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	                 .body("Error: Unsupported media type.");
	     }
	 }



	 
    @GetMapping("viewallprojects")
	public List<ProjectDTO> viewallprojects(@RequestParam("studentId") int studentId)
	{
		return studentService.viewAllProjects(studentId);
	}

    @GetMapping("displayproject")
    public ProjectDTO displayProject(@RequestParam int projectId) {
        return studentService.viewProjectById(projectId); 
    }

    @GetMapping("/trackproject")
    public ProjectDTO trackProject(@RequestParam int projectId) {
        // Call the service to get the project data
        return studentService.trackProjectByID(projectId);
    }
    


    @GetMapping("displayprojectimage")
    public ResponseEntity<byte[]> displayProjectImage(@RequestParam int projectId) throws Exception {
        Project project = studentService.viewProjectByID(projectId); 
        byte[] image = project.getImage().getBytes(1, (int) project.getImage().length());

        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_PNG)
                             .body(image);
    }

    @GetMapping("displayprojectfile")
    public ResponseEntity<byte[]> displayProjectText(@RequestParam int projectId) throws Exception {
        Project project = studentService.viewProjectByID(projectId); 
        byte[] text = project.getFile().getBytes(1, (int) project.getFile().length());

        return ResponseEntity.ok()
                             .contentType(MediaType.TEXT_PLAIN)
                             .body(text);
    }
   
    @DeleteMapping("deleteproject")
    public String deleteProject(@RequestParam("id") int projectId) {
        return studentService.deleteProject(projectId); 
    }

   
    @PutMapping("updateproject")
    public String updateProject( @RequestBody ProjectDTO p) {
        return studentService.updateProject(p); 
    }

     
    
    @PostMapping("/createportfolio")
    public String createPortfolio(@RequestBody PortfolioRequest portfolioData) {
        try {
//            System.out.println("Certifications Student ID: " + portfolioData.getCertifications().get(0).getStudentId());
//            System.out.println("Education Student ID: " + portfolioData.getEducation().get(0).getStudentId());
//            System.out.println("Internships Student ID: " + portfolioData.getInternships().get(0).getStudentId());
//            System.out.println("Portfolio Student ID: " + portfolioData.getPortfolios().get(0).getStudentId());
//            System.out.println("Testimonials Student ID: " + portfolioData.getTestimonials().get(0).getStudentId());
//            
            
            System.out.println("Skills Student ID: " + portfolioData.getSkills().get(0).getStudentId());
            System.out.println("Project IDs: " + portfolioData.getProjectIds()); 
           

            // Call the service to save the portfolio data, including project IDs
            studentService.createPortfolio(
                portfolioData.getCertifications(),
                portfolioData.getEducation(),
                portfolioData.getInternships(),
                portfolioData.getPortfolios(),
                portfolioData.getSkills(),
                portfolioData.getTestimonials(),
                portfolioData.getProjectIds() // Pass only project IDs here for saving
            );

            return "Your Portfolio is ready";
        } catch (Exception e) {
            return "Error saving data: " + e.getMessage();
        }
    }

    
    @GetMapping("displayportfolio")
    public PortfolioData getPortfolioDataByStudentId(@RequestParam("studentId")  int studentId)
    {
    	return studentService.getPortfolioDataByStudentId(studentId);
    }

    @PutMapping("/updateportfolio")
    public String updatePortfolio(@RequestParam("sid") int sid, @RequestBody PortfolioData portfiloData) {
        try {
        		
            studentService.updatePortfolio(
            		sid, 
            	    portfiloData.getCertifications(),
            		portfiloData.getEducation(),
            		portfiloData.getInternships(),
            		portfiloData.getPortfolios(),
            		portfiloData.getSkills(),
            		portfiloData.getTestimonials()
            );
            return "Your Portfolio is ready";
        } catch (Exception e) {
            return "Error saving data: " + e.getMessage();
        }
    }
    
	@GetMapping("viewmyfeedback")
	public List<ProjectFeedback> viewmyfeedback(@RequestParam int sid)
	{
		return studentService.viewmyfeedback(sid);
	}
}
