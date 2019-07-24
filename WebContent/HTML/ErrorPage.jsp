<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/ErrorPage.css">
</head>
		
	<body>

		<h2 class="errorMessage">
			<%if(request.getAttribute("errorMessage") != null) {
				out.println("errorMessage");
			}%>
			dio
		</h2>

	</body>
