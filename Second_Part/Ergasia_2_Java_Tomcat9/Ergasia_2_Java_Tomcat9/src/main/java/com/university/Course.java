package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

public class Course extends Department{
    private int idcourses;
    private String title;
    private int hours;
    private String type;
    private String exerGrade;
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
    
	public String getExerGrade() {
		return exerGrade;
	}
	public void setExerGrade(String exerGrade) {
		this.exerGrade = exerGrade;
	}
	public int getIdcourses() {
		return idcourses;
	}
	public void setIdcourses(int idcourses) {
		this.idcourses = idcourses;
	}
}
