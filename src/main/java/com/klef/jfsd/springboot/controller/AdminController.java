package com.klef.jfsd.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class AdminController
{
	@Autowired
	private AdminService service;


	@PostMapping("/checkadminlogin")
	public Admin checkAdminLogin(@RequestParam("username") String username , @RequestParam("password") String password)
	{
			
		Admin admin = service.checkadminlogin(username, password);	
		return admin;
		
	}
	
	
	@PostMapping("addstudent")
	public String addstudent(@RequestBody Student student)
	{
		return service.addStudent(student);
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
}

