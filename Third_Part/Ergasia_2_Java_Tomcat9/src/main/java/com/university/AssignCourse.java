package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

public class AssignCourse {
	
	public Professor professor = new Professor();
	public Course course = new Course();
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public AssignCourse(Professor professor, Course course) {
		super();
		this.professor = professor;
		this.course = course;
	}
	public AssignCourse() {
	}
}
