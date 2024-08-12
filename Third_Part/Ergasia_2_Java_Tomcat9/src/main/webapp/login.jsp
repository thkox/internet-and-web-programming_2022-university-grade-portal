<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;700&display=swap" rel="stylesheet"/>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
		<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/allpages.css" rel="stylesheet">
		<title>Login Page</title>
	</head>
	<body>
	
	<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
	char ch;
	
	if((session.getAttribute("username")) != null)
	{
	    String registNumber = (session.getAttribute("username")).toString();    //temp variable to keep username untouchable
	    ch = registNumber.charAt(0);    //Keep the first char which is the role
		
		switch(ch) {
			case 'S':
				response.sendRedirect("secretaryPages/profile.jsp");
		    	break;
			case 'P':
				response.sendRedirect("professorPages/profile.jsp");
		    	break;
			case 'F':
				response.sendRedirect("studentPages/profile.jsp");  	
		    	break;
		  	default:
		  		response.sendRedirect("index.jsp");	
		}
	}
	
	%>	
	
	<div class="login-div">
		<div class="title">University</div>
		<div class="sub-title">Form login</div>
		<form action="login" method="POST">
			<div class="fields">
				<div class="username">
					<span class="material-symbols-outlined">person</span>
					<input type="text" id="username" name="username" class="user-input" placeholder="Username" pattern="[PFS]{1}\d{5}" required>
<!-- Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069 -->					
				</div>
				<div class="password">
					<span class="material-symbols-outlined">password</span>
					<input type="password" id="password" name="password" class="password-input" placeholder="Password" required>
				</div>
			</div>
			<button class="signin-button">Login</button>
			<br>
			<div style="color:red; text-align:center;">${error}</div>
		</form>
	</div>
	</body>
</html>