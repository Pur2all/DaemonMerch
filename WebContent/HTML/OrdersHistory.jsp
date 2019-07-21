<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<% LinkedList<Order> orders = (LinkedList<Order>) request.getAttribute("orders"); %>
		<c:forEach items="orders" var="currentOrder" begin="1" end="<%orders.size();%>">
			<p>Order ID: <c:out value="${currentOrder.id}"></c:out> </p>
			<p>Oder made by user <c:out value="${currentOrder.userID}"></c:out> on date <c:out value="${currentOrder.date}"></c:out></p>
			<p>State: <c:out value="${currentOrder.state}"></c:out> </p>
			<p>Billing Address: <c:out value="currentOrder.billingAddress"></c:out> </p>
		</c:forEach>

	</body>

</html>
