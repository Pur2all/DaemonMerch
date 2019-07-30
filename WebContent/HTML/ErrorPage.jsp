<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="model.bean.*"%>

<head>
	<base href="./" target="_self">
	<link rel="stylesheet" type="text/css" href="./Css/ErrorPage.css">
</head>

	<body>
		<div class="container">
			<h2 class="errorMessage">
				<%if(request.getAttribute("errorMessage") != null) {
					out.println("errorMessage");
				}%>
				Sorry, there was an error loading your page!
			</h2>

			<a class="link" href="/DaemonMerch/Home">Go to home</a>
		</div>

	</body>
