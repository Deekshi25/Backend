package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "internships_table")
public class Internships {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int internshipId;

    @Column(nullable = false)
    private int studentId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String startDate;

    private String endDate;  // End date is optional
    
    private String about; // A brief description of the internship role
    
    private String technologiesUsed; // Technologies or tools used during the internship

    private String achievements; // Key achievements during the internship

    private String skillsGained; // Skills gained during the internship

    private String location; // Location of the internship (remote, office, etc.)

 
    // Getters and Setters
    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public String[] getTechnologiesUsedArray() {
        return technologiesUsed != null && !technologiesUsed.isEmpty()
                ? technologiesUsed.split(",")
                : new String[0]; // Return empty array if null or empty
    }

    public void setTechnologiesUsedArray(String[] technologiesArray) {
        this.technologiesUsed = technologiesArray != null && technologiesArray.length > 0
                ? String.join(",", technologiesArray)
                : null; // Set to null if the input array is null or empty
    }

    // Getter and Setter for `achievements`
    public String[] getAchievementsArray() {
        return achievements != null && !achievements.isEmpty()
                ? achievements.split(",")
                : new String[0];
    }

    public void setAchievementsArray(String[] achievementsArray) {
        this.achievements = achievementsArray != null && achievementsArray.length > 0
                ? String.join(",", achievementsArray)
                : null;
    }

    // Getter and Setter for `skillsGained`
    public String[] getSkillsGainedArray() {
        return skillsGained != null && !skillsGained.isEmpty()
                ? skillsGained.split(",")
                : new String[0];
    }

    public void setSkillsGainedArray(String[] skillsArray) {
        this.skillsGained = skillsArray != null && skillsArray.length > 0
                ? String.join(",", skillsArray)
                : null;
    }

	public String getTechnologiesUsed() {
		return technologiesUsed;
	}

	public void setTechnologiesUsed(String technologiesUsed) {
		this.technologiesUsed = technologiesUsed;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public String getSkillsGained() {
		return skillsGained;
	}

	public void setSkillsGained(String skillsGained) {
		this.skillsGained = skillsGained;
	}



}
