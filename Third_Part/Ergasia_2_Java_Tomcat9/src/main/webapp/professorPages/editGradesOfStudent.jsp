<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Editing F${Grade.student.registrationNumber}'s Grades</title>
	<link href="${pageContext.request.contextPath}/css/sidenavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/topnavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
</head>
<body>
<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
	char ch;
	
	if((session.getAttribute("username")) == null)
	{
	    response.sendRedirect("../index.jsp");
	}
	else
	{
	    String registNumber = (session.getAttribute("username")).toString();    //temp variable to keep username untouchable
	    ch = registNumber.charAt(0);    //Keep the first char which is the role
	    if(ch != 'P')
	    {
	        session.removeAttribute("username");
	        session.invalidate();
	        request.setAttribute("error","Access to page denied.<br>You were logged out.<br>Please try to sign in again.");
	        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
	        requestDispatcher.forward(request,response);
	    }
	}
%>
	<div id="main">
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a class="department">INFORMATICS</a>
			<a href="${pageContext.request.contextPath}/professorPages/profile.jsp">Profile</a>
			<a href="${pageContext.request.contextPath}/CourseController?action=listCourseByProfessor&registNumber=<%=session.getAttribute("username") %>">Courses</a>
			<a class="enabled">Grades</a>
		  <a></a>
		  <a href="${pageContext.request.contextPath}/logout">Logout</a>
		</div>
		<div class="header">
			<a class="logo">
				<span style="font-size:20px; cursor:pointer" onclick="openNav()">&#9776; Menu</span>
			</a>
			<div class="header-center">
				<a class="active">Grades List Per Course</a>
				<a href="${pageContext.request.contextPath}/CourseController?action=selectCourseAssign&registNumber=<%=session.getAttribute("username") %>">Assign Grades Per Course</a>
			</div>
			<div class="header-right">
				<a href="${pageContext.request.contextPath}/professorPages/profile.jsp"><%=session.getAttribute("username") %></a>
			</div>
		</div>
		<h2><c:out value="${Grade.course.title}"/></h2>
		<div class="container">
		<form method="POST" action='ProfessorController' name="frmeditGradeStudent">
			<input type="hidden" name="action" value="editGrades" />
			
			<div class="row">
		    	<div class="col-25">
					<label>Student Registration Number:</label>
				</div>
				<div class="col-75">	
					<input type="number" readonly="readonly" name="registNumber" value="<c:out value="${Grade.student.registrationNumber}" />" />
				</div>
			</div>
			
			<div class="row">
		    	<div class="col-25">
					<label>Course ID:</label>
				</div>
		      	<div class="col-75">
					<input type="number" readonly="readonly" name="idCourse" value="<c:out value="${Grade.course.idcourse}" />" />
				</div>
			</div>
			<div class="row">
		    	<div class="col-25">
					<label>Exercise Grade: </label>
				</div>
		      	<div class="col-75">
					<input type="number" name="exerGrade" value="<c:out value="${Grade.course.exerGrade}" />" min="0" max="10" required>
				</div>
			</div>			
			<div class="row">
		    	<div class="col-25">
					<label>Exam Grade:</label>
				</div>
		      	<div class="col-75">
					<input type="number" name="examGrade" value="<c:out value="${Grade.course.examGrade}" />" min="0" max="10" required>
				</div>
			</div>			
			<div class="row">
		    	<div class="col-25">
					<label>Final Grade:</label>
				</div>
		      	<div class="col-75">
					<input type="number" name="finalGrade" value="<c:out value="${Grade.course.finalGrade}" />" min="0" max="10" required>
				</div>
			</div>
			<div class="row">
		      <br>
		      <input type="submit" value="Submit">
		    </div>
		</form>
		</div>
	</div>
	<div id="footer">
		<p>Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 © Copyright 2022</p>
	  	
	</div>
	<script src="${pageContext.request.contextPath}/js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>