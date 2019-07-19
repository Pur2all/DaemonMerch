<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"
    import="java.util.LinkedList"%>
    <%@
    	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
    %>
    
<!DOCTYPE html>

<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="./Css/UserProfile.css">
    </head>

	<body>
	<h1>porco dio </h1>
		<%LinkedList<Artist> artists = (LinkedList<Artist>) request.getAttribute("artists"); %>
	
		<c:forEach items="${artists}" var="currentArtist">		

			<div> <c:out value="${currentArtist.name}">No name</c:out> </div>
			<div> <img src="images/${currentArtist.logo.imagename}"> </div>

		</c:forEach>
		
	</body>
</html>
