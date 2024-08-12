package com.controller;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sqlFunctions.CourseDBFunction;
import com.sqlFunctions.ProfessorDBFunction;
import com.university.Grade;
import com.university.Professor;

@WebServlet("/ProfessorController")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String LIST_PROFESSORS = "/secretaryPages/professors.jsp";   
    private static String EDIT = "/secretaryPages/editProfessor.jsp";
    private static String EDIT_GRADES = "/professorPages/editGradesOfStudent.jsp";
    private static String ADD_GRADES = "/professorPages/addGradesToStudent.jsp";
    
    private ProfessorDBFunction professordb;
    private CourseDBFunction coursedb;

    public ProfessorController() {
        super();
        professordb = new ProfessorDBFunction();
        coursedb = new CourseDBFunction();
    }

    //anything that has to do with professor db commands
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("listProfessor")) {	//professors list
			forward = LIST_PROFESSORS;
			request.setAttribute("Professors", professordb.getAllProfessors());
		} else if (action.equalsIgnoreCase("delete")) {	//professor from our university system, database and professors list
			professordb.deleteProfessorAssign(Integer.parseInt(request.getParameter("RegistNumber")));
			professordb.deleteProfessor(Integer.parseInt(request.getParameter("RegistNumber")));
			forward = LIST_PROFESSORS;
			request.setAttribute("Professors", professordb.getAllProfessors());
		} else if (action.equalsIgnoreCase("edit")) {	//selected professor, secretary user change this certain professor
			forward = EDIT;
			Professor professor = professordb.getProfessorByRegistNumber(Integer.parseInt(request.getParameter("RegistNumber")));
			request.setAttribute("Professor", professor);
		} else if (action.equalsIgnoreCase("editGrades")) {
			forward = EDIT_GRADES;
			Grade grade = coursedb.getGradesByCourseAndStudent(Integer.parseInt(request.getParameter("IdCourse")), Integer.parseInt(request.getParameter("RegistNumber")));
			request.setAttribute("Grade", grade);
		} else if (action.equalsIgnoreCase("addGrades")) {
			forward = ADD_GRADES;
			Grade grade = coursedb.getGradesByCourseAndStudent(Integer.parseInt(request.getParameter("IdCourse")), Integer.parseInt(request.getParameter("RegistNumber")));
			request.setAttribute("Grade", grade);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
    	
    	RequestDispatcher view = null;
    	
    	if (action.equalsIgnoreCase("edit")) {	//apply the changes for professor user
    		Professor professor = new Professor();
        	professor.setRegistrationNumber(Integer.parseInt(request.getParameter("registnumber")));
        	professor.setAfmNumber(Integer.parseInt(request.getParameter("afmnumber")));
        	professor.setName(request.getParameter("name"));
        	professor.setSurname(request.getParameter("surname"));
        	professor.setAcadYearAdmission(Integer.parseInt(request.getParameter("acadyearadmission")));
        	professor.setBirthDate(request.getParameter("birthdate"));
        	professor.setEmail(request.getParameter("email"));
    		
    		professordb.updateProfessor(professor);
    		
    		view = request.getRequestDispatcher(LIST_PROFESSORS);
        	request.setAttribute("Professors", professordb.getAllProfessors());
    	}else if(action.equalsIgnoreCase("editGrades")) { //edit grades of students
        	Grade grade = new Grade();
        	grade.student.setRegistrationNumber(Integer.parseInt(request.getParameter("registNumber")));
        	grade.course.setIdcourse(Integer.parseInt(request.getParameter("idCourse")));
        	grade.course.setExerGrade(Integer.parseInt(request.getParameter("exerGrade")));
        	grade.course.setExamGrade(Integer.parseInt(request.getParameter("examGrade")));
        	grade.course.setFinalGrade(Integer.parseInt(request.getParameter("finalGrade")));
    		
    		coursedb.updateGradesByCourseAndStudent(grade);
    		view = request.getRequestDispatcher("/CourseController?action=selectedCourseView&courses=" + grade.course.getIdcourse());
    	}else if(action.equalsIgnoreCase("addGrades")) { //add grades of students
        	Grade grade = new Grade();
        	grade.student.setRegistrationNumber(Integer.parseInt(request.getParameter("registNumber")));
        	grade.course.setIdcourse(Integer.parseInt(request.getParameter("idCourse")));
        	grade.course.setExerGrade(Integer.parseInt(request.getParameter("exerGrade")));
        	grade.course.setExamGrade(Integer.parseInt(request.getParameter("examGrade")));
        	grade.course.setFinalGrade(Integer.parseInt(request.getParameter("finalGrade")));
    		
    		coursedb.updateGradesByCourseAndStudent(grade);
    		view = request.getRequestDispatcher("/CourseController?action=selectedCourseAssign&courses=" + grade.course.getIdcourse());
    	}
    	view.forward(request, response);
	}

}
