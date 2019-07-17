<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
	<head>
		<meta name="author" content="Francesco Migliaro, Cosimo Botticelli">
		<meta name="description" content="We sell metal band merchandise">
		<meta name="keywords" lang="eng" content="Metal, Thrash Metal, Black Metal, Death Metal, Merchandise, Metal Band">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
		<link rel="icon" href="../Images/icon.jpg">
		<link rel="stylesheet" type="text/css" href="./Css/Index.css">
		<title>DaemonMerch</title>
	</head>
	<body>
	<div class="index-grid">
  	  <header class="index-header">
  	    <%@ include file="./Header.jsp" %>
  	  </header>

 	   <main class="index-main">
 	   	<% String pageName=(String) request.getAttribute("mainPage"); %>
  	  	<%@ include file="./Main.jsp" %>
  	  </main>

   	  <footer class="index-footer">
   	   <%@ include file="./Footer.jsp" %>
  	  </footer>
   </div>

	</body>
</html>
