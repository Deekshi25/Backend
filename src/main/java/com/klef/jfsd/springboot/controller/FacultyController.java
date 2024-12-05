package com.klef.jfsd.springboot.controller;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.GradeProject;
import com.klef.jfsd.springboot.model.PortfolioData;
import com.klef.jfsd.springboot.model.PortfolioFeedback;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.service.FacultyService;
import com.klef.jfsd.springboot.service.ReportGenerator;
import com.klef.jfsd.springboot.service.StudentService;


@RestController
public class FacultyController
{
	@Autowired
	private FacultyService service;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ReportGenerator reportGenerator;
	

	@PostMapping("/checkfacultylogin")
    public Faculty checkFacultyLogin(@RequestBody Map<String, String> formData) {
        String username = formData.get("username");
        String password = formData.get("password");
        
        
		return service.checkfacultylogin(username, password);
	}
	

	@GetMapping("viewstudentsbyfaculty")
	public List<Student> viewmystudents(@RequestParam int fid)
	{
		return service.viewStudentsByFaculty(fid);
	}
	
	 @GetMapping("displaystudentportfolio")
	    public PortfolioData getPortfolioDataByStudentId(@RequestParam("studentId")  int studentId)
	    {
	    	return studentService.getPortfolioDataByStudentId(studentId);
	    } 
	
	@GetMapping("viewallprojectsbyfaculty")
	public List<Project> viewAllProjectsByFaculty(@RequestParam int facultyId) {
	    return service.viewProjectsByFaculty(facultyId);
	}

    @PostMapping("/gradeproject")
    public ResponseEntity<String> gradeProject(@RequestBody GradeProject pfDTO) {
        // Call the gradeProject method from the service
        String result = service.gradeProject(pfDTO);

        // Return the result as a response
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/reviewportfolio")
    public String reviewPortfolio(@RequestBody PortfolioFeedback portfolioFeedback)
    {
    	
    	return service.reviewPortfolio(portfolioFeedback);
    }
    
    @PostMapping("/allowproject")
    public ResponseEntity<String> allowProject(@RequestParam int projectId) {
        // Call the gradeProject method from the service
        String result = service.allowProject(projectId);

        // Return the result as a response
        return ResponseEntity.ok(result);
    }

	
	@GetMapping("viewallfeedback")
	public List<ProjectFeedback> fviewmyfeedback(@RequestParam int fid)
	{
		return service.fviewmyfeedback(fid);
	}
	
	@PostMapping("/generatereport")
    public ResponseEntity<String> generateReport(@RequestParam int projectId) {
        Blob reportCard = reportGenerator.generatePDFReport(projectId);
        if (reportCard != null) {
            return ResponseEntity.ok("Report generated successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to generate report.");
        }
    }

    @GetMapping("/viewreport")
    public ResponseEntity<byte[]> getReport(@RequestParam int projectId) {
        Project project = studentService.viewProjectByID(projectId);

        Blob reportCard = project.getReportCard();
        if (reportCard != null) {
            byte[] reportBytes;
            try {
                reportBytes = reportCard.getBytes(1, (int) reportCard.length());
                return ResponseEntity.ok()
                        .header("Content-Type", "application/pdf")
                        .header("Content-Disposition", "inline; filename=report.pdf")
                        .body(reportBytes);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(null);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}

