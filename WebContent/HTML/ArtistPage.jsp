<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>
<%@
    	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/ArtistPage.css">
		<script src="./js/scriptJS.js"></script>
</head>
		
	<body onload="slideshowFunct()">

		<div class="container">
			<%Artist artist = (Artist)request.getAttribute("artist");%>
			<%Image[] images = artist.getImages();%>
			<%int l = images.length - 1;%>

			<div class="name"> <c:out value="${artist.name}">No name</c:out> </div>			

			<div class="slideshow-container">
				<%for(int i = 0; i < images.length; i++) { %>
					<img src="Images/<%=images[i].getImageName()%>?id=<%=artist.getId()%>" /> 		
				<% } %>
			</div>
		</div>
		