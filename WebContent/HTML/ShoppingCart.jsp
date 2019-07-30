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
		<script src="/DaemonMerch/js/scriptJS.js"></script>
	  <script src="/DaemonMerch/js/Ajax.js"></script>
	</head>

	<body>

    <div class="selector" oninput="changeCurrencyInCart()">
      <select id="selectCurrency">
        <option value="EUR"> EUR </option>
        <option value="USD"> USD </option>
      </select>
    </div>
		<div class="grid-container">

			<%Cart cart = (Cart)request.getSession(false).getAttribute("cart"); %>
			<%float total = 0; %>

      <!-- TODO: Funzione js che quando è 0 stampa di nuobo qyesto -->
			<%if(cart==null || cart.getNumberOfProduct() == 0) { %>
				<div class="empty">The cart is empty, add something!</div>
			<% }

			else {%>

				<ul class="list-of-products">


					<%for(int i = 0; i < cart.getNumberOfProduct(); i++) { %>
					<%total += cart.getProduct(i).getPrice();%>
						<li class="product" id="product<%out.print(i);%>">
							<%Product currentProduct = (Product) cart.getProduct(i);%>
							<%Image[] images = currentProduct.getImages(); %>
							<button class="button" onclick="deleteProductFromCart(<%=i%>, <%=total%>, <%=currentProduct.getPrice()%>)">X</button>
							<a>
								<div>
									<img class="image" src="/DaemonMerch/Images/<%=images[0].getImageName()%>?id=<%=currentProduct.getId()%>">
								</div>
							</a>
								<h3 class="name"> <%out.println(currentProduct.getName());%> </h3>
								<h4 class="price"> <%out.println(currentProduct.getPrice() + "0 EUR");%> </h4>
						</li>

						</ul>

				<% } %>
					<div class="total" id="total">
						<h3 id="totalPrice"><%out.println("Total: " + total + "0 EUR");%></h3>
						<button onclick="window.location.href='/DaemonMerch/auth/Checkout'">Checkout</button>
					</div>

			<% } %>

		</div>
	</body>
</html>
