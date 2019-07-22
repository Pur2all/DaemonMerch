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

		<h2 id="heading">Lista dei fottuti desideri!</h2>

		<div class="grid-container">

			<ul class="wishllist">
				
				<%LinkedList<Product> wishlist = (LinkedList<Product>)request.getAttribute("wishlist"); %>

				<c:forEach items="${wishlist}" var="currentProduct">

					<li class="product">
						<a>
							<div>
								<img src="Images/${currentProduct.images[0].imageName}">
							</div>
						</a>
							<h3 class="name"> <c:out value="${currentProduct.name}"> </c:out></h3>
							<h4 class="price"> <c:out value="${currentProduct.price}"> </c:out></h4>					
							<button class="button">Remove</button>
					</li>
					
				</c:forEach>		
			
			</ul>
		
		</div>

	</body>
</html>
