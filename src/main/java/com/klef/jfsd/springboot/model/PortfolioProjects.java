package com.klef.jfsd.springboot.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import javax.sql.rowset.serial.SerialClob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "portfolio_projects_table")
public class PortfolioProjects {
    @Id
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
    
    public enum ProjectCheckpoint {
        ZERO(0),                 // For 0%
        TWENTY_FIVE(25),         // For 25%
        TWENTY_FIVE_TO_FIFTY(37), // For a checkpoint between 25% and 50%
        FIFTY(50),               // For 50%
        FIFTY_TO_SEVENTY_FIVE(62), // For a checkpoint between 50% and 75%
        SEVENTY_FIVE(75),        // For 75%
        SEVENTY_FIVE_TO_ONE_HUNDRED(87), // For a checkpoint between 75% and 100%
        ONE_HUNDRED(100);        // For 100%

        private final int value;

        // Constructor
        ProjectCheckpoint(int value) {
            this.value = value;
        }

        // Getter method
        public int getValue() {
            return value;
        }

        public static ProjectCheckpoint[] getAllValues() {
            return values();
        }

        // Method to get enum by value (if needed)
        public static ProjectCheckpoint fromValue(int value) {
            for (ProjectCheckpoint checkpoint : values()) {
                if (checkpoint.getValue() == value) {
                    return checkpoint;
                }
            }
            throw new IllegalArgumentException("Unexpected value: " + value);
        }

        @Override
        public String toString() {
            return value + "%"; // Customize this if you want the string representation of the enum
        }
    }

    
    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectPhase phase;
    
    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectCheckpoint percentage;

    private boolean checkStatus=false;
   
  
//    private List<Blob> files  = new ArrayList<>();   
//    private List<Blob> images = new ArrayList<>();
    @JsonIgnore
    private Blob file;
    @JsonIgnore
    private Blob image;
    
//    @JsonIgnore
//    private Blob pdf;
//    @JsonIgnore
//    private Blob zip; 
    
    @JsonIgnore
    private Blob reportCard;
    
   @OneToMany(mappedBy = "portfolioProject")
    private List<Media> mediaList;
   //private  List<Milestone> milestones;
   //private ProjectFeedback feedback;
    @Column( nullable = false)
    private int studentId;

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

//	public Blob getPdf() {
//		return pdf;
//	}
//
//	public void setPdf(Blob pdf) {
//		this.pdf = pdf;
//	}
//
//	public Blob getZip() {
//		return zip;
//	}
//
//	public void setZip(Blob zip) {
//		this.zip = zip;
//	}

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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<Media> getMediaList() {
		return mediaList;
	}

	public void setMedia(List<Media> mediaList) {
		this.mediaList = mediaList;
	}

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	public void setMediaList(List<Media> mediaList) {
		this.mediaList = mediaList;
	}

	public ProjectCheckpoint getPercentage() {
		return percentage;
	}

	public void setCheckpoint(ProjectCheckpoint percentage) {
		this.percentage = percentage;
	}

	public Blob getReportCard() {
		return reportCard;
	}

	public void setReportCard(Blob reportCard) {
		this.reportCard = reportCard;
	}

}