package com.controller;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbUtil.DBAccount;
import com.sqlFunctions.CourseDBFunction;
import com.sqlFunctions.ProfessorDBFunction;
import com.sqlFunctions.SecretaryDBFunction;
import com.sqlFunctions.StudentDBFunction;


@WebServlet("/SecretaryController")
public class SecretaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ASSIGN = "/secretaryPages/addNewCToP.jsp";
	private static String VIEW_ASSIGN = "/secretaryPages/viewCToP.jsp";
	
	private CourseDBFunction coursedb;
	private ProfessorDBFunction professordb;

    public SecretaryController() {
    	super();
    	new DBAccount();
        new StudentDBFunction();
        new SecretaryDBFunction();
        coursedb = new CourseDBFunction();
        professordb = new ProfessorDBFunction();
    }
    //actions that has to do with the secretary
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String forward="";
        String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("assign")) {	//assign a course to a professor
			forward = ASSIGN;
			request.setAttribute("Professors", professordb.getAllProfessors());
			request.setAttribute("Courses", coursedb.getAllCourses());
		}else if (action.equalsIgnoreCase("viewAssign")) {	//print the assigned courses by professors with the professors
			forward = VIEW_ASSIGN;
			request.setAttribute("AssignCourses", coursedb.getAllAssignedCourses());
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
    	
    	int firstOption = Integer.parseInt(request.getParameter("professors"));	//take the value from the first drop down list which contains the professors
    	int secondOption = Integer.parseInt(request.getParameter("courses"));	//take the value from the second drop down list which contains the courses
    	
    	if (action.equalsIgnoreCase("assign")) {	//assign the professor with the course
    		coursedb.assignCourse(secondOption, firstOption);
    	}
    	
    	RequestDispatcher view = request.getRequestDispatcher("/secretaryPages/profile.jsp");
    	view.forward(request, response);
	}

}
