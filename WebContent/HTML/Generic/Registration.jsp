<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="Registration.css">
	<script src="myscripts.js"></script> 
	<script type="text/javascript">
	 $(function() {
		 $("#datepicker").datepicker();
		 });
	</script>
</head>

<body>

	<div class='login'>
		<h2>Registrazione</h2>
		<input name='first-name' placeholder='Nome' type='text'>
		<input name='last-name' placeholder='Cognome' type='text'>
		<input id="datepicker" placeholder='Data di Nascita'>
		<input name='username' placeholder='Nome Utente' type='text'>
		<input id='pw' name='password' placeholder='Password' type='password'>
		<input name='email' placeholder='Indirizo e-mail' type='text'>
		<div class='agree'>
			<input id='agree' name='agree' type='checkbox'>
			<label for='agree'></label>Accetta i termini e le condizioni
		</div>
		<input class='animated' type='submit' value='Registrati!'>
		<a class='forgot' href='#'>Sei già registrato?</a>
	</div>

</body>