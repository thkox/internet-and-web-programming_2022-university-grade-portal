<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Secretary's Profile</title>
	<link href="${pageContext.request.contextPath}/css/sidenavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/topnavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/tabledata.css" rel="stylesheet">
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
	  <a class="enabled">Profile</a>
	  <a href="${pageContext.request.contextPath}/secretaryPages/courses.jsp">Courses</a>
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
			
		  	<div class="header-right">
		    	<a class="active"> Welcome <%=session.getAttribute("username") %> </a>
		 	</div>
		</div>
		<h2>Secretary</h2>
		<table class="profile-table">
		    <thead>
	            <tr>
	                <th colspan=2>Profile Details</th>
	            </tr>
	        </thead>
			<tbody>
				<tr>
					<td class="categories">Registration number:</td>
					<td><%=session.getAttribute("username") %></td>
				</tr>
				<tr>
					<td class="categories">A.F.M.:</td>
					<td><%=session.getAttribute("afmnumber") %></td>
				</tr>
				<tr>
					<td class="categories">Name:</td>
					<td><%=session.getAttribute("name") %></td>
				</tr>
				<tr>
					<td class="categories">Surname:</td>
					<td><%=session.getAttribute("surname") %></td>
				</tr>
				<tr>
					<td class="categories">Academic Year Admission:</td>
					<td><%=session.getAttribute("acadyearadmission") %></td>
				</tr>
				<tr>
					<td class="categories">Birth Date:</td>
					<td><%=session.getAttribute("birthdate") %></td>
				</tr>
				<tr>
					<td class="categories">Email:</td>
					<td><%=session.getAttribute("email") %></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>