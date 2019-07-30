<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
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
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="./Css/ProductsList.css">
		<script src="/DaemonMerch/js/scriptJS.js"></script>
	    <script src="/DaemonMerch/js/Ajax.js"></script>

	</head>

	<body onload="addPrevRow()">


		<div class="grid-container">

		<div class="addedToCart" style="display: none;">
			<h5 class="checkMessage">Product added to cart!</h5>
			<img class="checkIcon" alt="" src="http://www.pngmart.com/files/3/Green-Tick-Transparent-PNG.png">
		</div>

		<div class="addedToWishlist" style="display: none;">
			<h5 class="checkMessage">Product added to wishlist!</h5>
			<img class="checkIcon" alt="" src="https://www.graphiclibrary.com/wp-content/uploads/2019/03/big-bold-red-heart_8-1.png">
		</div>

			<ul class="product-list">

				<%LinkedList<Product> products = (LinkedList<Product>)request.getAttribute("products"); %>

				<c:forEach items="${products}" var="currentProduct">
					<li class="product">
						<a href="Product?id=${currentProduct.id}&type=<%=pageContext.getAttribute("currentProduct").getClass().getSimpleName().toLowerCase()%>">
							<div>
								<img class="image" src="Images/${currentProduct.images[0].imageName}?id=${currentProduct.id}">
							</div>
						</a>

							<h3 class="name"> <c:out value="${currentProduct.name}"> </c:out></h3>
							<h4 class="price"> <c:out value="${currentProduct.price}0 EUR"> </c:out></h4>

							<%if(request.getSession(false)!=null && request.getSession(false).getAttribute("userInfo")!=null)
								{%>
									<button class="button addToCartButton" onclick="addToCart(${currentProduct.id})">Add to Cart</button>
								<%}
							else
								{%>
									<button class="button addToCartButton" onclick="window.location.href='/DaemonMerch/LoginForm'">Add to Cart</button>
								<% } %>

							<%if(request.getSession(false)!=null && request.getSession(false).getAttribute("userInfo")!=null)
								{%>
									<button class="button wishlist-button" onclick="addToWishlist(${currentProduct.id})"><i class="fa fa-heart"></i></button>
								<%}
							else
								{%>
									<button class=" button wishlist-button" onclick="window.location.href='/DaemonMerch/LoginForm'"><i class="fa fa-heart"></i></button>
								<% } %>
					</li>
				</c:forEach>

			</ul>

		</div>

	<div class="rows">
	    <%if(products.size()==17)
	    {%>
		      <button id="next" onclick="changePage(1)"> -> </button>
	    <%}%>
    </div>

	</body>
</html>
