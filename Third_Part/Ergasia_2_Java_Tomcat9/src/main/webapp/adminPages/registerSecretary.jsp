<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;700&display=swap" rel="stylesheet"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link href="${pageContext.request.contextPath}/css/allpages.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
	<title>Register Secretary</title>
	</head>
	<body>
		<h2>Secretary Form</h2>
		<p>Create new secretary account! This is ONLY available to administration.</p>
		<div class="container">
		  <form action="../RegisterSecretaryServlet" method="POST" name="form" onsubmit="return validate()">
		  	<div class="row">
		  	</div>
		  	<div class="row" id="registrationNumber_div">
		      <div class="col-25">
		        <label for="registrationNumber">Registration Number</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="registrationNumber" name="registrationNumber" placeholder="Enter Registration Number"  min="10000" max="99999" required>
		      </div>
		    </div>
		    <div class="row" id="name_div">
		      <div class="col-25">
		        <label for="name">Name</label>
		      </div>
		      <div class="col-75">
		        <input type="text" id="name" name="name" maxlength="20" placeholder="Enter Name" pattern="[a-zA-Z]{2,}" required>
		      </div>
		    </div>
		    <div class="row" id="surname_div">
		      <div class="col-25">
		        <label for="surname">Surname</label>
		      </div>
		      <div class="col-75">
		        <input type="text" id="surname" name="surname" placeholder="Enter Surname" pattern="[a-zA-Z]{2,}" required>
		      </div>
		    </div>
		    <div class="row" id="afm_div">
		      <div class="col-25">
		        <label for="afm">AFM number</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="afm" name="afm" placeholder="Enter AFM Number" min="100000000" max="999999999" required>
		      </div>
		    </div>
		    <div class="row" id="acadyearadmission_div">
		      <div class="col-25">
		        <label for="acadyearadmission">Academic Year Admission</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="acadyearadmission" name="acadyearadmission" placeholder="Enter Academic Year Admission" min="1991" max="2022" required>
		      </div>
		    </div>
		    <div class="row" id="birthdate_div">
		      <div class="col-25">
		        <label for="birthdate">Birth Date</label>
		      </div>
		      <div class="col-75">
		        <input type="date" id="birthdate" name="birthdate" placeholder="Enter Birth Date" min="1950-01-01" max="2003-12-31" required>
		      </div>
		    </div>
		    <div class="row" id="email_div">
		      <div class="col-25">
		        <label for="email">E-mail</label>
		      </div>
		      <div class="col-75">
		        <input type="email" id="email" name="email" placeholder="Enter E-mail" required>
		      </div>
		    </div>
		    <div class="row" id="password_div">
		      <div class="col-25">
		        <label for="password">Password</label>
		      </div>
		      <div class="col-75">
		        <input type="password" id="password" name="password" placeholder="Enter Password" required>
		      </div>
		    </div>
		    <div class="row" id="repeatpassword_div">
		      <div class="col-25">
		        <label for="repeatpassword">Repeat Password</label>
		      </div>
		      <div class="col-75">
		        <input type="password" id="repeatpassword" name="repeatpassword" placeholder="Repeat Password" required>
		      </div>
		    </div>
		    <div class="row">
		      <br>
		      <input type="submit" value="Submit">
		    </div>
		  </form>
		</div>
	</body>
</html>