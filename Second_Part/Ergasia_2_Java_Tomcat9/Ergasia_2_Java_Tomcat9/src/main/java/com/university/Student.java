package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.util.InputMismatchException;

public class Student extends User{

	
	public Student(String name, String surname, String email, String birthDate, int acadYearAdmission,
			int registrationNumber, int semester) {
		super(name, surname, email, birthDate, acadYearAdmission, registrationNumber);
		this.semester = semester;
	}
	//finalGrade won't be set here
	public Student(String name, String surname, String email, String password, String salt, String birthDate,
			int acadYearAdmission, int registrationNumber, int semester)
	{
		//this sets the properties that the student inherits from the superclass
		super(name, surname, email, password, salt, birthDate, acadYearAdmission, registrationNumber);
		//sets the properties from the subclass
		this.semester = semester;
	}

	public Student() {
	}

	public int finalGrade;
    private int semester;

    public int getSemester() { return semester; }
    public void setSemester(int semester) {
        try {
            if (semester > 0 && semester <= 8)
                this.semester = semester;
            else
                throw new InputMismatchException("Invalid semester! Accepted integer from 1 to 8");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}