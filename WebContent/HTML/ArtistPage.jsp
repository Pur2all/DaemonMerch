<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/ErrorPage.css">
</head>
		
	<body>

		<h4>
			Pagina artista incredibile
		</h4>
		
			<%Artist artist = (Artist)request.getAttribute("artist");%>
		
			<div> <a href="Artist?name=${artist.name}"> <c:out value="${artist.name}">No name</c:out> </a> </div>
			<div> <img src="Images/${artist.logo.imageName}"> </div>
			
	</body>
