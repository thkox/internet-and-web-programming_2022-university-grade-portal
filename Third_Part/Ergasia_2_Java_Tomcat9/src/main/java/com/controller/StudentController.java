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
import com.sqlFunctions.StudentDBFunction;
import com.university.Student;


@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_STUDENTS = "/secretaryPages/students.jsp";
	private static String EDIT = "/secretaryPages/editStudent.jsp";
	private static String VIEW_COURSES = "/studentPages/allCourses.jsp";
	private static String ASSIGN_COURSE = "/studentPages/assignANewCourse.jsp";
	private static String REDIRECT_TO_COURSES = "/StudentController?action=viewCourses&registNumber=";
	private static String VIEW_GRADES_BY_STUDENT = "/studentPages/viewYourGrades.jsp";
	private static String SELECT_SEMESTER = "/studentPages/viewYourGradesBySemester.jsp";
	private static String SELECTED_SEMESTER = "/studentPages/viewYourGradesBySemesterSelected.jsp";

      
	private StudentDBFunction studentdb;
	private CourseDBFunction coursedb;

    public StudentController() {
        super();
        studentdb = new StudentDBFunction();
        coursedb = new CourseDBFunction();
    }


    //any action that has to do with a student
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("listStudent")) {	//print the students list in students.jsp
			forward  = LIST_STUDENTS;
			request.setAttribute("Students", studentdb.getAllStudents());
		} else if (action.equalsIgnoreCase("delete")) {	//delete a student from our university system, database and students list
			studentdb.deleteStudent(Integer.parseInt(request.getParameter("RegistNumber")));
			studentdb.deleteStudentAssignedCourses(Integer.parseInt(request.getParameter("RegistNumber")));
			forward = LIST_STUDENTS;			
			request.setAttribute("Students", studentdb.getAllStudents());
		} else if (action.equalsIgnoreCase("edit")) {	//print the information a selected student and change that information about this certain student
			forward = EDIT;
			Student student = studentdb.getStudentByRegistNumber(Integer.parseInt(request.getParameter("RegistNumber")));
			request.setAttribute("Student", student);
		} else if (action.equalsIgnoreCase("viewCourses")) {	//view all the courses assigned to the student
			forward = VIEW_COURSES;
			String registNumber = request.getParameter("registNumber");
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			request.setAttribute("Courses", coursedb.getAllCoursesByStudent(regNum));
		} else if (action.equalsIgnoreCase("assignCourse")) {	//assign a course to the student
			forward = ASSIGN_COURSE;
			String registNumber = request.getParameter("registNumber");
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			request.setAttribute("Courses", coursedb.getAllUnsignedCoursesByStudent(regNum));
		} else if (action.equalsIgnoreCase("selectedCourse")) {	//redirect when assigned a course to student
			String registNumber = request.getParameter("registNumber");
			forward = REDIRECT_TO_COURSES + registNumber;
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			int idcourse = Integer.parseInt(request.getParameter("courses")); 
			coursedb.assignCourseToStudent(idcourse, regNum);
		} else if (action.equalsIgnoreCase("viewAllGrades")) {	//view all grades assigned to student
			forward = VIEW_GRADES_BY_STUDENT;
			String registNumber = request.getParameter("registNumber");
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			request.setAttribute("Grades", studentdb.getCoursesWithGrades(regNum));
			request.setAttribute("AvgNumber", studentdb.getFinalAVGGrade(regNum));
		} else if (action.equalsIgnoreCase("selectSemester")) {	 //select a semester
			forward = SELECT_SEMESTER;
			String registNumber = request.getParameter("registNumber");
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			request.setAttribute("semesters", studentdb.getSemesters(regNum));
		} else if (action.equalsIgnoreCase("selectedSemester")) { //show courses that are assigned to the student
			forward = SELECTED_SEMESTER;
			String registNumber = request.getParameter("registNumber");
			int semesters = Integer.parseInt(request.getParameter("semesters"));
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			request.setAttribute("courses", studentdb.getCoursesBySemester(regNum, semesters));
			request.setAttribute("AvgNumber", studentdb.getFinalAVGGradeSemester(regNum, semesters));
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
    	
    	RequestDispatcher view = request.getRequestDispatcher(LIST_STUDENTS);
    	request.setAttribute("Students", studentdb.getAllStudents());
    	view.forward(request, response);
	}

}
