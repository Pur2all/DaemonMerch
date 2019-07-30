<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>

<%@
	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
	%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>DaemonMerch - AdminArea</title>
		<link rel="stylesheet" type="text/css" href="./Css/AdminArea.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="./js/scriptJS.js"></script>
		<script src="./js/Ajax.js"></script>
	</head>

	<body onload="userProfileScript()">

		<a href="./Home"><img class="logo" alt="" src="/DaemonMerch/images/logo.png"></a>

		<% User user = (User)request.getSession(false).getAttribute("userInfo"); %>
		<div class="grid-container">
			<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
			<div class="username"><%out.println(user.getUsername());%> (Admin)</div>
			<div class="data"> Profiles Details</div>
			<div class="leftA">First Name</div>
			<div class="rightA"><%out.println(user.getName());%></div>
			<div class="leftA">Last Name</div>
			<div class="rightA"><%out.println(user.getSurname());%></div>
			<div class="leftA">Birth Date</div>
			<div class="rightA"><%out.println(user.getBirthday());%></div>

			<input type="button" class="left" value="Add New Product" id="productAdd">
			<input type="button" class="right" value="Add New Artist" id="artistAdd">

			<div id="artistForm" style="display: none;">
				<%@ include file="./AddArtistForm.jsp" %>
			</div>
			<div id="productForm" style="display: none;">
				<%@ include file="./AddProductForm.jsp" %>
			</div>

		</div>

	</body>

</html>
