<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
	<head>
		<meta name="author" content="Francesco Migliaro, Cosimo Botticelli">
		<meta name="description" content="We sell metal band merchandise">
		<meta name="keywords" lang="eng" content="Metal, Thrash Metal, Black Metal, Death Metal, Merchandise, Metal Band"> <!-- Serve a farsi trovare dai motori di ricerca inserendo keywords -->
		<meta charset="utf-8">
		<base href="../images/" target="_blank"> <!-- Serve a definire una sorta di standard per tutti i link del documento, l'href serve a dare una direcotry base a tutti i link e quindi si possono evitare path assoluti quando si pu�, mentre il target definisce il target base di tutti i link -->
		<link rel="icon" href="icon.jpg"> <!-- Serve a dare un link a determinati oggetti del documento -->
		<title>DaemonMerch</title>
	</head>
	<body>
    <div id="header">
      <%@ include file="../Generic/Header.jsp" %>
    </div>
    <h1> <a href=Home> boh</a> Roba </h1>
    <div id="footer">
      <%@ include file="../Generic/Footer.jsp" %>
    </div>
	</body>
</html>
