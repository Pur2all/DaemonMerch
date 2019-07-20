<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"
    import="control.servlet.webapp.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/Header.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">

	</script>
</head>

<body>
	<div class="header-grid" >
		<logo class="logo-item">
			<img src="./images/logo.png" width="200">
		</logo>

		<div class="wrap-item">
			<form action="/SearchProduct" method="get">
				<div class="searchbar-item">
					<input type="hidden" name="pageInit" value='0'>
					<input type="hidden" name="pageEnd" value='15'>
					<input type="text" class="searchTerm" placeholder="Search..." name="q">
					<button type="submit" class="searchButton">
	     	 		<i class="fa fa-search"></i>
	     	 		</button>
	     	 	</div>
			</form>
		</div>

		<div class="services-icons">
			<span id="account" class="service-flex-item">
				<a href="/CheckIfLogged" title="Account" class="services-link"> <img src="./images/account.png" alt="Account" class="services-image"> </a>
			</span>

			<span id="wishlist" class="service-flex-item">
				<a href="Wishlist" title="Wishlist" class="services-link"> <img src="./images/wishlist.png" alt="Wishlist" class="services-image"> </a>
			</span>

			<span id="cart" class="service-flex-item">
				<a href="Cart" title="Cart" class="services-link"> <img src="./images/cart.png" alt="Cart" class="services-image"> </a>
			</span>

			<span id="faq" class="service-flex-item">
				<a href="Faq" title="FAQ" class="services-link"> <img src="./images/faq.png" alt="FAQ" class="services-image"> </a>
			</span>
		</div>

		<a class="services-icons" href="/auth/Logout">LOGOUT</a>

		<div class="navbar">
			<nav role="navigation">
				<ul>
					<li><a href="./ArtistsPage">Band List</a></li>
					<li><a href="#">Top</a></li>
					<li><a href="#">Patch</a></li>
					<li><a href="#">Other</a></li>
				</ul>
			</nav>

		</div>
	</div>
</body>
