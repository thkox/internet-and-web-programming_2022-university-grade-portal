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
import com.university.Course;

@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_COURSES = "/secretaryPages/viewCourses.jsp";
	private static String LIST_COURSES_BY_PROFESSOR = "/professorPages/courses.jsp";
	private static String INSERT = "/secretaryPages/addCourse.jsp";
	private static String EDIT = "/secretaryPages/editCourse.jsp";
	private static String SELECT_COURSE_TO_ASSIGN = "/professorPages/assignGradesPerCourse.jsp";
	private static String SELECT_COURSE_TO_VIEW = "/professorPages/gradesListPerCourse.jsp";
	private static String SHOW_GRADES_TO_ASSIGN = "/professorPages/assignGradesToUngradedStudents.jsp";
	private static String SHOW_GRADES_TO_VIEW = "/professorPages/gradesListForGradedStudents.jsp";
	
	private CourseDBFunction coursedb;
       
    public CourseController() {
        super();
        coursedb = new CourseDBFunction();
    }
    
    //changes the db based on the courses
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("listCourse")) {	//in this case we print the course list in viewCourses.jsp
			forward = LIST_COURSES;
			request.setAttribute("Courses", coursedb.getAllCourses());
		} else if (action.equalsIgnoreCase("edit")) {	//edit course
			forward = EDIT;
			Course course = coursedb.getCourseByIdCourse(Integer.parseInt(request.getParameter("IdCourse")));
			request.setAttribute("Course", course);
		} else if (action.equalsIgnoreCase("delete")) {	//delete course
			coursedb.deleteCourseAssign(Integer.parseInt(request.getParameter("IdCourse")));
			coursedb.deleteCourse(Integer.parseInt(request.getParameter("IdCourse")));
			coursedb.deleteCourseStudentAssigned(Integer.parseInt(request.getParameter("IdCourse")));
			forward = LIST_COURSES;
			request.setAttribute("Courses", coursedb.getAllCourses());
		} else if (action.equalsIgnoreCase("selectCourseAssign")) { // select a course to assign to a professor
			forward = SELECT_COURSE_TO_ASSIGN;
			String registNumber = request.getParameter("registNumber");
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			request.setAttribute("Courses", coursedb.getAllCoursesByProfessor(regNum));
		} else if (action.equalsIgnoreCase("selectCourseView")) { //details for a course
			forward = SELECT_COURSE_TO_VIEW;
			String registNumber = request.getParameter("registNumber");
			int regNum = 0;
			registNumber = registNumber.substring(1);
			regNum = Integer.valueOf(registNumber);
			request.setAttribute("Courses", coursedb.getAllCoursesByProfessor(regNum));
		} else if (action.equalsIgnoreCase("selectedCourseAssign")){ //add grades for a selected course
			forward = SHOW_GRADES_TO_ASSIGN;
			request.setAttribute("GradesCourse", coursedb.getUnsignedGradesByCourse(Integer.parseInt(request.getParameter("courses"))));
		} else if (action.equalsIgnoreCase("selectedCourseView")){ //details for a course
			forward = SHOW_GRADES_TO_VIEW;
			request.setAttribute("GradesCourse", coursedb.getAssignedGradesByCourse(Integer.parseInt(request.getParameter("courses"))));
		} else if (action.equalsIgnoreCase("listCourseByProfessor")) {	//print courses assigned to a professor
			forward = LIST_COURSES_BY_PROFESSOR;
			String registNumber = request.getParameter("registNumber");
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			request.setAttribute("Courses", coursedb.getAllCoursesByProfessor(regNum));
		} else if (action.equalsIgnoreCase("insert")) {
			forward = INSERT;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	
	//insert, edit, view courses
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
    	
    	Course course = new Course();	
    	
    	RequestDispatcher view = null;
    	
    	if (action.equalsIgnoreCase("insert")) {	//insert a course to db
    		course.setIdcourse(Integer.parseInt(request.getParameter("idcourse")));
        	course.setTitle(request.getParameter("title"));
        	course.setSemester(Integer.parseInt(request.getParameter("semester")));
        	course.setHours(Integer.parseInt(request.getParameter("hours")));
        	course.setType(request.getParameter("type"));
    		
    		coursedb.addCourse(course);
    		
    		view = request.getRequestDispatcher(LIST_COURSES);
        	request.setAttribute("Courses", coursedb.getAllCourses());
    	} else if (action.equalsIgnoreCase("edit")) {	//edit a course to db
    		course.setIdcourse(Integer.parseInt(request.getParameter("idcourse")));
        	course.setTitle(request.getParameter("title"));
        	course.setSemester(Integer.parseInt(request.getParameter("semester")));
        	course.setHours(Integer.parseInt(request.getParameter("hours")));
        	course.setType(request.getParameter("type"));
    		
    		coursedb.updateCourse(course);
    		
    		view = request.getRequestDispatcher(LIST_COURSES);
        	request.setAttribute("Courses", coursedb.getAllCourses());
    	}else if (action.equalsIgnoreCase("selectedCourseAssign")){ //selected course assigned
    		view = request.getRequestDispatcher(SHOW_GRADES_TO_ASSIGN);
    		request.setAttribute("GradesCourse", coursedb.getUnsignedGradesByCourse(Integer.parseInt(request.getParameter("courses"))));
		}else if (action.equalsIgnoreCase("selectedCourseView")){ //view selected course
    		view = request.getRequestDispatcher(SHOW_GRADES_TO_VIEW);
    		request.setAttribute("GradesCourse", coursedb.getAssignedGradesByCourse(Integer.parseInt(request.getParameter("courses"))));
		}
    	view.forward(request, response);
	}

}
