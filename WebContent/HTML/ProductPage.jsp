<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>
    <%@
    	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
    %>
    
<!DOCTYPE html>

<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="./Css/UserProfile.css">
    </head>

	<body>

		<div class="sc-user-profile">
			<% Product product = (Product)request.getAttribute("product"); %>
			<% UserType userRole = ((User)request.getSession(false).getAttribute("userInfo")).getUserType();%>
			<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
		
		<c:if test="${userRole == UserType.ADMIN}">	
			<a href="#"><img class="edit-icon" alt="icon" src="http://simpleicon.com/wp-content/uploads/pencil.png" /></a>
		</c:if>

		<div class="name">NOMe</div>
		<div class="description">DDDDDDDDDDDDDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAu</div>
		<div class="price">19.99</div>
		<button class="delete-product-button">DELETE PRODUCT</button>

		</div>

	</body>
</html>
