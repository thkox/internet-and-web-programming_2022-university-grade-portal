package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public abstract class User extends Department{


	public User(String name, String surname, String email, String birthDate, int acadYearAdmission,
			int registrationNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
		this.acadYearAdmission = acadYearAdmission;
		this.registrationNumber = registrationNumber;
	}

	private boolean matches;
    private String regExPattern;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String salt;
    private String birthDate;
    private int acadYearAdmission;
    private int registrationNumber = 0;

	public User(String name, String surname, String email, String password, String salt, String birthDate,
			int acadYearAdmission, int registrationNumber) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.birthDate = birthDate;
		this.acadYearAdmission = acadYearAdmission;
		this.registrationNumber = registrationNumber;
	}
    
	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}  
    public int getRegistrationNumber() { return registrationNumber; }
    public String getName() { return name; }
    public void setName(String name) {
        matches = false;
        regExPattern = "[a-zA-Z]+";
        try {
            matches = Pattern.matches(regExPattern, name);
            if (matches)
                this.name = name;
            else
                throw new InputMismatchException("Invalid name! Alphabetic characters only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getSurname() { return surname; }
    public void setSurname(String surname) {
        matches = false;
        regExPattern = "[a-zA-Z]+";
        try {
            matches = Pattern.matches(regExPattern, surname);
            if (matches)
                this.surname = surname;
            else
                throw new InputMismatchException("Invalid surname! Alphabetic characters only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        matches = false;
        regExPattern = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        try {
            matches = Pattern.matches(regExPattern, email);
            if (matches)
                this.email = email;
            else
                throw new InputMismatchException("Invalid email! Email format only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
                this.password = password;
    }
    
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) {
        matches = false;
        regExPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        try {
            matches = Pattern.matches(regExPattern, birthDate);
            if (matches)
                this.birthDate = birthDate;
            else
                throw new InputMismatchException("Invalid birthDate! Accepted format is YYYY-MM-DD");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int getAcadYearAdmission() { return acadYearAdmission; }
    public void setAcadYearAdmission(int acadYearAdmission) {
        try {
            if (acadYearAdmission > 1990 && acadYearAdmission <= Calendar.getInstance().get(Calendar.YEAR))
                this.acadYearAdmission = acadYearAdmission;
            else
                throw new InputMismatchException("Invalid academic year admission! Accepted integer from 1990 to 2022");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    User() {}



}
