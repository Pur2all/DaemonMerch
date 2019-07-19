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
    	<link rel="stylesheet" type="text/css" href="./Css/ArtistsPage.css">
    </head>

	<body>
	<h1>porco dio </h1>
		<%LinkedList<Artist> artists = (LinkedList<Artist>) request.getAttribute("artists"); %>
		<%String letter = "a";%>
	
		<c:forEach items="${artists}" var="currentArtist">		
<<<<<<< HEAD

			<div> <c:out value="${currentArtist.name}">No name</c:out> </div>
			<div> <img src="images/${currentArtist.logo.imagename}"> </div>
=======
			
			<c:if test="${!currentArtist.name.charAt(0).equalsIgnoreCase(letter)}">
				<div class="letter"><c:out value="${currentArtist.name.substring(0, 1).toUpperCase()}"></c:out></div>
			</c:if>
			
			<%letter = ((Artist)pageContext.getAttribute("currentArtist")).getName().substring(0, 1);%>
			
			<div class="name"> <c:out value="${currentArtist.name}">No name</c:out> </div>
			<div class="image"> <c:out value="${currentArtist.logo}">No image</c:out> </div>
>>>>>>> 16cfbec0119462e2a5367b659b5bfd2b62f30e12

		</c:forEach>
		
	</body>
</html>
