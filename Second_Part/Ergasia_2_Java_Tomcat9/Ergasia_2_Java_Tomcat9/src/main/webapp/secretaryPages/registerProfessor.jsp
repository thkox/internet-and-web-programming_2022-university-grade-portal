<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Register a Professor</title>
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
	  <a href="${pageContext.request.contextPath}/secretaryPages/courses.jsp">Courses</a>
	  <a href="${pageContext.request.contextPath}/ProfessorController?action=listProfessor">Professors</a>
	  <a href="${pageContext.request.contextPath}/StudentController?action=listStudent">Students</a>
	  <a class="enabled">Register new user</a>
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
				<a href="${pageContext.request.contextPath}/secretaryPages/registerStudent.jsp">Register Student</a>
				<a class="active">Register Professor</a>
			</div>
		  	<div class="header-right">
		    	<a href="${pageContext.request.contextPath}/secretaryPages/profile.jsp"><%=session.getAttribute("username") %></a>
		 	</div>
		</div>
		
		<h2>Register a Professor</h2>
		<div id="professor" class="container">
		  <form action="../RegisterProfessorServlet" method="POST" name="form" onsubmit="return validate()">
		  	<div class="row">
		      <div class="col-25">
		        <label for="registrationNumber">Registration Number</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="registrationNumber" name="registrationNumber" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="name">Name</label>
		      </div>
		      <div class="col-75">
		        <input type="text" id="name" name="name" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="surname">Surname</label>
		      </div>
		      <div class="col-75">
		        <input type="text" id="surname" name="surname" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="afm">AFM number</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="afm" name="afm" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="acadyearadmission">Academic Year Admission</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="acadyearadmission" name="acadyearadmission" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="birthdate">Birth Date</label>
		      </div>
		      <div class="col-75">
		        <input type="date" id="birthdate" name="birthdate" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="email">E-mail</label>
		      </div>
		      <div class="col-75">
		        <input type="email" id="email" name="email" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="password">Password</label>
		      </div>
		      <div class="col-75">
		        <input type="password" id="password" name="password" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-25">
		        <label for="repeatpassword">Repeat Password</label>
		      </div>
		      <div class="col-75">
		        <input type="password" id="repeatpassword" name="repeatpassword" placeholder="" required>
		      </div>
		    </div>
		    <div class="row">
		      <br>
		      <input type="submit" value="Submit">
		    </div>
		  </form>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>