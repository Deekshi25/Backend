package com.klef.jfsd.springboot.controller;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController
{
	@Autowired
	private StudentService studentService;
	private String email;
	
	
	
	@PostMapping("/checkstudentlogin")
	public Student checkStudentLogin(@RequestParam("email") String email , @RequestParam("password") String password)
	{
			
		Student student = studentService.checkStudentLogin(email, password);
		return student;
		
	}

	
    @PostMapping("/createproject")
    public String createProject(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("pdf") MultipartFile pdf,
            @RequestParam("zip") MultipartFile zip,
            @RequestParam("file") MultipartFile file) throws Exception {

        Blob fileBlob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
        Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(image.getBytes());
        Blob pdfBlob = new javax.sql.rowset.serial.SerialBlob(pdf.getBytes());
        Blob zipBlob = new javax.sql.rowset.serial.SerialBlob(zip.getBytes());

        
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);

        project.setPhase(Project.ProjectPhase.NOT_STARTED); 
        project.setImage(imageBlob);  
        project.setPdf(pdfBlob);  
        project.setZip(zipBlob);   
        project.setFile(fileBlob); 
        
        return studentService.createProject(project);
    }
	
    @GetMapping("viewallprojects")
	public List<Project> viewallprojects()
	{
		return studentService.viewAllProjects();
	}

    @GetMapping("displayprojectbyid")
	public Project displayprojectbyid(@RequestParam("id")int pid)
	{
		return studentService.viewProjectByID(pid);
	}
    
    @GetMapping("displayprojectimage")
    public ResponseEntity<byte[]> displayProjectImage(@RequestParam int projectId) throws Exception {
        Project project = studentService.viewProjectByID(projectId);
        byte[] image = project.getImage().getBytes(1, (int) project.getImage().length());

        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_PNG)
                             .body(image);
    }

    
    
	   
    @GetMapping("displayprojectpdf")
    public ResponseEntity<byte[]> displayProjectPdf(@RequestParam int projectId) throws Exception {
        Project project = studentService.viewProjectByID(projectId);
        byte[] pdf = project.getPdf().getBytes(1, (int) project.getPdf().length());

        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_PDF)
                             .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"project.pdf\"")
                             .body(pdf);
    }


    @GetMapping("displayprojectzip")
    public ResponseEntity<byte[]> displayProjectZip(@RequestParam int projectId) throws Exception {
        Project project = studentService.viewProjectByID(projectId);
        byte[] zip = project.getZip().getBytes(1, (int) project.getZip().length());

        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"project.zip\"")
                             .body(zip);
    }

    @GetMapping("displayprojectfile")
    public ResponseEntity<byte[]> displayProjectText(@RequestParam int projectId) throws Exception {
        Project project = studentService.viewProjectByID(projectId);
        byte[] text = project.getFile().getBytes(1, (int) project.getFile().length());

        return ResponseEntity.ok()
                             .contentType(MediaType.TEXT_PLAIN)
                             .body(text);
    }

}
