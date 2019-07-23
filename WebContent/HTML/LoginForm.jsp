<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>
	
<!DOCTYPE html>
<html>
	<head>
		<base href="./" target="_self">
		<link rel="stylesheet" type="text/css" href="./Css/RegistrationLoginForm.css">
	 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="./js/scriptJS.js"></script>
		
	</head>
	
	<body onload="loginForm()">
	

		<a class='toggle-button'>Don't have an account?</a>
					
		<div class='loginForm'>
			<form method="post" action="Login">
				<h2>Log in</h2>
				<input name='username' placeholder='Username' type='text'>
				<input id='pw' name='password' placeholder='Password' type='password'>
				<input class='animated' type='submit' value='Submit'>
				
							
				<div class="errorMessage">
				<%if((Boolean)request.getAttribute("loginError")!=null) {
					out.println("Login error message");
				} %>
				</div>
				
				<div class="errorMessage">
				<%if((Boolean)request.getAttribute("profileError")!=null) {
					out.println("ProfileError message");
				} %>
				</div>
				
			</form>
		</div>

		<div class='registrationForm'>
			<h2>Sign up</h2>
			<form action="Registration" method="post">
				<input name='name' placeholder='First Name' type='text'>
				<input name='surname' placeholder='Last Name' type='text'>
				<input name="birthday" type="date" placeholder='Birth Date'>
				<input name='username' placeholder='Username' type='text'>
				<input id='pw' name='password' placeholder='Password' type='password'>
				<div class='agree'>
					<input id='agree' name='agree' type='checkbox'>
					<label for='agree'></label>Accept terms and conditions
				</div>
				<input class='animated' type='submit' value='Submit'>
			</form>
		</div>
	
	</body>
</html>