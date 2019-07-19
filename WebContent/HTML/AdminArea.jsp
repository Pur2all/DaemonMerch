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
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="./Css/UserProfile.css">
	</head>

	<body>

		<% User user = (User)request.getSession(false).getAttribute("userInfo"); %>
		<div class="sc-user-profile">
			<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
			<a href="#"><img class="edit-icon" alt="icon" src="http://simpleicon.com/wp-content/uploads/pencil.png" /></a>
			<div class="username"><%out.println(user.getUsername());%> (Admin)</div>
			<div class="data">
				<span class="entypo-heart"> Profiles Details</span>
			</div>
			<div class="left">First Name</div>
			<div class="right"><%out.println(user.getName());%></div>
			<div class="left">Last Name</div>
			<div class="right"><%out.println(user.getSurname());%></div>
			<div class="left">Birth Date</div>
			<div class="right"><%out.println(user.getBirthday());%></div>
			
			<form action="./AddProductForm">
				<div class="admin-button">
					<button class="add-product">Add Product</button>
				</div>
			</form>
				
			<form action="./AddArtistForm">			
				<div class="admin-button add-artist">
					<button class="add-artist">Add Artist</button>
				</div>
			</form>
				
		</div>
			
	</body>

</html>