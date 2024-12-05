package com.klef.jfsd.springboot.model;



import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PortfolioFeedback {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;

    @Column()
    private int rating; 

    @Column(length=50)
    private String comments; 

 
    @Column()
    private Date dateSubmitted;

    @Column()
    private int facultyId;
    
    @Column()
    private int studentId;

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}