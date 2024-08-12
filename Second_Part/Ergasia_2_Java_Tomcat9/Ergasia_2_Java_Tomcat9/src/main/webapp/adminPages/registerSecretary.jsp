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
		  		<div id="error"></div>
		  	</div>
		  	<div class="row" id="registrationNumber_div">
		      <div class="col-25">
		        <label for="registrationNumber">Registration Number</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="registrationNumber" name="registrationNumber" placeholder="">
		      </div>
		      <div id="registrationNumber_error"></div>
		    </div>
		    <div class="row" id="name_div">
		      <div class="col-25">
		        <label for="name">Name</label>
		      </div>
		      <div class="col-75">
		        <input type="text" id="name" name="name" maxlength="20" placeholder="" >
		      </div>
		      <div id="name_error"></div>
		    </div>
		    <div class="row" id="surname_div">
		      <div class="col-25">
		        <label for="surname">Surname</label>
		      </div>
		      <div class="col-75">
		        <input type="text" id="surname" name="surname" placeholder="" required>
		      </div>
		      <div id="surname_error"></div>
		    </div>
		    <div class="row" id="afm_div">
		      <div class="col-25">
		        <label for="afm">AFM number</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="afm" name="afm" placeholder="" required>
		      </div>
		      <div id="afm_error"></div>
		    </div>
		    <div class="row" id="acadyearadmission_div">
		      <div class="col-25">
		        <label for="acadyearadmission">Academic Year Admission</label>
		      </div>
		      <div class="col-75">
		        <input type="number" id="acadyearadmission" name="acadyearadmission" placeholder="" required>
		      </div>
		      <div id="acadyearadmission_error"></div>
		    </div>
		    <div class="row" id="birthdate_div">
		      <div class="col-25">
		        <label for="birthdate">Birth Date</label>
		      </div>
		      <div class="col-75">
		        <input type="date" id="birthdate" name="birthdate" placeholder="" required>
		      </div>
		      <div id="birthdate_error"></div>
		    </div>
		    <div class="row" id="email_div">
		      <div class="col-25">
		        <label for="email">E-mail</label>
		      </div>
		      <div class="col-75">
		        <input type="email" id="email" name="email" placeholder="" required>
		      </div>
		      <div id="email_error"></div>
		    </div>
		    <div class="row" id="password_div">
		      <div class="col-25">
		        <label for="password">Password</label>
		      </div>
		      <div class="col-75">
		        <input type="password" id="password" name="password" placeholder="" required>
		      </div>
		    </div>
		    <div class="row" id="repeatpassword_div">
		      <div class="col-25">
		        <label for="repeatpassword">Repeat Password</label>
		      </div>
		      <div class="col-75">
		        <input type="password" id="repeatpassword" name="repeatpassword" placeholder="" required>
		      </div>
		      <div id="password_error"></div>
		    </div>
		    <div class="row">
		      <br>
		      <input type="submit" value="Submit">
		    </div>
		  </form>
		</div>
		  <script src="${pageContext.request.contextPath}/js/validationsecretaryform.js"></script>
	</body>
</html>