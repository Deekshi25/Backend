package com.klef.jfsd.springboot.model;


import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(length=30, nullable = false)
    private String title;

    @Column(length=100, nullable = false)
    private String description;

    @Column( nullable = false)
    private Date startDate;

    @Column( nullable = false)
    private Date endDate;

    public enum ProjectPhase {
        NOT_STARTED,
        ACTIVE,
        COMPLETED
    }

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectPhase phase;

    @Column( length=50,nullable = false)
    private String textFileUrl;

    @Column( length=50,nullable = false)
    private String mediaUrl; 
  
   //private  List<Milestone> milestones;
   //private ProjectFeedback feedback;

//Add Getters and Setters and toString()
}