<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Edit P${Professor.registrationNumber}</title>
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
	  <a class="enabled">Professors</a>
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
			<a class="active">Editing ${Professor.name}</a>
		</div>
	  	<div class="header-right">
	    	<a href="${pageContext.request.contextPath}/secretaryPages/profile.jsp"><%=session.getAttribute("username") %></a>
	 	</div>
	</div>
	<h2>Add a course</h2>
		<form method="POST" action='ProfessorController' name="frmeditProfessor">
			
			<input type="hidden" name="action" value="edit" />
			
			<div class="row">
		    	<div class="col-25">
					<label>Registration Number:</label>
				</div>
		      	<div class="col-75">
					<input type="number" readonly="readonly" name="registnumber" value="<c:out value="${Professor.registrationNumber}" />" />
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label>A.F.M.:</label>
			  	</div>
		      	<div class="col-75">
					<input type="number" name="afmnumber" value="<c:out value="${Professor.afmNumber}" />" />
			  	</div>
			</div>
			<div class="row">
			  <div class="col-25">
			  		<label>Name:</label>
			  </div>
		      <div class="col-75">
			  		<input type="text" name="name" value="<c:out value="${Professor.name}" />" />
			  </div>		
			</div>
			<div class="row">
			  <div class="col-25">
					<label>Surname:</label>
		      </div>
		      <div class="col-75">	
					<input type="text" name="surname" value="<c:out value="${Professor.surname}" />" />
			  </div>
			</div>
			<div class="row">
			  <div class="col-25">
				<label>Academic Year Admission:</label>
		      </div>
		      <div class="col-75">
				<input type="number" name="acadyearadmission" value="<c:out value="${Professor.acadYearAdmission}" />" />
			  </div>
			</div>
			<div class="row">
			  <div class="col-25">
			  	<label>Birth Date:</label>
			  </div>
		      <div class="col-75">
				 <input type="date" name="birthdate" value="<c:out value="${Professor.birthDate}" />" />
		      </div>
		    </div>
			<div class="row">
			  <div class="col-25">
			  	<label>Email:</label>	
			  </div>
		      <div class="col-75">
				<input type="text" name="email" value="<c:out value="${Professor.email}" />" />
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