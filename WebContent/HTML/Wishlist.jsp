<%@page import="com.google.gson.Gson"%>
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


		<div class="grid-container">

			<%LinkedList<WishlistProduct> wishlist = (LinkedList<WishlistProduct>)request.getAttribute("wishlistProducts"); %>
			<%float total = 0; %>

			<%if(wishlist == null || wishlist.size()==0) { %>
				<div class="empty">The wishlist is empty, add something!</div>
			<% }

			else {%>

				<ul class="list-of-products">


					<%for(int i = 0; i < wishlist.size(); i++) { %>
					<%total += wishlist.get(i).getPrice();%>
						<li class="product" id="product<%out.print(i);%>">
							<%WishlistProduct currentProduct = (WishlistProduct) wishlist.get(i);%>
							<%Image[] images = currentProduct.getImages(); %>
							<button class="button" onclick="deleteProductFromWishlist(<%= currentProduct.getId()%>, <%=i%>);">X</button>
							<a>
								<div>
									<img class="image" src="/DaemonMerch/Images/<%=images[0].getImageName()%>?id=<%=currentProduct.getId()%>">
								</div>
							</a>
								<h3 class="name"> <%out.println(currentProduct.getName());%> </h3>
								<h4 class="price"> <%out.println(currentProduct.getPrice() + "0 EUR");%> </h4>
								<button class="button addW" onclick="deleteProductFromWishlist(<%= currentProduct.getId()%>, <%=i%>); addToCart(<%= currentProduct.getId()%>);">Add To Cart</button>
						</li>

						</ul>

				<% } %>
			<% } %>

		</div>

	</body>
</html>
