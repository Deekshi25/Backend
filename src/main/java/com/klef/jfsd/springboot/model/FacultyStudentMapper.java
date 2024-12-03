package com.klef.jfsd.springboot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="facultystudentmapping_table")
public class FacultyStudentMapper 
{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int mappingid;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "faculty_id") // foreign key column name
	  private Faculty faculty;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "student_id") // foreign key column name
	  private Student student;

	  
	public int getMappingid() {
		return mappingid;
	}

	public void setMappingid(int mappingid) {
		this.mappingid = mappingid;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "FacultyStudentMapper [mappingid=" + mappingid + ", faculty=" + faculty + ", student=" + student + "]";
	}
	  	
}
 