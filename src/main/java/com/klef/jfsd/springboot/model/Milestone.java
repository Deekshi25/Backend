package com.klef.jfsd.springboot.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;


@Entity
@Table(name = "milestone_table")
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private int id;

    @Column(name = "milestone_name",length = 30 ,nullable = false, unique = true)
    private String name;

    @Column(name = "milestone_description",length = 30 ,nullable = false)
    private String description;

    @Column(name = "milestone_duedate",length = 30 ,nullable = false)
    private Date dueDate;

    public enum Status {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED
    }
    
    @Enumerated(EnumType.STRING)
    @Column(name = "milestone_status",length = 30 ,nullable = false)
    private Status status = Status.NOT_STARTED;

    @Column(name = "milestone_completedAt",length = 30 ,nullable = false)
    private Date completedAt;

//    private Project project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

	/*
	 * public Project getProject() { return project; }
	 * 
	 * public void setProject(Project project) { this.project = project; }
	 */

    
}
