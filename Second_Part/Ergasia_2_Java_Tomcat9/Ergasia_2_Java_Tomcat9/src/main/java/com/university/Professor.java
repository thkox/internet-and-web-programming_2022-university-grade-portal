package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Professor extends User{

	private boolean matches;
    private String regExPattern;
    private int afmNumber;
    private String courses;

	public Professor(String name, String surname, String email, String birthDate, int acadYearAdmission,
			int registrationNumber, int afmNumber) {
		super(name, surname, email, birthDate, acadYearAdmission, registrationNumber);
		this.afmNumber = afmNumber;
	}
    //constructor for courses
	public Professor(String name, String surname, String email, String password, String salt, String birthDate,
			int acadYearAdmission, int registrationNumber, int afmNumber, String courses) {
		super(name, surname, email, password, salt, birthDate, acadYearAdmission, registrationNumber);
		this.afmNumber = afmNumber;
		this.courses = courses;
	}
	
	//constructor for sign up
	public Professor(String name, String surname, String email, String password, String salt, String birthDate,
			int acadYearAdmission, int registrationNumber, int afmNumber) {
		super(name, surname, email, password, salt, birthDate, acadYearAdmission, registrationNumber);
		this.afmNumber = afmNumber;
	}
	

	public Professor() {
	}
	public int getAfmNumber() { return afmNumber; }
    public void setAfmNumber(int afmNumber) {
        try {
            if (afmNumber >= 100000000 && afmNumber <= 999999999)
                this.afmNumber = afmNumber;
            else
                throw new InputMismatchException("Invalid afm! Accepted number from 100000000 to 999999999");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getCourses() { return courses; }
    public void setCourses(String courses) {
        matches = false;
        regExPattern = "[a-zA-Z]+";
        try {
            matches = Pattern.matches(regExPattern, courses);
            if (matches)
                this.courses = courses;
            else
                throw new InputMismatchException("Invalid name! Alphanumeric characters and comma only!");
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}