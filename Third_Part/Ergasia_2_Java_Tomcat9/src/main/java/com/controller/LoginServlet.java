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
import javax.servlet.http.HttpSession;

import com.dbUtil.DBAccount;
import com.dbUtil.DBConnection;
import com.sqlFunctions.ProfessorDBFunction;
import com.sqlFunctions.SecretaryDBFunction;
import com.sqlFunctions.StudentDBFunction;
import com.university.Professor;
import com.university.Secretary;
import com.university.Student;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDBFunction studentdb;
	private SecretaryDBFunction secretarydb;
	private ProfessorDBFunction professordb;
	private DBAccount accountdb;
	
	public LoginServlet() {
        super();
        secretarydb = new SecretaryDBFunction();
        studentdb = new StudentDBFunction();
        professordb = new ProfessorDBFunction();
        accountdb = new DBAccount();
        DBConnection.connectToDB();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");	//registnumber with the letter in front of number
		String password = request.getParameter("password");
		boolean usernamevalidation = accountdb.loginUsernameCheck(username);
		//validate username
		if (usernamevalidation != true)	//if the username not exists then we redirect the user to the index.jsp page
		{
			request.setAttribute("error", "Username or Password is incorrect.<br> Please try again...");
			RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
			view.forward(request, response);
		}
		else	//if the username exists
		{
			//check password
			password = password + accountdb.getSalt(username);	//we add the salt that we get from the database, for this specific username
			MessageDigest digest;
			try {	//encrypt the password + salt
				digest = MessageDigest.getInstance("SHA-1");
				byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				password = accountdb.bytesToHex(encodedhash);
				boolean passwordvalidation = accountdb.passwordCheck(username, password);	//we check if this encrypted password is equivalent to this username
				
				if (passwordvalidation == true)	//if the username and password is equivalent to a user then the user will login to the university system
				{
					System.out.println("You have successfully logged in!");
					HttpSession session = request.getSession(true);	//we start a session for the session management
					session.setAttribute("username", username);	//registnumber
					
					char ch = username.charAt(0);	//Keep the first char which is the role
					
					switch(ch) {	//we check which char it is in the username to understand which type of user it is
					  case 'S':	//secretary user
					    Secretary secretary = secretarydb.GetSecretaryDetails(username);	//we keep the attributes for a secretary user
						session.setAttribute("afmnumber", secretary.getAfmNumber());
						session.setAttribute("name", secretary.getName());
						session.setAttribute("surname", secretary.getSurname());
						session.setAttribute("acadyearadmission", secretary.getAcadYearAdmission());
						session.setAttribute("birthdate", secretary.getBirthDate());
						session.setAttribute("email", secretary.getEmail());					
						RequestDispatcher view = request.getRequestDispatcher("sessionPage.jsp");
						view.forward(request, response);
					    break;
					  case 'F':	//student user
					    Student student = studentdb.getStudentDetails(username);	//we keep the attributes for a student user
                        session.setAttribute("name", student.getName());
                        session.setAttribute("surname", student.getSurname());
                        session.setAttribute("acadyearadmission", student.getAcadYearAdmission());
                        session.setAttribute("birthdate", student.getBirthDate());
                        session.setAttribute("email", student.getEmail());
                        session.setAttribute("semester", student.getSemester());
                        RequestDispatcher view1 = request.getRequestDispatcher("sessionPage.jsp");
                        view1.forward(request, response);
					    break;
					  case 'P':	//professor user
					    Professor professor = professordb.getProfessorDetails(username);	//we keep the attributes for a professor user
                        session.setAttribute("afmnumber", professor.getAfmNumber());
                        session.setAttribute("name", professor.getName());
                        session.setAttribute("surname", professor.getSurname());
                        session.setAttribute("acadyearadmission", professor.getAcadYearAdmission());
                        session.setAttribute("birthdate", professor.getBirthDate());
                        session.setAttribute("email", professor.getEmail());
                        RequestDispatcher view2 = request.getRequestDispatcher("sessionPage.jsp");
                        view2.forward(request, response);
						break;
					  default:
					}
				}
				else	//if the username and password
				{
					request.setAttribute("error", "Username or Password is incorrect.<br> Please try again...");
					RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
					view.forward(request, response);
				}
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
		}
	}
}
