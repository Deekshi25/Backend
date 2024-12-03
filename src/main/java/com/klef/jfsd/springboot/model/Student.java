
package com.klef.jfsd.springboot.model;

import java.sql.Blob;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_table")
public class Student
{

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private int id;

    @Column(name = "sname",length = 30, nullable = false)
    private String fullName;

    @Column(name = "sgender",length = 10,nullable = false)
    private String gender; 

    @Column(name = "sdepartment",length = 10,nullable = false)
    private String department;

    @Column(name = "sprogram" ,length = 10, nullable = false)
    private String program; 

    @Column(name = "ssemester", length = 10, nullable = false)
    private String semester; 

    @Column(name = "syear", nullable = false)
    private int year;

    @Column(name = "sdob", nullable = false)
    private Date dateOfBirth;

    @Column(name = "spassword", nullable = false, length = 30)
    private String password;

    @Column(name = "semail", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "scontact",length = 15, nullable = false, unique = true)
    private String contact;  
    
    @Column
    private Blob profile;
    
    @Column
    private Blob Resume;
  

  @Override
  public String toString() {
    return "Student [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", department=" + department
        + ", program=" + program + ", semester=" + semester + ", year=" + year + ", dateOfBirth=" + dateOfBirth
        + ", password=" + password + ", email=" + email + ", contact=" + contact + "]";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getProgram() {
    return program;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
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

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

public Blob getProfile() {
	return profile;
}

public void setProfile(Blob profile) {
	this.profile = profile;
}

public Blob getResume() {
	return Resume;
}

public void setResume(Blob resume) {
	Resume = resume;
}

  
  
}