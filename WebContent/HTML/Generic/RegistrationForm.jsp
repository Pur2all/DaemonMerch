<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="../../Css/RegistrationLoginForm.css">
</head>

<body>

	<div class='registrationLoginForm'>
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
		<a class='forgot' href='LoginForm.jsp'>Do you already have an account?</a>
	</div>

</body>