package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_table")
public class Admin 
{
	@Id
	@Column(name = "auname")
	private String username;
	@Column(name = "apassword",length = 20,nullable = false)
	private String password;
	@Column(name = "aemail",length = 20,nullable = false,unique = true)
	private String email;
	
	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", email=" + email + "]";
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
