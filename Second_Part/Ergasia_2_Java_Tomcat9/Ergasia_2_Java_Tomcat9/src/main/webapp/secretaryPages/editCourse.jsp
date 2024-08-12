<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Editing ${Course.title}</title>
	<link href="${pageContext.request.contextPath}/css/sidenavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/topnavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
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
	
	    System.out.println("Im here");
	
	    if(ch != 'S')
	    {
	        session.removeAttribute("username");
	        session.invalidate();
	        request.setAttribute("msg","Access to page denied, you were logged out for security reasons.");
	        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
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
				<span style="font-size:20px; cursor:pointer" onclick="openNav()">&#9776; Menu</span>
			</a>
			<div class="header-center">
				<a class="active">Editing ${Course.title}</a>
			</div>
		  	<div class="header-right">
		    	<a href="${pageContext.request.contextPath}/secretaryPages/profile.jsp"><%=session.getAttribute("username") %></a>
		 	</div>
		</div>
		<h2>Edit a course</h2>
		<form method="POST" action='CoursesController' name="frmeditCourse">
		
			<input type="hidden" name="action" value="edit" />
		
			<div class="row">
		      <div class="col-25">
		        <label for="idcourses">Course ID</label>
		      </div>
		      <div class="col-75">
		        <input type="number" name="idcourses" id="idcourses" value="<c:out value="${Course.idcourses}"/>"/>
		      </div>
		    </div>
			<div class="row">
			  <div class="col-25">
		        <label for="title">Title</label>
		      </div>
		      <div class="col-75">
		        <input type="text" name="title" id="title" value="<c:out value="${Course.title}"/>"/>
			  </div>
			</div>
			<div class="row">
			  <div class="col-25">
		        <label for="semester">Semester</label>
		      </div>
		      <div class="col-75">
		        <input type="number" name="semester" id="semester" value="<c:out value="${Course.semester}"/>"/>
		      </div>
		    </div>
			<div class="row">
			  <div class="col-25">
		        <label for="semester">Hours</label>
		      </div>
		      <div class="col-75">
		        <input type="number" name="hours" id="hours" value="<c:out value="${Course.hours}"/>"/>
		      </div>
		    </div>
			<div class="row">
			  <div class="col-25">
		        <label for="semester">Type</label>
		      </div>
		      <div class="col-75">
		        <input type="text" name="type" id="type" value="<c:out value="${Course.type}"/>" />
		      </div>
		    </div>
			<div class="row">
			  <br>
		      <input type="submit" value="Submit">
		    </div>
		</form>
	</div>
	<script src="${pageContext.request.contextPath}/js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>