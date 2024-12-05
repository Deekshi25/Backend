package com.klef.jfsd.springboot.model;

import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_table")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String description;

    public enum ProjectPhase {
        NOT_STARTED("NOT_STARTED", "Project is not yet started", 0),
        IDEA("IDEA", "Project is in the idea and concept stage.", 20),
        DESIGN("DESIGN", "Project is in the design phase, working on mockups and wireframes.", 40),
        BUILD("BUILD", "Development work is underway, coding and implementation.", 60),
        TESTING("TESTING", "Project is undergoing testing and debugging.", 80),
        DEPLOYMENT("DEPLOYMENT", "The project is being deployed to production.", 100),
        COMPLETED("COMPLETED", "Project is Completed", 100);

        private final String shortTitle;
        private final String defaultDescription;
        private final int percentage;

        ProjectPhase(String shortTitle, String defaultDescription, int percentage) {
            this.shortTitle = shortTitle;
            this.defaultDescription = defaultDescription;
            this.percentage = percentage;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public String getDefaultDescription() {
            return defaultDescription;
        }

        public int getPercentage() {
            return percentage;
        }
    }

    @Enumerated(EnumType.STRING)
    private ProjectPhase phase;

    @ElementCollection
    @CollectionTable(name = "project_phase_descriptions", joinColumns = @JoinColumn(name = "project_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "description")
    private Map<ProjectPhase, String> phaseDescriptions = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "project_phase_grades", joinColumns = @JoinColumn(name = "project_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "grade")
    private Map<ProjectPhase, String> phaseGrades = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "project_phase_percentages", joinColumns = @JoinColumn(name = "project_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "percentage")
    private Map<ProjectPhase, Integer> phasePercentages = new HashMap<>();

    private boolean checkStatus = false;

    @JsonIgnore
    @Lob
    private Blob file;

    @JsonIgnore
    @Lob
    private Blob image;

    @JsonIgnore
    @Lob
    private Blob reportCard;

    @Column(length = 255)
    private String projectLink;

    @Column(length = 255)
    private String technologiesUsed;

    @Column(nullable = false)
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

    public ProjectPhase getPhase() {
        return phase;
    }

    public void setPhase(ProjectPhase phase) {
        this.phase = phase;
    }

    public Map<ProjectPhase, String> getPhaseDescriptions() {
        return phaseDescriptions;
    }

    public void setPhaseDescriptions(Map<ProjectPhase, String> phaseDescriptions) {
        this.phaseDescriptions = phaseDescriptions;
    }

    public Map<ProjectPhase, String> getPhaseGrades() {
        return phaseGrades;
    }

    public void setPhaseGrades(Map<ProjectPhase, String> phaseGrades) {
        this.phaseGrades = phaseGrades;
    }

    public Map<ProjectPhase, Integer> getPhasePercentages() {
        return phasePercentages;
    }

    public void setPhasePercentages(Map<ProjectPhase, Integer> phasePercentages) {
        this.phasePercentages = phasePercentages;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
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

    public Blob getReportCard() {
        return reportCard;
    }

    public void setReportCard(Blob reportCard) {
        this.reportCard = reportCard;
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
