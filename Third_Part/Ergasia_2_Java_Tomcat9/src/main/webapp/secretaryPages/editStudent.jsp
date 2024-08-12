<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Editing F${Student.registrationNumber}</title>
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
	  <a href="${pageContext.request.contextPath}/secretaryPages/courses.jsp">Courses</a>
	  <a href="${pageContext.request.contextPath}/ProfessorController?action=listProfessor">Professors</a>
	  <a class="enabled">Students</a>
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
			<a class="active">Editing ${Student.name} ${Student.surname}</a>
		</div>
	  	<div class="header-right">
	    	<a href="${pageContext.request.contextPath}/secretaryPages/profile.jsp"><%=session.getAttribute("username") %></a>
	 	</div>
	</div>
	<h2>Edit Student F${Student.registrationNumber}</h2>
	<div class="container">
	<form method="POST" action='StudentController' name="frmeditProfessor">
		<input type="hidden" name="action" value="edit" />
		
		<div class="row">
	    	<div class="col-25">
				<label>Registration Number:</label>
			</div>
			<div class="col-75">	
				<input type="number" readonly="readonly" name="registnumber" value="<c:out value="${Student.registrationNumber}" />" />
			</div>
		</div>
		
		<div class="row">
	    	<div class="col-25">
				<label>Name:</label>
			</div>
	      	<div class="col-75">
				<input type="text" name="name" value="<c:out value="${Student.name}" />" pattern="[a-zA-Z]{2,}" required>
			</div>
		</div>			
		<div class="row">
	    	<div class="col-25">
				<label>Surname: </label>
			</div>
	      	<div class="col-75">
				<input type="text" name="surname" value="<c:out value="${Student.surname}" />" pattern="[a-zA-Z]{2,}" required>
			</div>
		</div>			
		<div class="row">
	    	<div class="col-25">
				<label>Academic Year Admission:</label>
			</div>
	      	<div class="col-75">
				<input type="number" name="acadyearadmission" value="<c:out value="${Student.acadYearAdmission}" />" min="1991" max="2022" required>
			</div>
		</div>			
		<div class="row">
	    	<div class="col-25">
				<label>Birth Date:</label>
			</div>
	      	<div class="col-75">
				<input type="date" name="birthdate" value="<c:out value="${Student.birthDate}" />" min="1950-01-01" max="2003-12-31" required>
			</div>
		</div>			
		<div class="row">
	    	<div class="col-25">
				<label>Email:</label>
			</div>
	      	<div class="col-75">
				<input type="text" name="email" value="<c:out value="${Student.email}" />" required>
			</div>
		</div>			
		<div class="row">
	    	<div class="col-25">
				<label>Semester:</label>
			</div>
	      	<div class="col-75">
				<input type="number" name="semester" value="<c:out value="${Student.semester}" />" min="1" max="8" required>
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