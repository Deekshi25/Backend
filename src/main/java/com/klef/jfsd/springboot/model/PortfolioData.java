package com.klef.jfsd.springboot.model;

import java.util.List;

public class  PortfolioData
{

private List<Certifications> certifications;
private List<Education> education;
private List<Internships> internships;
private List<Portfolio> portfolios;
private List<Skills> skills;
private List<Testimonials> testimonials;
private List<Project> projects;



public List<Certifications> getCertifications() {
	return certifications;
}
public void setCertifications(List<Certifications> certifications) {
	this.certifications = certifications;
}
public List<Education> getEducation() {
	return education;
}
public void setEducation(List<Education> education) {
	this.education = education;
}
public List<Internships> getInternships() {
	return internships;
}
public void setInternships(List<Internships> internships) {
	this.internships = internships;
}
public List<Portfolio> getPortfolios() {
	return portfolios;
}
public void setPortfolios(List<Portfolio> portfolios) {
	this.portfolios = portfolios;
}
public List<Skills> getSkills() {
	return skills;
}
public void setSkills(List<Skills> skills) {
	this.skills = skills;
}
public List<Testimonials> getTestimonials() {
	return testimonials;
}
public void setTestimonials(List<Testimonials> testimonials) {
	this.testimonials = testimonials;
}

public List<Project> getProjects() {
	return projects;
}
public void setProjects(List<Project> projects) {
	this.projects = projects;
}


}
