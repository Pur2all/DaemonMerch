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

		<%LinkedList<Artist> artists = (LinkedList<Artist>) request.getAttribute("artists"); %>
		<%char letter = 'a';%>

			<div class="artistsList">		
			<c:forEach items="${artists}" var="currentArtist">
				<div class="artist">	
					<div> <a href="Artist?name=${currentArtist.name}&id=${currentArtist.id}&page=1"> <img src="Images/${currentArtist.logo.imageName}"> </a> </div>
					<div> <a href="Artist?name=${currentArtist.name}&id=${currentArtist.id}&page=1"  class="artist-name"> <c:out value="${currentArtist.name}">No name</c:out> </a> </div>
				</div>
		
				<!-- 
				<c:if test="${!(currentArtist.name.charAt(0)==letter)}">
					<div class="letter"><c:out value="${currentArtist.name.substring(0, 1).toUpperCase()}"></c:out></div>
				</c:if>
				-->
				<%letter = ((Artist)pageContext.getAttribute("currentArtist")).getName().charAt(0);%>
	
			</c:forEach>
		</div>

	</body>
</html>