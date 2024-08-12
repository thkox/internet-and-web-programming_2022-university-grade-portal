<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Assign Course</title>
	<link href="${pageContext.request.contextPath}/css/sidenavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/topnavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/tabledata.css" rel="stylesheet">
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
	  <a href="${pageContext.request.contextPath}/secretaryPages/professors.jsp">Professors</a>
	  <a href="${pageContext.request.contextPath}/StudentController?action=listStudent">Students</a>
	  <a href="${pageContext.request.contextPath}/secretaryPages/register.jsp">Register new user</a>
	  <a class="enabled">Assign Course</a>
	  <a></a>
	  <a href="${pageContext.request.contextPath}/logout">Logout</a>
	</div>
	
	<div id="main">
		<div class="header">
			<a class="logo">
				<span style="font-size:20px; cursor:pointer" onclick="openNav()">&#9776; Menu</span>
			</a>
			<div class="header-center">
				<a href="${pageContext.request.contextPath}/SecretaryController?action=viewAssign">View assigned Courses</a>
				<a class="active">Assign a new Course</a>
			</div>
		  	<div class="header-right">
		    	<a href="${pageContext.request.contextPath}/secretaryPages/profile.jsp"><%=session.getAttribute("username") %></a>
		 	</div>
		</div>
		<h2>Assign Courses to Professors</h2>
	</div>
		<form method="POST" action='SecretaryController' name="frmassignCourse">
			<input type="hidden" name="action" value="assign" />
			<table class="content-table">
				<thead>
					<tr>
						<th></th>
						<th>Please choose an option:</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<label for="professors">Choose a professor from the drop down list: </label>
						</td>
						<td>
							<select name="professors" id="professors">
								<c:forEach items="${requestScope.Professors}" var="s">
									<option value="<c:out value="${s.registrationNumber}" />">${s.registrationNumber} ${s.surname}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label for="courses">Choose a course from the drop down list: </label>
						</td>
						<td>
							<select name="courses" id="courses">
								<c:forEach items="${requestScope.Courses}" var="c">
									<option value="<c:out value="${c.idcourses}"/>">${c.idcourses} ${c.title}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="Assign"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	<script src="${pageContext.request.contextPath}/js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>