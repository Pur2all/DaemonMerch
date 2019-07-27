<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"
    import="control.servlet.webapp.*"%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="./" target="_self">
		<link rel="stylesheet" type="text/css" href="/DaemonMerch/Css/Header.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="/DaemonMerch/js/Ajax.js"></script>
		<script src="/DaemonMerch/js/scriptJS.js"></script>
	</head>

	<body>
		<div class="header-grid" >
			<logo class="logo-item">
				<a href="/DaemonMerch/Home">
					<img src="/DaemonMerch/images/logo.png" class="logo-image" width="200">
				</a>
			</logo>

			<div class="wrap-item">
				<form action="/DaemonMerch/SearchProduct" method="get">
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
					<a href="/DaemonMerch/Profile" title="Account" class="services-link"> <img src="/DaemonMerch/images/account.png" alt="Account" class="services-image"> </a>
				</span>

				<span id="wishlist" class="service-flex-item">
					<a href="/DaemonMerch/Wishlist" title="Wishlist" class="services-link"> <img src="/DaemonMerch/images/wishlist.png" alt="Wishlist" class="services-image"> </a>
				</span>

				<span id="cart" class="service-flex-item">
					<a href="/DaemonMerch/auth/Cart" title="Cart" class="services-link"> <img src="/DaemonMerch/images/cart.png" alt="Cart" class="services-image"> </a>
				</span>

				<span id="faq" class="service-flex-item">
					<a href="/DaemonMerch/Faq" title="FAQ" class="services-link"> <img src="/DaemonMerch/images/faq.png" alt="FAQ" class="services-image"> </a>
				</span>
			</div>

			<div class="navbar">
				<nav role="navigation">
					<ul>
						<div>
							<li><a class="animated" href="/DaemonMerch/Artists">Band List</a></li>
							<li><a class="animated" href="/DaemonMerch/Tops?pageInit=0&pageEnd=15">Top</a></li>
							<li><a class="animated" href="/DaemonMerch/Patches?pageInit=0&pageEnd=15">Patch</a></li>
							<li><a class="animated" href="/DaemonMerch/Products?pageInit=0&pageEnd=15">Other</a></li>
						</div>
						<%
							if(request.getSession(false) == null || request.getSession(false).getAttribute("userInfo") == null) {
						%>
						<li><a class="animated" href="/DaemonMerch/LoginForm">LOGIN</a></li>
						<%
						 	  }
							  else {
						%>
						<li><a class="animated" href="/DaemonMerch/auth/Logout">LOGOUT</a></li>
						<%
							}
						%>

					</ul>
				</nav>

			</div>
		</div>
	</body>
</html>
