package com.klef.jfsd.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.FacultyStudentMapper;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.service.AdminService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class AdminController
{
	@GetMapping("/")
	public String demo()
	{
		return "Demo";
	}
	
	
	@Autowired
	private AdminService service;
	
	@PostMapping("/checkadminlogin")
    public Admin checkAdminLogin(@RequestBody Map<String, String> formData) {
        String username = formData.get("username");
        String password = formData.get("password");
        
        
		return service.checkadminlogin(username, password);
	}
	
	@PostMapping("addstudent")
	public String addstudent(@RequestBody Student student)
	{
		return service.addStudent(student);
	}	
	
	@PostMapping("/addstudentlist")
	public ResponseEntity<String> addStudents(@RequestParam("file") MultipartFile file) {
	    try {
	        String result = service.processAndSaveCSV(file);
	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
	    }
	}
	
	@PostMapping("/addfacultylist")
	public ResponseEntity<String> addFacultyFromCSV(@RequestParam("file") MultipartFile file) {
	    try {
	        String result = service.processAndSaveFacultyCSV(file);
	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
	    }
	}


	
	
	@GetMapping("viewallstudents")
	public List<Student> viewallstudents()
	{
		return service.viewAllStudents();
	}
	
	@GetMapping("displaystudentbyid")
	public Student displaystudentbyid(@RequestParam("id")int sid)
	{
		return service.displayStudentByID(sid); 
	}
	@DeleteMapping("deletestudent")
	public String deletestudent(@RequestParam("id")int sid)
	{
		return service.deleteStudent(sid);
	}	
	@PutMapping("updatestudent")
	public String updatestudent(@RequestBody Student s)
	{
		return service.updateStudent(s);
	}
	
	@PostMapping("addfaculty")
	public String addfaculty(@RequestBody Faculty faculty)
	{
		return service.addFaculty(faculty);
	}	
	@GetMapping("viewallfaculty")
	public List<Faculty> viewallfaculty()
	{
		return service.viewAllFaculty();
	}
	
	@GetMapping("displayfacultybyid")
	public Faculty displayfacultybyid(@RequestParam("id")int fid)
	{
		return service.displayFacultyById(fid); 
	}
	@DeleteMapping("deletefaculty")
	public String deletefaculty(@RequestParam("id")int fid)
	{
		return service.deleteFaculty(fid);
	}	
	@PutMapping("updatefaculty")
	public String updatefaculty(@RequestBody Faculty f)
	{
		return service.updateFaculty(f);
	}
	

	 @GetMapping("/fstudentmapping")
	    public Map<String, Object> getFacultyStudentMappingData() {
	        Map<String, Object> response = new HashMap<>();
	        response.put("studentdata", service.viewAllStudents());
	        response.put("facultydata", service.viewAllFaculty());
	        return response;
	    }

	 @PostMapping("/fstudentmappinginsert")
	 public Map<String, String> insertFacultyStudentMapping(@RequestBody Map<String, Object> requestData) {
	     Map<String, String> response = new HashMap<>();
	     int fid = Integer.parseInt(requestData.get("fid").toString());
	     int sid = Integer.parseInt(requestData.get("sid").toString());

	     System.out.println("Value of fid: " + requestData.get("fid"));
	     System.out.println("Type of fid: " + requestData.get("fid").getClass().getName());

	     // Fetch the faculty and student objects
	     Faculty faculty = service.displayFacultyById(fid);
	     Student student = service.displayStudentByID(sid);

	     // Check if the specific faculty-student mapping exists
	     long specificMappingCount = service.checkFacultyStudentMapping(faculty, student);

	     // Check if the student is mapped to any other faculty
	     boolean isStudentMappedToOtherFaculty = service.isStudentMappedToAnyFaculty(sid, fid); // Assuming you implement this method

	     if (specificMappingCount > 0) {
	         response.put("message", "Mapping Already Done");
	     } else if (isStudentMappedToOtherFaculty) {
	         response.put("message", "This student is already mapped to another faculty.");
	     } else {
	         // Proceed with mapping
	         FacultyStudentMapper fsm = new FacultyStudentMapper();
	         fsm.setStudent(student);
	         fsm.setFaculty(faculty);

	         String result = service.facultystudentMapping(fsm);
	         response.put("message", result);
	     }
	     return response;
	 }


	    @GetMapping("/viewfstudentmapping")
	    public List<FacultyStudentMapper> viewFacultyStudentMappings() {
	        return service.viewFacultyStudentMapping();
	    }
	    
//	    @GetMapping("viewallprojectsbyfaculty")
//		public List<Project> viewallprojects()
//		{
//			return service.viewallprojects();
//		}

//		@PostMapping("gradeproject")
//		public String gradeProject(@RequestBody ProjectFeedback pf)
//		{
//			return service.gradeProject(pf);
//		}	
//	    
//		@GetMapping("viewallfeedback")
//		public List<ProjectFeedback> fviewmyfeedback(@RequestParam int fid)
//		{
//			return service.fviewmyfeedback(fid);
//		}
		
	    @GetMapping("viewfeedback")
		public List<ProjectFeedback> viewfeedback()
		{
			return service.viewfeedback();
		}
	    
	    @GetMapping("studentcount")
	    public long studentcount()
	    {
	    	return service.studentcount();
	    }
	    
	    @GetMapping("facultycount")
	    public long facultycount()
	    {
	    	return service.facultycount();
	    }
	    
	    @GetMapping("projectcount")
	    public long projectcount()
	    {
	    	return service.projectcount();
	    }
	  
	
}


