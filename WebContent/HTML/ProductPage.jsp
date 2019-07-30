<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>
    <%@
    	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
    %>
    
<!DOCTYPE html>

<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="./Css/ProductPage.css">
    	<script src="/DaemonMerch/js/scriptJS.js"></script>
	    <script src="/DaemonMerch/js/Ajax.js"></script>
    </head>

	<body>
	
		<div class="addedToCart" style="display: none;">
			<h5 class="checkMessage">Product added to cart!</h5>
			<img class="checkIcon" alt="" src="http://www.pngmart.com/files/3/Green-Tick-Transparent-PNG.png">
		</div>
	

		<%if(request.getSession(false) == null) {%>

		<%} else { %>	
			<div class="productContainer">
				<%Product product = (Product)request.getAttribute("product"); %>
				<img class="image" src="Images/${product.images[0].imageName}?id=${product.id}">
										
				<h3 class="name"> <%out.print(product.getName() + " (" + product.getClass().getSimpleName() + ")"); %></h3>
				<h4 class="price"> <c:out value="${product.price}0 EUR"> </c:out></h4>
				<h6 class="description"> <c:out value="${product.description}"> </c:out></h6>
				
				<%if(request.getSession(false)!=null && request.getSession(false).getAttribute("userInfo")!=null)
					{%>
						<button class="button addToCartButton" onclick="addToCart(${product.id})">Add to Cart</button>
					<%}
				else
					{%>
						<button class="button" onclick="window.location.href='/DaemonMerch/LoginForm'">Add to Cart</button>
					<% } %>
					
				<%if(request.getSession(false)!=null && request.getSession(false).getAttribute("userInfo")!=null)
					{%>
						<button class="wishlist-button button" onclick="addToWishlist(${product.id})">33</button>
					<%}
				else
					{%>
						<button class="wishlist-button button" onclick="window.location.href='/DaemonMerch/LoginForm'">33</button>
					<% } %>

				<%if(product.getClass().getSimpleName().equals("Top")) {%>
					<h5 class="info topPrint"><%out.print("Print type: " + ((Top)product).getPrintType());%></h5>
					<h5 class="info topSize"><%out.print("Size: " + ((Top)product).getSize());%></h5>
					<h5 class="info topCategory"><%out.print("Category: " + ((Top)product).getCategory());%></h5>
				<% } %>
				
				<%if(product.getClass().getSimpleName().equals("Patch")) {%>
					<h5 class="info patchType"><%out.print("Patch type: " + ((Patch)product).getPatchType());%></h5>
					<h5 class="info patchMaterial"><%out.print("Material: " + ((Patch)product).getMaterial());%></h5>
					<h5 class="info patchSize"><%out.print("Measures: " + ((Patch)product).getMeasures());%></h5>				
				<% } %>
				
				<c:if test="${userRole == UserType.ADMIN}">	
					<a href="#"><img class="edit-icon" alt="icon" src="http://simpleicon.com/wp-content/uploads/pencil.png" /></a>
				</c:if>
	
			</div>
		<% } %>
			
	</body>
</html>
