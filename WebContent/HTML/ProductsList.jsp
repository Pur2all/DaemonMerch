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
		<link rel="stylesheet" type="text/css" href="./Css/ProductsList.css">
	</head>

	<body>


		<div class="grid-container">

			<ul class="product-list">

				<%LinkedList<Product> products = (LinkedList<Product>)request.getAttribute("products"); %>

				<c:forEach items="${products}" var="currentProduct">
					<li class="product">

						<a>
							<div>
								<img class="image" src="Images/${currentProduct.images[0].imageName}?id=${currentProduct.id}">
							</div>
						</a>
							<h3 class="name"> <c:out value="${currentProduct.name}"> </c:out></h3>
							<h4 class="price"> <c:out value="${currentProduct.price}0 EUR"> </c:out></h4>
							
							<%if(request.getSession(false)!=null && request.getSession(false).getAttribute("userInfo")!=null)
								{%>
									<button class="button" onclick="addToCart(${currentProduct.id})">Add to Cart</button>
								<%}
							else
								{%>
									<button class="button" onclick="window.location.href='/DaemonMerch/LoginForm'">Add to Cart</button>
								<% } %>
								
							<%if(request.getSession(false)!=null && request.getSession(false).getAttribute("userInfo")!=null)
								{%>
									<button class="wishlist-button" onclick="addToWishlist(${currentProduct.id})">33</button>
								<%}
							else
								{%>
									<button class="wishlist-button" onclick="window.location.href='/DaemonMerch/LoginForm'">33</button>
								<% } %>
					</li>
				</c:forEach>

			</ul>

		</div>

	</body>
</html>
