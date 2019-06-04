<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/Header.css">
</head>

<div class="header-grid" >
	<logo class="logo-item">
		<img src="./images/logo.png" width="200">
	</logo>

	<searchbar class="searchbar-item">
		<form name="searchbar" action="Search" method=GET>
			<input type="text" placeholder="Type to search">
			<button type="submit">
				<img alt="search" src="../images/search.png">
			</button>
		</form>
	</searchbar>

	<div class="services-icons">
		<span id="account" class="service-flex-item">
			<a href="account.jsp" title="Account" class="services-link"> <img src="./images/account.png" alt="Account" class="services-image"> </a>
		</span>
		
		<span id="wishlist" class="service-flex-item">
			<a href="wishlist.jsp" title="Wishlist" class="services-link"> <img src="./images/wishlist.png" alt="Wishlist" class="services-image"> </a>
		</span>
		
		<span id="cart" class="service-flex-item">
			<a href="cart.jsp" title="Cart" class="services-link"> <img src="./images/cart.png" alt="Cart" class="services-image"> </a>
		</span>
		
		<span id="faq" class="service-flex-item">
			<a href="faq.jsp" title="FAQ" class="services-link"> <img src="./images/faq.png" alt="FAQ" class="services-image"> </a>
		</span>
	</div>
</div>