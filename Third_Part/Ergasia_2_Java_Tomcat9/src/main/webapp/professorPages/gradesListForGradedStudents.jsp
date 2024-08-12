<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Assign Grades to <c:out value="${GradesCourse[1].course.title}"/></title>
	<link href="${pageContext.request.contextPath}/css/sidenavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/topnavbar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/tabledata.css" rel="stylesheet">
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
	<div id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<a class="department">INFORMATICS</a>
		<a href="${pageContext.request.contextPath}/professorPages/profile.jsp">Profile</a>
		<a href="${pageContext.request.contextPath}/CourseController?action=listCourseByProfessor&registNumber=<%=session.getAttribute("username") %>">Courses</a>
		<a class="enabled">Grades</a>
	  <a></a>
	  <a href="${pageContext.request.contextPath}/logout">Logout</a>
	</div>
	<div id="main">
		<div class="header">
			<a class="logo">
				<span style="font-size:20px; cursor:pointer" onclick="openNav()">&#9776; Main menu</span>
			</a>
			<div class="header-center">
				<a class="active">Grades List Per Course</a>
				<a href="${pageContext.request.contextPath}/CourseController?action=selectCourseAssign&registNumber=<%=session.getAttribute("username") %>">Assign Grades Per Course</a>
			</div>
			<div class="header-right">
				<a href="${pageContext.request.contextPath}/professorPages/profile.jsp"><%=session.getAttribute("username") %></a>
			</div>
		</div>
		<h2><c:out value="${GradesCourse[1].course.title}"/></h2>
		<form method="GET" action="CourseController" name="frmselectCourse">
			<table  class="content-table">
		        <thead>
		            <tr>
		                <th>A.M.</th>
		                <th>Exercise's Grade</th>
		                <th>Final Exam Grade</th>
		                <th>Final Grade</th>
		                <th>Action</th>
		            </tr>
		        </thead>
		        <tbody>
		           	<c:forEach items="${requestScope.GradesCourse}" var="s">
		                <tr>
		                	<td><c:out value="F${s.student.registrationNumber}"/></td>
		                	<td><c:out value="${s.course.exerGrade}"/></td>
		                	<td><c:out value="${s.course.examGrade}"/></td>
		                    <td><c:out value="${s.course.finalGrade}"/></td>
		                   <td><a href="${pageContext.request.contextPath}/ProfessorController?action=editGrades&IdCourse=<c:out value="${s.course.idcourse}"/>&RegistNumber=<c:out value="${s.student.registrationNumber}"/>">Edit Grades</a></td>
		             	</tr>
		            </c:forEach>
	        	</tbody>
			</table>
		</form>
	</div>
	<div id="footer">
		<p>Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 © Copyright 2022</p>
	  	
	</div>
	<script src="${pageContext.request.contextPath}/js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>