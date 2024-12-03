package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.FacultyStudentMapper;
import com.klef.jfsd.springboot.model.Media;
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
	@Override
	public String addStudent(Student s) {
		studentRepository.save(s);
	return "Student added Successfully";
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
		facultyRepository.save(f);
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