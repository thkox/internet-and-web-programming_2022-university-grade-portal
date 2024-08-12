package com.university;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.util.InputMismatchException;

public class Secretary extends User{
	
	private int afmNumber;

	public Secretary(String name, String surname, String email, String password, String salt, String birthDate,
			int acadYearAdmission, int registrationNumber, int afmNumber) {
		super(name, surname, email, password, salt, birthDate, acadYearAdmission, registrationNumber);
		this.afmNumber = afmNumber;
	}


    public Secretary() {
		
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
}