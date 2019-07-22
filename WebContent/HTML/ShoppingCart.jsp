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
		<link rel="stylesheet" type="text/css" href="./Css/Wishlist.css">
	</head>

	<body>

		<h2 id="heading">Carrelloide!</h2>

		<div class="grid-container">

			<ul class="wishllist">
				
				<%LinkedList<Product> cart = (LinkedList<Product>)request.getAttribute("cart"); %>

				<c:forEach items="${cart}" var="currentProduct">

					<li class="product">
						<a>
							<img class="image" alt="" src="https://upload.wikimedia.org/wikipedia/commons/a/a0/Squircle_rounded_square.svg">
						</a>
							<h3 class="name"> <c:out value="${currentProduct.name}"> </c:out></h3>
							<h3 class="price"> <c:out value="${currentProduct.price}"> </c:out></h3>
							<!-- quantità -->
							<button class="button">Remove</button>
					</li>
					
				</c:forEach>		
			
			</ul>
		
		</div>

	</body>
</html>
