package com.klef.jfsd.springboot.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ProjectFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;
    @Column(nullable=false,unique=true)
    private int projectid;

    @Column(nullable=false)
    private int rating; 

    @Column(length=50,nullable=false)
    private String comments; 

    @Column(nullable=false)
    private Date dateSubmitted;

    //private String submittedBy;

    // Getters and Setters and toString()
}