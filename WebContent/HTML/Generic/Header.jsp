<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/Header.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<div class="header-grid" >
		<logo class="logo-item">
			<img src="./images/logo.png" width="200">
		</logo>
	
		<div class="wrap-item">
	 	  <div class="searchbar-item">
	   	 	 <input type="text" class="searchTerm" placeholder="What are you looking for?">
	     	 <button type="submit" class="searchButton">
	     	 	<i class="fa fa-search"></i>
	     	 </button>
	  	  </div>	
		</div>
	
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
				<a href="Generic/faq.jsp" title="FAQ" class="services-link"> <img src="./images/faq.png" alt="FAQ" class="services-image"> </a>
			</span>
		</div>
		
		<div class="navbar">
<<<<<<< HEAD:WebContent/Generic/Header.jsp
			<nav role="navigation">
				<ul>
					<li><a href="#">Meme</a></li>
					<li><a href="#">Memone</a>
						<ul class="dropdown">
							<li><a href="#">Sub-1</a></li>
							<li><a href="#">Sub-2</a></li>
							<li><a href="#">Sub-3</a></li>
						</ul>
					</li>
					<li><a href="#">Gianni</a></li>
					<li><a href="#">Gianni2</a></li>
				</ul>
			</nav>

			<%@include file="Navbar.jsp" %>
		</div>
	</div>
</body>