<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Add a Course</title>
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
	    if(ch != 'S')
	    {
	        session.removeAttribute("username");
	        session.invalidate();
	        request.setAttribute("error","Access to page denied.<br>You were logged out.<br>Please try to sign in again.");
	        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
	        requestDispatcher.forward(request,response);
	    }
	}
%>
	<div id="mySidenav" class="sidenav">
	  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	  <a class="department">INFORMATICS</a>
	  <a href="${pageContext.request.contextPath}/secretaryPages/profile.jsp">Profile</a>
	  <a class="enabled">Courses</a>
	  <a href="${pageContext.request.contextPath}/ProfessorController?action=listProfessor">Professors</a>
	  <a href="${pageContext.request.contextPath}/StudentController?action=listStudent">Students</a>
	  <a href="${pageContext.request.contextPath}/secretaryPages/register.jsp">Register new user</a>
	  <a href="${pageContext.request.contextPath}/secretaryPages/assignCToP.jsp">Assign Course</a>
	  <a></a>
	  <a href="${pageContext.request.contextPath}/logout">Logout</a>
	</div>
	
	<div id="main">
		<div class="header">
			<a class="logo">
				<span style="font-size:20px; cursor:pointer" onclick="openNav()">&#9776; Main menu</span>
			</a>
			<div class="header-center">
				<a href="${pageContext.request.contextPath}/CourseController?action=listCourse">View Courses</a>
				<a class="active">Add Courses</a>
			</div>
		  	<div class="header-right">
		    	<a href="${pageContext.request.contextPath}/secretaryPages/profile.jsp"><%=session.getAttribute("username") %></a>
		 	</div>
		</div>
		<h2>Add a course</h2>
		<div class="container">
		<form method="POST" action='CourseController' name="frmAddCourse">
		
			<input type="hidden" name="action" value="insert" />
		
			<div class="row">
		      <div class="col-25">
		        <label for="idcourse">ID Course</label>
		      </div>
		      <div class="col-75">
		        <input type="number" name="idcourse" id="idcourse" placeholder="Enter Course ID"  min="100" max="999" required>
		      </div>
		    </div>
			<div class="row">
			  <div class="col-25">
		        <label for="title">Title</label>
		      </div>
		      <div class="col-75">
		        <input type="text" name="title" id="title" placeholder="Enter Title" pattern="[a-zA-Z ]{2,}" required>
			  </div>
			</div>
			<div class="row">
			  <div class="col-25">
		        <label for="semester">Semester</label>
		      </div>
		      <div class="col-75">
		        <input type="number" name="semester" id="semester" placeholder="Enter Semester" min="1" max="8" required>
		      </div>
		    </div>
			<div class="row">
			  <div class="col-25">
		        <label for="hours">Hours</label>
		      </div>
		      <div class="col-75">
		        <input type="number" name="hours" id="hours" placeholder="Enter Hours"  min="2" max="8" required>
		      </div>
		    </div>
			<div class="row">
			  <div class="col-25">
		        <label for="type">Type</label>
		      </div>
		      <div class="col-75">
		        <select name="type" id="type">
                      <option value="Mandatory">Mandatory</option>
                      <option value="Optional">Optional</option>
                </select>
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