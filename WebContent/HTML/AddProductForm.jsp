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

		<form action="/servlet/admin/InsertProduct" method="post">
			<input name='name' placeholder='Product Name' type='text'>
			<input name='price' placeholder='Product Price' step="0.01" min="0" type="number">
			<input name="description" placeholder='Product Description' type="text">
			<select>
				<option value="thrash">Thrash Metal</option>
				<option value="death">Death Metal</option>
				<option value="black">Black Metal</option>
				<option value="hc">Hardcore Punk</option>
			</select>
			 <input type="file" name="pic" accept="image/*">
			<input class='animated' type='submit' value='Submit'>
		</form>
		
	</body>

</html>