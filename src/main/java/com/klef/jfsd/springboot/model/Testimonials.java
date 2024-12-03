package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "testimonials_table")
public class Testimonials {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testimonialId;

    @Column(nullable = false)
    private int studentId;

    @Column(length = 500, nullable = false)
    private String testimonialText;

    @Column(nullable = false)
    private String giverName;  // Name of the person who gave the testimonial
    
    @Column(nullable = true)
    private String giverRole;  // Role of the person giving the testimonial (optional)

    @Column(nullable = true)
    private String giverCompany;  // Company of the person giving the testimonial (optional)

    // Getters and Setters
    public int getTestimonialId() {
        return testimonialId;
    }

    public void setTestimonialId(int testimonialId) {
        this.testimonialId = testimonialId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getTestimonialText() {
        return testimonialText;
    }

    public void setTestimonialText(String testimonialText) {
        this.testimonialText = testimonialText;
    }

    public String getGiverName() {
        return giverName;
    }

    public void setGiverName(String giverName) {
        this.giverName = giverName;
    }

    public String getGiverRole() {
        return giverRole;
    }

    public void setGiverRole(String giverRole) {
        this.giverRole = giverRole;
    }

    public String getGiverCompany() {
        return giverCompany;
    }

    public void setGiverCompany(String giverCompany) {
        this.giverCompany = giverCompany;
    }
}
