<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"
    import="java.util.LinkedList"
    %>
    <%@
    	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
    %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>DaemonMerch</title>
		<link rel="stylesheet" type="text/css" href="/DaemonMerch/Css/WishlistCart.css">
	</head>

	<body>

		<h2 id="heading">Carrelloide!</h2>

		<div class="grid-container">

			<ul class="list-of-products">

				<%Cart cart = (Cart)request.getSession(false).getAttribute("cart"); %>


				<%for(int i = 0; i < cart.getNumberOfProduct(); i++) { %>
					<li class="product">
						<%Product currentProduct = (Product) cart.getProduct(i);%>
						<%Image[] images = currentProduct.getImages(); %>
						<button class="button">x</button>
						<a>
							<div>
								<img class="image" src="/DaemonMerch/Images/<%=images[0].getImageName()%>?id=<%=currentProduct.getId()%>">
							</div>
						</a>
							<h3 class="name"> <%out.println(currentProduct.getName());%> </h3>
							<h3 class="price"> <%out.println(currentProduct.getPrice() + "0 EUR");%> </h3>
					</li>

				<% } %>

			</ul>

			<div class="total">
				<h3>totaleeee</h3>
				<button onclick="window.location.href='auth/Checkout'">Checkout</button>
			</div>

		</div>
	</body>
</html>
