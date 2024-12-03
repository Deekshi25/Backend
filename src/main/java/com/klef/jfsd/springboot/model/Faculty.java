package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
	
@Entity
@Table(name = "faculty_table")
	public class Faculty 
	{
		@Id
		@Column(name = "fid")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column(name = "funame",length = 20,nullable = false,unique = true)
		private String username;
		@Column(name = "fpassword",length = 20,nullable = false)
		private String password;
		@Column(name = "femail",length = 50,nullable = false,unique = true)
		private String email;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
	}
	