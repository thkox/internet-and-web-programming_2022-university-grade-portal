package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

public class Grade {

	public Student student = new Student();
    public Course course = new Course();
    
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
    public Grade(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
    
    public Grade() {
    	
    }
}