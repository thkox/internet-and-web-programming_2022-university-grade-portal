/**
 * 
 */

// SELECTING ALL TEXT ELEMENTS
 
 var registrationNumber = document.forms['form']['registrationNumber'];
 var name = document.forms['form']['name'];
 var surname = document.forms['form']['surname'];
 var afm = document.forms['form']['afm'];
 var birthdate = document.forms['form']['birthdate'];
 var email = document.forms['form']['email'];
 var password = document.forms['form']['password'];
 var repeatpassword = document.forms['form']['repeatpassword'];
  
 // SELECTING ALL ERROR DISPLAY ELEMENTS
 var registrationNumber_error = document.getElementById('registrationNumber_error');
 var name_error = document.getElementById('name_error');
 var surname_error = document.getElementById('surname_error');
 var afm_error = document.getElementById('afm_error');
 var birthdate_error = document.getElementById('birthdate_error');
 var email_error = document.getElementById('email_error');
 var password_error = document.getElementById('password_error');
 
 
 // SETTING ALL EVENT LISTENERS
 registrationNumber.addEventListener('blur', registrationNumberVerify, true);
 name.addEventListener('blur', nameVerify, true);
 surname.addEventListener('blur', surname, true);
 afm.addEventListener('blur', afmVerify, true);
 birthdate.addEventListener('blur', birthdateVerify, true);
 email.addEventListener('blur', emailVerify, true);
 password.addEventListener('blur', passwordVerify, true);
 
 
 // validation function
  
 function validate()
 {
	// validate name
  	if (name.value == "") {
    name.style.border = "1px solid red";
    document.getElementById('username_div').style.color = "red";
    name_error.textContent = "Username is required";
    name.focus();
    return false;
  }
  
  // event handler functions
 function nameVerify() {
  if (name.value != "") {
   name.style.border = "1px solid #5e6e66";
   document.getElementById('username_div').style.color = "#5e6e66";
   name_error.innerHTML = "";
   return true;
  }
}
 }