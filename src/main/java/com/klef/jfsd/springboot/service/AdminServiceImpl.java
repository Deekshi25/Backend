package com.klef.jfsd.springboot.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.FacultyStudentMapper;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.AdminRepository;
import com.klef.jfsd.springboot.repository.CertificationsRepository;
import com.klef.jfsd.springboot.repository.EducationRepository;
import com.klef.jfsd.springboot.repository.FacultyRepository;
import com.klef.jfsd.springboot.repository.FacultyStudentMapperRepository;
import com.klef.jfsd.springboot.repository.InternshipsRepository;
import com.klef.jfsd.springboot.repository.PortfolioRepository;
import com.klef.jfsd.springboot.repository.ProjectFeedbackRepository;
import com.klef.jfsd.springboot.repository.ProjectRepository;
import com.klef.jfsd.springboot.repository.SkillsRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;
import com.klef.jfsd.springboot.repository.TestimonialsRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private FacultyStudentMapperRepository facultyStudentMapperRepository;
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private ProjectFeedbackRepository projectFeedbackRepository;
	
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
	 private JavaMailSender mailSender;
	  

	  public  void sendEmail(String recipientEmail, String subject, String body) {
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);

	            helper.setTo(recipientEmail);
	            helper.setSubject(subject);
	            helper.setText(body);

	            mailSender.send(message);
	        } catch (MessagingException e) {
	            e.printStackTrace(); // Log and handle the exception
	        }
	    }
	
 
		@Override
		public String addStudent(Student s) {
			if(studentRepository.existsByEmailAndContact(s.getEmail(), s.getContact()))
			{
				return "Email Id you provided is associated to another student";
			}
			else if(studentRepository.existsByEmailAndContactAndFullName(s.getEmail(), s.getContact(),s.getFullName()))
			{
				return "Student you are trying to add has already been added";
			}
			else
			{
			studentRepository.save(s);
			
			 String subject = "Welcome to the System";
	         String body = "Dear " + s.getFullName() + ",\n\n" +
	                       "Your account has been created. Here are your login details:\n" +
	                       "Email: " + s.getEmail() + "\n" +
	                       "Password: " + s.getPassword() + "\n\n" +
	                       "Best regards,\nEduSupport Team";
	       
	                 try {
	 	    	        sendEmail(s.getEmail(), subject, body);
	 	    	    } catch (Exception e) {
	 	    	       
	 	    	        System.err.println("Failed to send email to " + s.getEmail() + ": " + e.getMessage());
	 	    	    }
	 	    	    
	 	            
			}
		return "Student added Successfully";
		}
		
		 
	
	 public String processAndSaveCSV(MultipartFile file) throws Exception {
	        List<Student> students = parseCSV(file);
	        studentRepository.saveAll(students);
	        
	        for (Student student : students) {
	            String subject = "Welcome to the System";
	            String body = "Dear " + student.getFullName() + ",\n\n" +
	                          "Your account has been created. Here are your login details:\n" +
	                          "Email: " + student.getEmail() + "\n" +
	                          "Password: " + student.getPassword() + "\n\n" +
	                          "Best regards,\nEduSupport Team";
                      try {
    	        sendEmail(student.getEmail(), subject, body);
    	    } catch (Exception e) {
    	       
    	        System.err.println("Failed to send email to " + student.getEmail() + ": " + e.getMessage());
    	    
    	    }
            
	        }
	        
	        return "Students added successfully!";
	    }

	   public static String generateRandomPassword(int length) {
	         String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
	         SecureRandom random = new SecureRandom();
	         StringBuilder password = new StringBuilder(length);
	         
	         for (int i = 0; i < length; i++) {
	             int randomIndex = random.nextInt(charset.length());
	             password.append(charset.charAt(randomIndex));
	         }
	         
	         return password.toString();
	     }
	     
	     public List<Student> parseCSV(MultipartFile file) throws Exception {
	         List<Student> students = new ArrayList<>();
	         Set<String> seenEmailsAndContacts = new HashSet<>();  // To track combinations of email and contact

	         // Define the expected date format (adjust as needed based on your CSV data)
	         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the pattern based on the CSV format

	         try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
	             for (CSVRecord record : parser) {
	                 String email = record.get("Email");
	                 String contact = record.get("Contact");

	                 // Combine email and contact to form a unique key
	                 String uniqueKey = email + contact;

	                 // Check if the student with this email and contact combination already exists in the database
	                 if (seenEmailsAndContacts.contains(uniqueKey) || studentRepository.existsByEmailAndContact(email, contact)) {
	                     continue;  // Skip the current student if duplicate found (either in the list or DB)
	                 }

	                 // Create a new student object
	                 Student student = new Student();
	                 student.setFullName(record.get("FullName"));
	                 student.setGender(record.get("Gender"));
	                 student.setDepartment(record.get("Department"));
	                 student.setProgram(record.get("Program"));
	                 student.setSemester(record.get("Semester"));
	                 student.setYear(Integer.parseInt(record.get("Year")));
	                 
	                 // Parse the DateOfBirth field to Date object
	                 String dateOfBirthStr = record.get("DateOfBirth");
	                 Date dateOfBirth = dateFormat.parse(dateOfBirthStr);  // Parse date string to Date object
	                 student.setDateOfBirth(dateOfBirth);

	                 student.setEmail(email);
	                 student.setContact(contact);

	                 // Uncomment this and comment down line of this 3 lines for randomized password
	                 // Generate a random 6-character password for each student
	                 //String randomPassword = generateRandomPassword(6);
	                 //student.setPassword(randomPassword);
	                 
	                 
	                 student.setPassword(record.get("Password"));

	                 // Add the unique key to the set to mark it as seen
	                 seenEmailsAndContacts.add(uniqueKey);

	                 // Add student to the list
	                 students.add(student);
	             }
	         }
	         return students;
	     }
	     
	     
	     public String processAndSaveFacultyCSV(MultipartFile file) throws Exception {
	    	    List<Faculty> facultyList = parseFacultyCSV(file); // Parse CSV file
	    	    facultyRepository.saveAll(facultyList); // Save the faculty list to the database
	    	    
	    	    for (Faculty faculty : facultyList) {
	                String subject = "Welcome to the System";
	                String body = "Dear " + faculty.getUsername() + ",\n\n" +
	                              "Your account has been created. Here are your login details:\n" +
	                              "Username: " + faculty.getUsername() + "\n" +
	                              "Password: " + faculty.getPassword() + "\n\n" +
	                              "Best regards,\nEduSupport Team";
	                           
	                          try {
	    	        sendEmail(faculty.getEmail(), subject, body);
	    	    } catch (Exception e) {
	    	       
	    	        System.err.println("Failed to send email to " + faculty.getEmail() + ": " + e.getMessage());
	    	    }
	    	    }
	            
	    	    
	    	    return "Faculty added successfully!";
	    	}

	    	public List<Faculty> parseFacultyCSV(MultipartFile file) throws Exception {
	    	    List<Faculty> facultyList = new ArrayList<>();
	    	    Set<String> seenEmails = new HashSet<>();  // To track unique emails

	    	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	    	        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
	    	        for (CSVRecord record : parser) {
	    	            String email = record.get("Email");

	    	            // Check if the email already exists in the database or has already been processed
	    	            if (seenEmails.contains(email) || facultyRepository.existsByEmail(email)) {
	    	                continue; // Skip if duplicate
	    	            }

	    	            // Create a new faculty object
	    	            Faculty faculty = new Faculty();
	    	            faculty.setUsername(record.get("Username"));
	    	            faculty.setPassword(generateRandomPassword(6)); // Generate a random password
	    	            faculty.setEmail(email);

	    	            // Add the email to the seen set
	    	            seenEmails.add(email);

	    	            // Add faculty to the list
	    	            facultyList.add(faculty);
	    	        }
	    	    }
	    	    return facultyList;
	    	}

	@Override
	public String updateStudent(Student s) {
		Optional<Student> student = studentRepository.findById(s.getId());
		
		String msg;
		
		if(student.isPresent())
		{
			studentRepository.save(s);
			msg = "Student Updated Successfully";
			
		}
		else
		{
			msg = "Student ID Not Found";
		}
		
		return msg;
	}
	@Override
	public List<Student> viewAllStudents() {
		
		return studentRepository.findAll();
		
	}
	
	@Override
	@Transactional
	public String deleteStudent(int sid) {
	  
	    Optional<Student> s = studentRepository.findById(sid);
	    if (s.isPresent()) {
	        
	    	//I am deleting everything related to student manually since i have not set jpa mappings 
	        projectRepository.deleteByStudentId(sid);

	        certificationRepository.deleteByStudentId(sid);
	        
	        internshipRepository.deleteByStudentId(sid);
	        
	        testimonialRepository.deleteByStudentId(sid);
	        
	        skillRepository.deleteByStudentId(sid);
	        
	        portfolioRepository.deleteByStudentId(sid);
	        
	        educationRepository.deleteByStudentId(sid);
	        
	        facultyStudentMapperRepository.deleteByStudentId(sid);

	        // Finally, delete the student
	        studentRepository.deleteById(sid);

	        return "Student and related data deleted successfully";
	    } else {
	        return "Student ID not found";
	    }
	}

	
	@Override
	public Student displayStudentByID(int sid)
	{ Optional<Student> s=studentRepository.findById(sid);
	if(s.isPresent())
		return studentRepository.findById(sid).get();	
	else
		return null;
	}
	@Override
	public Admin checkadminlogin(String username, String password) {
		
		return adminRepository.findByUsernameAndPassword(username,password);
	}
	@Override
	public String addFaculty(Faculty f) {

		if(facultyRepository.existsByEmail(f.getEmail()))
		{
			return "Email Id you provided is associated to another faculty";
		}
		else if(facultyRepository.existsByEmailAndUsername(f.getEmail(),f.getUsername()))
		{
			return "Faculty you are trying to add has already been added";
		}
		else
		{
	    facultyRepository.save(f);

	    // Prepare email details
	    String subject = "Welcome to the System";
	    String body = "Dear " + f.getUsername() + ",\n\n" +
	                  "Your account has been created. Here are your login details:\n" +
	                  "Username: " + f.getUsername() + "\n" +
	                  "Password: " + f.getPassword() + "\n\n" +
	                  "Best regards,\nEduSupport Team";

	    // Attempt to send the email, but suppress any errors
	    try {
	        sendEmail(f.getEmail(), subject, body);
	    } catch (Exception e) {
	        // Log the exception for debugging (optional)
	        System.err.println("Failed to send email to " + f.getEmail() + ": " + e.getMessage());
	    }
		}

	    return "Faculty added successfully";
	}

	@Override
	public String updateFaculty(Faculty f) {
	Optional<Faculty> faculty = facultyRepository.findById(f.getId());
		
		String msg;
		
		if(faculty.isPresent())
		{
			facultyRepository.save(f);
			msg = "Faculty Updated Successfully";
			
		}
		else
		{
			msg = "Faculty ID Not Found";
		}
		
		return msg;
	}
	@Override
	public List<Faculty> viewAllFaculty() {
		return facultyRepository.findAll();
	}
	@Override
	public Faculty displayFacultyById(int fid) {
		Optional<Faculty> f=facultyRepository.findById(fid);
		if(f.isPresent())
		return facultyRepository.findById(fid).get();	
		else
		return null;
	}
	
	@Override
	public String deleteFaculty(int fid) {
		 Optional<Faculty> f=facultyRepository.findById(fid);
		 if(f.isPresent())
		 {
			 facultyStudentMapperRepository.deleteByFacultyId(fid);
			 facultyRepository.delete(f.get());
			 return "Faculty Deleted Succesfully";
		 }
		 else
		 {
			 return "Faculty ID Not Found";
		 }	
	}
	@Override
	public String facultystudentMapping(FacultyStudentMapper fsm) {
		facultyStudentMapperRepository.save(fsm);
		return "Faculty Student Mapping Done";
	}
	@Override
	public List<FacultyStudentMapper> viewFacultyStudentMapping() {
	
		return (List<FacultyStudentMapper>) facultyStudentMapperRepository.findAll();
	}
	@Override
	public long checkFacultyStudentMapping(Faculty f, Student s) {
		return facultyStudentMapperRepository.checkfacultystudentmapping(f, s);
	}
	public boolean isStudentMappedToAnyFaculty(int studentId, int excludeFacultyId) {
	    List<FacultyStudentMapper> mappings = facultyStudentMapperRepository.findMappingsByStudentId(studentId);
	    return mappings.stream().anyMatch(mapping -> mapping.getFaculty().getId() != excludeFacultyId);
	}

	@Override
	public List<ProjectFeedback> viewfeedback() {
		return projectFeedbackRepository.findAll();
	}
}