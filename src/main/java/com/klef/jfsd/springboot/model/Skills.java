package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills_table")
public class Skills {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillId;  // Unique ID for each skill
    
    @Column(nullable = false)
    private int studentId; // The student ID to associate the skill with a specific student
    
    @Column(nullable = false)
    private String skillName; // Name of the skill
    
    @Column(nullable = false)
    private String skillLevel; // Skill level (Beginner, Intermediate, Advanced)

    // New field added for more context
    @Column(length = 100)
    private String skillCategory; // Category of the skill (e.g., Programming, Soft Skill)
//
//    @Column(nullable = true)
//    private int yearsOfExperience=5; // Number of years the student has worked with this skill

    // Getters and Setters
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    // Getters and Setters for new fields
    public String getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }
//
//    public int getYearsOfExperience() {
//        return yearsOfExperience;
//    }
//
//    public void setYearsOfExperience(int yearsOfExperience) {
//        this.yearsOfExperience = yearsOfExperience;
//    }

    @Override
    public String toString() {
        return "Skills{" +
                "skillId=" + skillId +
                ", studentId=" + studentId +
                ", skillName='" + skillName + '\'' +
                ", skillLevel='" + skillLevel + '\'' +
                ", skillCategory='" + skillCategory + 
//                '\'' 
//                +
//                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
