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
		<link rel="stylesheet" type="text/css" href="./Css/ProductsList.css">
	</head>

	<body>

		<h2 id="heading">Prodotti incredibili impressionanti!</h2>

		<div class="grid-container">

			<ul class="product-list">
				
				<%LinkedList<Product> products = (LinkedList<Product>)request.getAttribute("products"); %>

				<c:forEach items="${products}" var="currentProduct">
					<li class="product">
						<a>
							<img class="image" alt="" src="https://upload.wikimedia.org/wikipedia/commons/a/a0/Squircle_rounded_square.svg">
						</a>
							<h3 class="name"> <c:out value="${currentProduct.name}"> </c:out></h3>
							<h4 class="price"> <c:out value="${currentProduct.price}"> </c:out></h4>

							<button class="button">Add to Cart</button>
					</li>
				</c:forEach>		
			
			</ul>
		
		</div>

	</body>
</html>
