<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"
    import="control.servlet.dbutils.retrieve.*"
    import="java.util.LinkedList"
	%>

<%@ 
	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" 
    %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>

	<body>
		<%  %>
		<!-- servlet/admin/RetrieveAllOrders -->
		<c:forEach var="i" begin="1" end="<%((LinkedList<Order>)RetrieveAllOrders).size();%>">
			<p>Order ID: <c:out value=""></c:out> </p>
			<p>Oder made by user <% %> on date <% %></p>
			<p>State: <% %></p>
			<p>Billing Address: <% %></p>
		</c:forEach>
		
	</body>

</html>