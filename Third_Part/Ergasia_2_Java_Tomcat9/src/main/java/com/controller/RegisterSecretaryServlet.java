package com.controller;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbUtil.DBAccount;
import com.sqlFunctions.*;
import com.university.Secretary;

@WebServlet("/RegisterSecretaryServlet")
public class RegisterSecretaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBAccount accountdb;
	private SecretaryDBFunction secretarydb;
       
    public RegisterSecretaryServlet() {
        super();
        accountdb = new DBAccount();
        secretarydb = new SecretaryDBFunction();
    }
    //create a new professor account
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Secretary secretary;
		String salt = accountdb.getAlphaNumericString(16);	//create a random salt with the length of 16 characters
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		int afm = Integer.parseInt(request.getParameter("afm"));
		int acadyearadmission = Integer.parseInt(request.getParameter("acadyearadmission"));
		String birthdate = request.getParameter("birthdate");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int registNumber = Integer.parseInt(request.getParameter("registrationNumber"));
		secretary = new Secretary(name, surname, email, password, salt, birthdate, acadyearadmission, registNumber, afm);
		boolean registNumbervalidation = secretarydb.signupregistNumberCheck(registNumber);	//check if this registration number exists at the university system and database
		if (registNumbervalidation == true)
		{
			password = password + salt;	//add salt to the password
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-1");	//encrypt password
				byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				secretary.setPassword(accountdb.bytesToHex(encodedhash));
				
				//after finished the sign up redirect to register page
				secretarydb.signup(secretary);	//signup secretary	

				RequestDispatcher dispatcher =request.getRequestDispatcher("/secretaryPages/register.jsp");
				dispatcher.forward(request, response);
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			request.setAttribute("message", registNumbervalidation);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}
	}

}
