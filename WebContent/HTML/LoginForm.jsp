<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/RegistrationLoginForm.css">
</head>

<body>

	<div class='registrationLoginForm'>
		<form method="post" action="servlet/Login">
			<h2>Sign up</h2>
			<input name='username' placeholder='Username' type='text'>
			<input id='pw' name='password' placeholder='Password' type='password'>
			<input class='animated' type='submit' value='Submit'>
			<a class='forgot' href='RegistrationForm'>Don't have an account?</a>
		</form>
	</div>

</body>
