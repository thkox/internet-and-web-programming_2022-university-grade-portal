package com.controller;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sqlFunctions.ProfessorDBFunction;
import com.university.Professor;

@WebServlet("/ProfessorController")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String LIST_Professors = "/secretaryPages/professors.jsp";   
    private static String EDIT = "/secretaryPages/editProfessor.jsp";
    
    
    private ProfessorDBFunction professordb;

    public ProfessorController() {
        super();
        professordb = new ProfessorDBFunction();
    }

    //anything that has to do with professor db commands
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("listProfessor")) {	//in this case we print the professors list in professors.jsp
			forward = LIST_Professors;
			request.setAttribute("Professors", professordb.getAllProfessors());
		} else if (action.equalsIgnoreCase("delete")) {	//in this case we delete a professor from our university system, database and professors list
			professordb.deleteProfessorAssign(Integer.parseInt(request.getParameter("RegistNumber")));
			professordb.deleteProfessor(Integer.parseInt(request.getParameter("RegistNumber")));
			forward = LIST_Professors;
			request.setAttribute("Professors", professordb.getAllProfessors());
		} else if (action.equalsIgnoreCase("edit")) {	//in this case we print the information a selected professor and we give the opportunity to the secretary user to change that information about this certain professor
			forward = EDIT;
			Professor professor = professordb.getProfessorByRegistNumber(Integer.parseInt(request.getParameter("RegistNumber")));
			request.setAttribute("Professor", professor);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
    	Professor professor = new Professor();
    	professor.setRegistrationNumber(Integer.parseInt(request.getParameter("registnumber")));
    	professor.setAfmNumber(Integer.parseInt(request.getParameter("afmnumber")));
    	professor.setName(request.getParameter("name"));
    	professor.setSurname(request.getParameter("surname"));
    	professor.setAcadYearAdmission(Integer.parseInt(request.getParameter("acadyearadmission")));
    	professor.setBirthDate(request.getParameter("birthdate"));
    	professor.setEmail(request.getParameter("email"));
    	if (action.equalsIgnoreCase("edit")) {	//in this case we apply the changes that a secretary user want to do at a professor
    		professordb.updateProfessor(professor);
    	}
    	
    	RequestDispatcher view = request.getRequestDispatcher(LIST_Professors);
    	request.setAttribute("Professors", professordb.getAllProfessors());
    	view.forward(request, response);
	}

}
