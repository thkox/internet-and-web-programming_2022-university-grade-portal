package com.controller;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sqlFunctions.CoursesDBFunction;
import com.university.Course;

@WebServlet("/CoursesController")
public class CoursesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_Courses = "/secretaryPages/viewCourses.jsp";
	private static String INSERT = "/secretaryPages/addCourse.jsp";
	private static String EDIT = "/secretaryPages/editCourse.jsp";
	
	private CoursesDBFunction coursesdb;
       
    public CoursesController() {
        super();
        coursesdb = new CoursesDBFunction();
    }
    
    //changes the db based on the courses
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (action.equalsIgnoreCase("listCourse")) {	//in this case we print the course list in viewCourses.jsp
			forward = LIST_Courses;
			request.setAttribute("Courses", coursesdb.getAllCourses());
		} else if (action.equalsIgnoreCase("edit")) {	//in this case we print the information a selected course and we give the opportunity to the secretary user to change that information about this certain course
			forward = EDIT;
			Course course = coursesdb.getCourseByIdCourse(Integer.parseInt(request.getParameter("IdCourse")));
			request.setAttribute("Course", course);
		}else if (action.equalsIgnoreCase("delete")) {	//in this case we delete a course from our university system, database and courses list
			coursesdb.deleteCourseAssign(Integer.parseInt(request.getParameter("IdCourse")));
			coursesdb.deleteCourse(Integer.parseInt(request.getParameter("IdCourse")));
			forward = LIST_Courses;
			request.setAttribute("Courses", coursesdb.getAllCourses());
		}else {	//in other case we insert a new course to our university system, database and courses list
			forward = INSERT;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	
	//insert and edit courses
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
    	Course course = new Course();	
    	course.setIdcourses(Integer.parseInt(request.getParameter("idcourses")));
    	course.setTitle(request.getParameter("title"));
    	course.setSemester(Integer.parseInt(request.getParameter("semester")));
    	course.setHours(Integer.parseInt(request.getParameter("hours")));
    	course.setType(request.getParameter("type"));
    	if (action.equalsIgnoreCase("insert")) {	//in this case we apply a course insertion to  to our university system, database and courses list
    		coursesdb.addCourse(course);
    	} else if (action.equalsIgnoreCase("edit")) {	//in this case we apply the changes that a secretary user want to do at a course
    		coursesdb.updateCourse(course);
    	}
    	
    	RequestDispatcher view = request.getRequestDispatcher(LIST_Courses);
    	request.setAttribute("Courses", coursesdb.getAllCourses());
    	view.forward(request, response);
	}

}
