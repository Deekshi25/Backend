package com.klef.jfsd.springboot.model;

public class GradeProject 
{
	    private int projectId;
	    private int facultyId;
	    private String grade; 
	    private String feedback; 
	    private String phase;
		public int getProjectId() {
			return projectId;
		}
		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}
		public int getFacultyId() {
			return facultyId;
		}
		public void setFacultyId(int facultyId) {
			this.facultyId = facultyId;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		public String getPhase() {
			return phase;
		}
		public void setPhase(String phase) {
			this.phase = phase;
		} 
}
