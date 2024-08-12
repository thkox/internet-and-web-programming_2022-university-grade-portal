package com.controller;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sqlFunctions.StudentDBFunction;
import com.university.Student;


@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_Students = "/secretaryPages/students.jsp";
	private static String EDIT = "/secretaryPages/editStudent.jsp";
      
	private StudentDBFunction studentdb;

    public StudentController() {
        super();
        studentdb = new StudentDBFunction();
    }


    //any action that has to do with a student
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("listStudent")) {	//in this case we print the students list in students.jsp
			forward  = LIST_Students;
			request.setAttribute("Students", studentdb.getAllStudents());
		} else if (action.equalsIgnoreCase("delete")) {	//in this case we delete a student from our university system, database and students list
			studentdb.deleteStudent(Integer.parseInt(request.getParameter("RegistNumber")));
			forward = LIST_Students;
			request.setAttribute("Students", studentdb.getAllStudents());
		} else if (action.equalsIgnoreCase("edit")) {	//in this case we print the information a selected student and we give the opportunity to the student user to change that information about this certain student
			forward = EDIT;
			Student student = studentdb.getStudentByRegistNumber(Integer.parseInt(request.getParameter("RegistNumber")));
			request.setAttribute("Student", student);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
    	Student student = new Student();
    	student.setRegistrationNumber(Integer.parseInt(request.getParameter("registnumber")));
    	student.setName(request.getParameter("name"));
    	student.setSurname(request.getParameter("surname"));
    	student.setAcadYearAdmission(Integer.parseInt(request.getParameter("acadyearadmission")));
    	student.setBirthDate(request.getParameter("birthdate"));
    	student.setEmail(request.getParameter("email"));
    	student.setSemester(Integer.parseInt(request.getParameter("semester")));
    	if (action.equalsIgnoreCase("edit")) {	//in this case we apply the changes that a secretary user want to do at a student
    		studentdb.updateStudent(student);
    	}
    	
    	RequestDispatcher view = request.getRequestDispatcher(LIST_Students);
    	request.setAttribute("Students", studentdb.getAllStudents());
    	view.forward(request, response);
	}

}
