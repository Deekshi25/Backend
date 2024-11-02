package com.klef.jfsd.springboot.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int portfolioId;

    @Column(length = 30, nullable = false)
    private String summary;

   @Column(length = 30, nullable = false)
    private String profilePictureUrl;

    @Column(length = 50, nullable = false)
    private List<String> skillNames; // You might want to reconsider this if you need to store as a single string

    @Column(length = 100)
    private List<String> certifications; // Certification class will be created later

    @Column(length = 100)
    private List<String> internships; // Internship class will be created later

    @Column(length = 255)
    private List<String> testimonials;

    @Column(length = 100, nullable = false)
    private String educationInstitution;

    @Column(length = 50, nullable = false)
    private String educationDegree;

    @Column( nullable = false)
    private Date educationStartDate;

    @Column( nullable = false)
    private Date educationEndDate;

    // private List<Project> projects; // List of projects

    // Getters and Setters and toString

}