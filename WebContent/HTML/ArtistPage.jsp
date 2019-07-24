<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>
<%@
    	taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"
%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/ErrorPage.css">
</head>
		
	<body>

		<h4>
			Pagina artista incredibile
		</h4>
			<%Artist artist = (Artist)request.getAttribute("artist");%>
			<%Image[] images = artist.getImages();%>

			<div> <img src="Images/${artist.images[0].imageName}?id=${artist.id}"> </div>
			<div> <c:out value="${artist.name}">No name</c:out> </div>			
			<div id="slideshowContainer">
				<div id="slideshow"> 
					<img class="first" src="http://placehold.it/680x390&text=[slide 1]" />
					<img src="http://placehold.it/680x390&text=[slide 2]" />
					<img src="http://placehold.it/680x390&text=[slide 3]" />
					<img src="http://placehold.it/680x390&text=[slide 4]" />
				</div>
			  
				<div id="nav">
					<div id="prev"><a title="previous">&#60;</a></div>
					<div id="next"><a title="next">&#62;</a></div>
				</div>
			</div>
			
	</body>
