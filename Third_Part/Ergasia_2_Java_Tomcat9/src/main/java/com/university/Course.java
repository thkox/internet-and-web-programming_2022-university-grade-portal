package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

public class Course extends Department{
    private int idcourse;
    private String title;
    private int hours;
    private String type;
    private int exerGrade;
    private int examGrade;
    private int finalGrade;
    private int semester;


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getFinalGrade() { return finalGrade; }
    public void setFinalGrade(int finalGrade) {    this.finalGrade = finalGrade; }

    public int getSemester() { return semester;}
    public void setSemester(int semester) { this.semester = semester;}
    
	public int getExerGrade() {
		return exerGrade;
	}
	public void setExerGrade(int exerGrade) {
		this.exerGrade = exerGrade;
	}
	public int getIdcourse() {
		return idcourse;
	}
	public void setIdcourse(int idcourse) {
		this.idcourse = idcourse;
	}
	public int getExamGrade() {
		return examGrade;
	}
	public void setExamGrade(int examGrade) {
		this.examGrade = examGrade;
	}
	@Override
	public String toString() {
		return "Course [idcourse=" + idcourse + ", title=" + title + ", hours=" + hours + ", type=" + type
				+ ", exerGrade=" + exerGrade + ", examGrade=" + examGrade + ", finalGrade=" + finalGrade + ", semester="
				+ semester + "]";
	}
}
