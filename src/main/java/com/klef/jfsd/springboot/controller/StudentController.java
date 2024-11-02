package com.klef.jfsd.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Milestone;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController
{
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView login()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	
	@GetMapping("/about")
	public ModelAndView about()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("about");
		return mv;
	}
	
	@GetMapping("/contact")
	public ModelAndView contact()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("contact");
		return mv;
	}
	
	@GetMapping("/studenthome")
	public ModelAndView studenthome()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("studenthome");
		return mv;
	}
	
	
	@PostMapping("/checkstudentlogin")
	public ModelAndView checkStudentLogin(HttpServletRequest request)
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Student student = studentService.checkStudentLogin(email, password);
		
		if(student!=null)
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("studenthome");
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("loginfail");
			return mv;
		}
		
	}
	
	@GetMapping("/profile")
	public ModelAndView profile()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile");
		return mv;
	}
	@GetMapping("/projects")
	public ModelAndView projects()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("projects");
		return mv;
	}
	@GetMapping("/portfolio")
	public ModelAndView portfolio()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("portfolio");
		return mv;
	}
	@GetMapping("/stulogout")
	public ModelAndView stulogout()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stulogout");
		return mv;
	}
	
	@GetMapping("/milestoneinsertform")
	public ModelAndView milestoneInsertForm()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("milestoneinsertform");
		return mv;
	}
	
	
	
	@PostMapping("/addmilestone")
	public String addMilestone(HttpServletRequest request) throws ParseException
	{
		 int id = Integer.parseInt(request.getParameter("id"));
         String name = request.getParameter("name");
         String description = request.getParameter("description");
         String dueDateString = request.getParameter("duedate");
         String completedAtString = request.getParameter("completedAt");
        
         
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         Date dueDate = dateFormat.parse(dueDateString);
         Date completedAt = completedAtString.isEmpty() ? null : dateFormat.parse(completedAtString);
         
         Milestone milestone = new Milestone();
         milestone.setId(id);
         milestone.setName(name);
         milestone.setDescription(description);
         milestone.setDueDate(dueDate);
         milestone.setCompletedAt(completedAt);
       
		return studentService.addMilestone(milestone);
		
	}
	
	@GetMapping("/viewallmilestones")
	public ModelAndView ViewAllMilestones()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ViewAllMilestones");
		return mv;
	}
	
	
	@PostMapping("/updatemilestone")
	public String updateMilestone(Milestone milestone)
	{
		return studentService.addMilestone(milestone);	
	}
	
	
	@PostMapping("/deletemilestone")
	public String deleteMilestone(int mid)
	{
		return studentService.deleteMilestone(mid);	
	}
}
