<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>
<!DOCTYPE html>

<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="../../Css/UserProfile.css">
    </head>

	<body>

		<div class="sc-user-profile">
			<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
			<a href="#"><img class="edit-icon" alt="icon" src="http://simpleicon.com/wp-content/uploads/pencil.png" /></a>
			<div class="username"><%((User)request.getSession().getAttribute("userInfo")).getUsername();%></div>
			<div class="data">
				<span class="entypo-heart"> Profiles Details</span>
			</div>
			<div class="left">First Name</div>
			<div class="right"><%((User)request.getSession().getAttribute("userInfo")).getName();%></div>
			<div class="left">Last Name</div>
			<div class="right"><%((User)request.getSession().getAttribute("userInfo")).getSurname();%></div>
			<div class="left">Birth Date</div>
			<div class="right"><%((User)request.getSession().getAttribute("userInfo")).getBirthday();%></div>
		</div>

	</body>
</html>
