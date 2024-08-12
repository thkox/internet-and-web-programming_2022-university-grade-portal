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
import com.sqlFunctions.StudentDBFunction;
import com.university.Student;

@WebServlet("/RegisterStudentServlet")
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBAccount accountdb;
	private StudentDBFunction studentdb;

    public RegisterStudentServlet() {
        super();
        accountdb = new DBAccount();
        studentdb = new StudentDBFunction();
    }
    //create a new student account
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student;
		String salt = accountdb.getAlphaNumericString(16);	//create a random salt with the length of 16 characters
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		int semester = Integer.parseInt(request.getParameter("semester"));
		int acadyearadmission = Integer.parseInt(request.getParameter("acadyearadmission"));
		String birthdate = request.getParameter("birthdate");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int registNumber = Integer.parseInt(request.getParameter("registrationNumber"));
		student = new Student(name, surname, email, password, salt, birthdate, acadyearadmission, registNumber, semester);
		boolean registNumbervalidation = studentdb.signUpRegistNumberCheck(registNumber);	//check if this registration number exists at the university system and database
		if (registNumbervalidation == true){
			password = password + salt;	//add salt to the password
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-1");	//encrypt password
				byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				student.setPassword(accountdb.bytesToHex(encodedhash));
				
				studentdb.signup(student);	//signup professor
				RequestDispatcher dispatcher =request.getRequestDispatcher("/secretaryPages/register.jsp");
				dispatcher.forward(request, response);
			}
			catch (NoSuchAlgorithmException e){
				e.printStackTrace();
			}
		}else{
			request.setAttribute("message", registNumbervalidation);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}
	}

}
