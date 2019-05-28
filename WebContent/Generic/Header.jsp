<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/Header.css">
</head>

<div id="logo" class="flex-item">
	<img src="../images/logo.png" width="175">
</div>

<div id="searchbar" class="flex-item">
	<form name="searchbar" action="Search" method=GET>
		<input type="text" placeholder="Type to search">
		<button type="submit">
			<img alt="search" src="../images/search.png">
		</button>
	</form>
</div>

<div class="services-flex-container">
	<span id="account" class="service-flex-item">
		<a href="account.jsp"> <img src="../images/account.png"> </a>
	</span>
	
	<span id="wishlist" class="service-flex-item">
		<a href="wishlist.jsp"> <img src="../images/wishlist.png"> </a>
	</span>
	
	<span id="cart" class="service-flex-item">
		<a href="cart.jsp"> <img src="cart.png" alt="Cart"> </a>
	</span>
	
	<span id="faq" class="service-flex-item">
		<a href="faq.jsp"> <img src="../images/faq.png"> </a>
	</span>
	
	<span id="info" class="service-flex-item">
		<a href="info.jsp"> <img src="../images/info.png"> </a>
	</span>
</div>