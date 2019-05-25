<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/Header.css">
</head>

<div id="logo">
	<img src="../images/logo.png">
</div>

<div id="searchbar">
	<form name="searchbar" action="Search" method=GET>
		<input type="text" placeholder="Type to search">
		<button type="submit">
			<img alt="search" src="../images/search.png">
		</button>
	</form>
</div>

<div id="services_icons">
	<span id="account">
		<a href="account.jsp"> <img src="../images/account.png"> </a>
	</span>
	
	<span id="wishlist">
		<a href="wishlist.jsp"> <img src="../images/wishlist.png"> </a>
	</span>
	
	<span id="cart">
		<a href="cart.jsp"> <img src="../images/cart.png"> </a>
	</span>
	
	<span id="faq">
		<a href="faq.jsp"> <img src="../images/faq.png"> </a>
	</span>
	
	<span id="info">
		<a href="info.jsp"> <img src="../images/info.png"> </a>
	</span>
</div>