<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/RegistrationLoginForm.css">
</head>

<body>

	<div class='registrationLoginForm'>
<<<<<<< HEAD
		<form method="post" action="servlet/Login">
			<h2>Sign up</h2>
=======
		<form action="Login" method="post">
			<h2>Log in</h2>
>>>>>>> branch 'master' of https://github.com/Pur2all/DaemonMerch.git
			<input name='username' placeholder='Username' type='text'>
			<input id='pw' name='password' placeholder='Password' type='password'>
			<input class='animated' type='submit' value='Submit'>
			<a class='forgot' href='RegistrationForm.jsp'>Don't have an account?</a>
		</form>
	</div>

</body>
