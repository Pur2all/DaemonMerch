<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>
<%@
    	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
%>

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.3/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.js"></script>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" rel="stylesheet" />
  <link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" rel="stylesheet" />
  <link rel="stylesheet" href="./Css/ArtistPage.css"/>
  <script type="text/javascript" src="./js/slick.js"></script>
</head>

	<body onload="slideshowFunct()">

		<div class="artistContainer">
			<%Artist artist = (Artist)request.getAttribute("artist");%>
			<%Image[] images = artist.getImages();%>
			<%int l = images.length - 1;%>

			<div class="artistName"> <c:out value="${artist.name}">No name</c:out> </div>

			<div class="slideshow-container">
				<%for(int i = 0; i < images.length; i++) { %>
					<img src="Images/<%=images[i].getImageName()%>?id=<%=artist.getId()%>" />
				<% } %>
			</div>
			<div class="artistProducts">
				<%@ include file="./ProductsList.jsp" %>
			</div>
		</div>

	</body>
