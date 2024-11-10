package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
	
//@Entity
//@Table(name = "faculty")
	public class Faculty 
	{
		@Id
		@Column(name = "fid")
		private int id;
		@Column(name = "funame",length = 20,nullable = false,unique = true)
		private String username;
		@Column(name = "fpassword",length = 20,nullable = false)
		private String password;
		@Column(name = "femail",length = 20,nullable = false,unique = true)
		private String email;
		
	}
	