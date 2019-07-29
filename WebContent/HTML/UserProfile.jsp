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
    	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="./js/scriptJS.js"></script>
    </head>

	<body onload="userProfileScript()">

		<div class="sc-user-profile profile">
			<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
			<a class="edit-button"><img class="edit-icon" alt="icon" src="http://simpleicon.com/wp-content/uploads/pencil.png" /></a>
			<% User user = (User)request.getSession(false).getAttribute("userInfo"); %>
			<div class="username" id="username"><%out.println(user.getUsername());%></div>
			<div class="data">
				<span class="entypo-heart"> Profiles Details</span>
			</div>
			<div class="left">First Name</div>
			<div class="right" id="firstname"><%out.println(user.getName());%></div>
			<div class="left">Last Name</div>
			<div class="right" id="surname"><%out.println(user.getSurname());%></div>
			<div class="left">Birth Date</div>
			<div class="right" id="birthday"><%out.println(user.getBirthday());%></div>

			<input type="button" class="left" value="Add Credit Card" id="creditCards">
			<input type="button" class="right" value="Add Billing Address" id="billingAddress">

			<form class="billingAddressForm" method="post">
				<div class="left">State:</div>
				<div class="right"><input name="state" type="text" id="state" value=""></div>
				<div class="left">City:</div>
				<div class="right"><input name="city" type="text" id="city" value=""></div>
				<div class="left">Street Address:</div>
				<div class="right"><input name="street" type="text" id="street" value=""></div>
				<div class="left">District:</div>
				<div class="right"><input name="district" type="text" id="district" value=""></div>
        <div class="left">House Number:</div>
				<div class="right"><input name="houseNumber" type="text" id="houseNumber" value=""></div>
				<input type="button" value="Confirm" class="left confirmButton" onclick="addBillingAddress()">
				<input type="button" value="Cancel" class="right cancelButton">
			</form>

			<form class="creditCardForm" method="post">
				<div class="left">Card Number:</div>
				<div class="right"><input name="cardNumber" type="text" id="cardNumber" value=""></div>
				<div class="left">Card CVV:</div>
				<div class="right"><input name="cardCVV" type="text" id="cardCVV" value=""></div>
				<div class="left">Expiration Date:</div>
				<div class="right"><input name="cardDate" type="text" id="cardDate" value=""></div>
				<input type="button" value="Confirm" class="left confirmButton" onclick="addCreditCard()">
				<input type="button" value="Cancel" class="right cancelButton">
			</form>

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
        		<input type="hidden" id="id" value="<%out.print(user.getId());%>">

				<input type="button" class="left" value="Credit Cards List" id="creditCards">
				<input type="button" class="right" value="Billing Addresses" id="billingAddress">
			</form>
		</div>

	</body>
</html>
