package com.ocrs.registration.model;

import java.sql.Time;

public class Course {

	private Integer id;
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseId=" + courseId
				+ ", professorName=" + professorName + ", courseName="
				+ courseName + ", courseLevel=" + courseLevel + ", courseDesc="
				+ courseDesc + ", totalSlots=" + totalSlots
				+ ", availableSlots=" + availableSlots + ", location="
				+ location + ", startTime=" + startTime + "]";
	}

	private String courseId;
	private String professorName;
	private String courseName;
	private Integer courseLevel;
	private String courseDesc;
	private Integer totalSlots;
	private Integer availableSlots;
	private String location;
	private String startTime;
	
	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(Integer courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public Integer getTotalSlots() {
		return totalSlots;
	}

	public void setTotalSlots(Integer totalSlots) {
		this.totalSlots = totalSlots;
	}

	public Integer getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(Integer availableSlots) {
		this.availableSlots = availableSlots;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}