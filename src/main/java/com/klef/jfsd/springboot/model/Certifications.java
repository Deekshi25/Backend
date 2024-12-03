package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "certifications_table")
public class Certifications {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int certificationId;

    @Column(nullable = false)
    private int studentId;

    @Column(nullable = false)
    private String certificationName;

    @Column(nullable = false)
    private String certificationIssuer;

    @Column(nullable = false)
    private String certificationDate;

    private String expirationDate; // Expiration date is optional

    @Column(nullable = true)
    private String verificationLink;

    @Column(nullable = true, length = 1000)
    private String description;

    @Column(nullable = true)
    private String marksScored;

    @Column(nullable = true)
    private String honors;

    // Getters and Setters
    public int getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(int certificationId) {
        this.certificationId = certificationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public String getCertificationIssuer() {
        return certificationIssuer;
    }

    public void setCertificationIssuer(String certificationIssuer) {
        this.certificationIssuer = certificationIssuer;
    }

    public String getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(String certificationDate) {
        this.certificationDate = certificationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getVerificationLink() {
        return verificationLink;
    }

    public void setVerificationLink(String verificationLink) {
        this.verificationLink = verificationLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarksScored() {
        return marksScored;
    }

    public void setMarksScored(String marksScored) {
        this.marksScored = marksScored;
    }

    public String getHonors() {
        return honors;
    }

    public void setHonors(String honors) {
        this.honors = honors;
    }
}
