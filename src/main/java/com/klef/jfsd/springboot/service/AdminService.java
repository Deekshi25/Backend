package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.FacultyStudentMapper;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.model.Student;

public interface AdminService
{
	public Admin checkadminlogin(String username, String password);
	public String addStudent(Student s);
	 public String processAndSaveCSV(MultipartFile file) throws Exception;
	public List<Student> parseCSV(MultipartFile file) throws Exception ;
	public String updateStudent(Student s);
	public List<Student> viewAllStudents();
	public Student displayStudentByID(int sid);
	public String deleteStudent(int sid);
	
	public String addFaculty(Faculty f);
	public String updateFaculty(Faculty f);
	public List<Faculty> viewAllFaculty();
	public Faculty displayFacultyById(int fid);
	public String deleteFaculty(int fid);
	
    public String facultystudentMapping(FacultyStudentMapper fsm);
	public List<FacultyStudentMapper> viewFacultyStudentMapping();
	public boolean isStudentMappedToAnyFaculty(int studentId, int excludeFacultyId);
	public long checkFacultyStudentMapping(Faculty f,Student s);
	
	public List<ProjectFeedback> viewfeedback();
	public String processAndSaveFacultyCSV(MultipartFile file) throws Exception;
	public List<Faculty> parseFacultyCSV(MultipartFile file) throws Exception;
	public long studentcount();
	public long facultycount();
	public long projectcount();
}
