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

	<body onload="editProfile()">

		<div class="sc-user-profile profile">
			<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
			<a class="edit-button"><img class="edit-icon" alt="icon" src="http://simpleicon.com/wp-content/uploads/pencil.png" /></a>
			<% User user = (User)request.getSession(false).getAttribute("userInfo"); %>
			<div class="username"><%out.println(user.getUsername());%></div>
			<div class="data">
				<span class="entypo-heart"> Profiles Details</span>
			</div>
			<div class="left">First Name</div>
			<div class="right"><%out.println(user.getName());%></div>
			<div class="left">Last Name</div>
			<div class="right"><%out.println(user.getSurname());%></div>
			<div class="left">Birth Date</div>
			<div class="right"><%out.println(user.getBirthday());%></div>
		</div>


		<div class="sc-user-profile edit">
			<form action="servlet/UpdateUser" method="post">
				<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
				<a class="edit-button" onclick="updateUser()"><img class="edit-icon" alt="icon" src="./images/floppy.png" /></a>
				<div class="username"><%out.println(user.getUsername());%></div>
				<div class="data">
					<span class="entypo-heart"> Edit Profile</span>
				</div>
				<div class="left">New Name:</div>
				<div class="right"><input name="name" type="text" id="newName" value="<%out.print(user.getName());%>"></div>
				<div class="left">New Surname:</div>
				<div class="right"><input name="surname" type="text" id="newSurname" value="<%out.print(user.getSurname());%>"></div>
				<div class="left">New Birthday:</div>
				<div class="right"><input name="name" type="date" id="newBirthday" value="<%out.print(user.getBirthday());%>"></div>
				<div class="left">New Password:</div>
				<div class="right"><input name="password" type="text" id="newPassword" value="<%out.print(user.getPassword());%>"></div>
				<div class="left">New Username:</div>
				<div class="right"><input name="username" type="text" id="newUsername" value="<%out.print(user.getUsername());%>"></div>

			</form>
		</div>

	</body>
</html>
