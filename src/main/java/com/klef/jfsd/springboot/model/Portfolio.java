package com.klef.jfsd.springboot.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "portfolio_table")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int portfolioId;

    @Column(length = 500, nullable = false)
    private String summary;
   
    @Column(length = 255,nullable = false)
    private int studentId;

    private String projectIds;
    
    
    
	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	} 

    

}