package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "education_table")
public class Education {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int educationId;

    @Column(length = 100, nullable = false)
    private int studentId;

    @Column(length = 100)
    private String educationInstitution;

    @Column(length = 50)
    private String educationDegree;

    @Column(length = 100)
    private String fieldOfStudy;  // New field

    @Column(length = 10)
    private String grade;  // New field

    @Column(length = 100)
    private String location;  // New field

    private String educationStartDate;

    private String educationEndDate;

    // Getters and Setters

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getEducationInstitution() {
        return educationInstitution;
    }

    public void setEducationInstitution(String educationInstitution) {
        this.educationInstitution = educationInstitution;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducationStartDate() {
        return educationStartDate;
    }

    public void setEducationStartDate(String educationStartDate) {
        this.educationStartDate = educationStartDate;
    }

    public String getEducationEndDate() {
        return educationEndDate;
    }

    public void setEducationEndDate(String educationEndDate) {
        this.educationEndDate = educationEndDate;
    }
}
