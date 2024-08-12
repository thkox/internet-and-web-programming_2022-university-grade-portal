<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Your Grades</title>
	<link href="css/sidenavbar.css" rel="stylesheet">
	<link href="css/topnavbar.css" rel="stylesheet">
</head>
<body>
	<div id="mySidenav" class="sidenav">
	  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	  <a class="department">INFORMATICS</a>
	  <a href="profile.jsp">Profile</a>
	  <a href="courses.jsp">Courses</a>
	  <a class="enabled">Grades</a>
	  <a></a>
	  <a href="#">Logout</a>
	</div>
	
	<div id="main">
		<div class="header">
			<a class="logo">
				<span style="font-size:20px; cursor:pointer" onclick="openNav()">&#9776; Menu</span>
			</a>
			
		  	<div class="header-right">
		    	<a href="profile.jsp">Profile</a>
		 	</div>
		</div>
		<h2>Sidenav Push Example</h2>
		<p>Click on the element below to open the side navigation menu, and push this content to the right. Notice that we add a black see-through background-color to body when the sidenav is opened.</p>
	</div>
	
	<script src="js/sidenavbar.js"></script>
</body>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
</html>