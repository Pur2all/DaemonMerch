<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/RegistrationLoginForm.css">
</head>

<body>
	<div class='registrationLoginForm'>
		<form method="post" action="Login">
			<h2>Log in</h2>
			<input name='username' placeholder='Username' type='text'>
			<input id='pw' name='password' placeholder='Password' type='password'>
			<input class='animated' type='submit' value='Submit'>
			
						
			<div class="errorMessage">
			<%if((Boolean)request.getAttribute("LoginError")) {
				out.println("Login error message");
			} %>
			</div>
			
			<div class="errorMessage">
			<%if((Boolean)request.getAttribute("ProfileError")) {
				out.println("ProfileError message");
			} %>
			</div>
			
			
			<a class='forgot' href='RegistrationForm'>Don't have an account?</a>
		</form>
	</div>

</body>
