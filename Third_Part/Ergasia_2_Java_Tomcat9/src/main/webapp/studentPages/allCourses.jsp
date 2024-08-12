<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Your Assigned Courses</title>
	<link href="${pageContext.request.contextPath}/css/sidenavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/topnavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/tabledata.css" rel="stylesheet">
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
		    if(ch != 'F')
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
	  <a href="${pageContext.request.contextPath}/studentPages/profile.jsp">Profile</a>
	  <a class="enabled">Courses</a>
	  <a href="${pageContext.request.contextPath}/studentPages/grades.jsp">Grades</a>
	  <a></a>
	  <a href="${pageContext.request.contextPath}/logout">Logout</a>
	</div>
	
	<div id="main">
		<div class="header">
			<a class="logo">
				<span style="font-size:20px; cursor:pointer" onclick="openNav()">&#9776; Main menu</span>
			</a>
			<div class="header-center">
				<a class="active">All Your Assigned Courses</a>
				<a href="${pageContext.request.contextPath}/StudentController?action=assignCourse&registNumber=<%=session.getAttribute("username") %>">Assign A New Course</a>
			</div>
		  	<div class="header-right">
		    	<a href="${pageContext.request.contextPath}/studentPages/profile.jsp"><%=session.getAttribute("username") %></a>
		 	</div>
		</div>
		<h2>Your Courses</h2>
		<table  class="content-table">
	        <thead>
	            <tr>
	            	<th>Semester</th>
	                <th>ID</th>
	                <th>Title</th>
	                <th>Hours</th>
	                <th>Type</th>
	            </tr>
	        </thead>
	        <tbody>
	           	<c:forEach items="${requestScope.Courses}" var="s">
	                <tr>
	                	<td><c:out value="${s.semester}"/></td>
	                	<td><c:out value="${s.idcourse}"/></td>
	                	<td><c:out value="${s.title}"/></td>
	                	<td><c:out value="${s.hours}"/></td>
	                    <td><c:out value="${s.type}"/></td>
	             	</tr>
	            </c:forEach>
        	</tbody>
		</table>
	</div>
	<div id="footer">
		<p>Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 © Copyright 2022</p>
	  	
	</div>
	<script src="${pageContext.request.contextPath}/js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>