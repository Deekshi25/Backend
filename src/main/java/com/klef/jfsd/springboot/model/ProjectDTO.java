package com.klef.jfsd.springboot.model;

import java.util.HashMap;
import java.util.Map;

public class ProjectDTO {

    private int projectId;
    private String title;
    private String description;
    private String phase;
    private Map<String, Integer> phasePercentages;
    private Map<String, String> phaseDescriptions;
    private Map<String, String> phaseGrades;
    private boolean checkStatus;
    private String projectLink;
    private String technologiesUsed;
    private int studentId;

    // Getters and Setters
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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Map<String, Integer> getPhasePercentages() {
        return phasePercentages;
    }

    public void setPhasePercentages(Map<String, Integer> phasePercentages) {
        this.phasePercentages = phasePercentages;
    }

    public Map<String, String> getPhaseDescriptions() {
        return phaseDescriptions;
    }

    public void setPhaseDescriptions(Map<String, String> phaseDescriptions) {
        this.phaseDescriptions = phaseDescriptions;
    }

    public Map<String, String> getPhaseGrades() {
        return phaseGrades;
    }

    public void setPhaseGrades(Map<String, String> phaseGrades) {
        this.phaseGrades = phaseGrades;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public String getTechnologiesUsed() {
        return technologiesUsed;
    }

    public void setTechnologiesUsed(String technologiesUsed) {
        this.technologiesUsed = technologiesUsed;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
