<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>

	<body>	

		<form action="/servlet/admin/InsertArtist" method="post">
			<input name='name' placeholder='Artist Name' type='text'>
			<input type="file" name="logo" accept="image/*">
			<input type="file" name="pic" accept="image/*">
			<input class='animated' type='submit' value='Submit'>
		</form>
		
	</body>

</html>