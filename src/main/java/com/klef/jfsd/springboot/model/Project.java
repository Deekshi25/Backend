package com.klef.jfsd.springboot.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.sql.rowset.serial.SerialClob;

@Entity
@Table(name = "project_table")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

	@Column(length=30, nullable = false)
    private String title;

    @Column(length=100, nullable = false)
    private String description;


   

    public enum ProjectPhase {
        NOT_STARTED,
        ACTIVE,
        COMPLETED
    }

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectPhase phase;

   
//    private List<Blob> files  = new ArrayList<>();   
//    private List<Blob> images = new ArrayList<>();

    private Blob file;
    private Blob image;
    
    
    private Blob pdf;
    private Blob zip;
    
   //private  List<Milestone> milestones;
   //private ProjectFeedback feedback;


    public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public ProjectPhase getPhase() {
		return phase;
	}

	public void setPhase(ProjectPhase phase) {
		this.phase = phase;
	}

//	public List<Blob> getFiles() {
//		return files;
//	}
//
//	public void setFiles(List<Blob> files) {
//		this.files = files;
//	}

//	public List<Blob> getImages() {
//		return images;
//	}
//
//	public void setImages(List<Blob> images) {
//		this.images = images;
//	}

	public Blob getPdf() {
		return pdf;
	}

	public void setPdf(Blob pdf) {
		this.pdf = pdf;
	}

	public Blob getZip() {
		return zip;
	}

	public void setZip(Blob zip) {
		this.zip = zip;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}