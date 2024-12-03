package com.klef.jfsd.springboot.model;

import java.sql.Blob;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "media_table")
public class Media 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mediaId;

	 @JsonIgnore
	private Blob media;
	
	private String mediaType;
	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonIgnore
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "portfolio_project_id")
	@JsonIgnore
	private PortfolioProjects portfolioProject;
	
	public int getMediaId() {
		return mediaId;
	}
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	public Blob getMedia() {
		return media;
	}
	public void setMedia(Blob media) {
		this.media = media;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
}
