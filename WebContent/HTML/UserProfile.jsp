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
			<img class="avatar" src="http://newleafci.com/wp-content/uploads/2018/08/team-member-1.jpg" alt="Ash" />
			<a href="#"><img class="edit-icon" alt="icon" src="http://simpleicon.com/wp-content/uploads/pencil.png" /></a>
			<% User user = (User)request.getSession(false).getAttribute("userInfo"); %>
			<div class="username"> <c:out value="${user.username}"></c:out> </div>
			<div class="data">
				<span class="entypo-heart"> Profiles Details</span>
			</div>
			<div class="left">First Name</div>
			<div class="right"> <c:out value="${user.name}">No name</c:out>  </div>
			<div class="left">Last Name</div>
			<div class="right"> <c:out value="${user.surname}">No surname</c:out>  </div>
			<div class="left">Birth Date</div>
			<div class="right"> <c:out value="${user.birthday}">No birth date</c:out>  </div>
		</div>

	</body>
</html>
