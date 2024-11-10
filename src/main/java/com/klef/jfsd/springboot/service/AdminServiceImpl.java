package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.AdminRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service
public class AdminServiceImpl  implements  AdminService
{
	@Autowired
    private AdminRepository adminRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Admin checkadminlogin(String username, String password)
	{
		Admin admin = adminRepository.checkadminlogin(username, password);
		return admin;
//		if(admin!=null)
//			return admin;
//		else
//			return null;
	}
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
	public String deleteStudent(int sid) {
	 Optional<Student> s=studentRepository.findById(sid);
	 if(s.isPresent())
	 {
		 studentRepository.delete(s.get());
		 return " Student Deleted Succesfully";
	 }
	 else
	 {
		 return "Student ID Not Found";
	 }	
	}
	
	@Override
	public Student displayStudentByID(int sid)
	{
		return studentRepository.findById(sid).get();	
	}

	

}
