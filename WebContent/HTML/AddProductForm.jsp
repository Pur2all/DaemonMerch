<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="./Css/AddArtistProductForm.css">
	</head>

	<body>

		<h2>Add Product</h2>

		<form method="post" action="admin/InsertProduct" enctype="multipart/form-data">

			<div class="group">
				<div class="left">
					<input type="text" value="" placeholder="Product Name" name="name"/>
				</div>
				<div class="right">
					<input type="text" value="" placeholder="Artist" name="artist"/>
				</div>
			</div>



			<div class="group">
				<div class="left">
				<select name="genre">
					<option value="thrash">Thrash Metal</option>
					<option value="death">Death Metal</option>
					<option value="black">Black Metal</option>
					<option value="hc">Hardcore Punk</option>
				</select>
				</div>

				<div class="right">
					<input type="number" step="0.01" min="0" value="" placeholder="Price" name="price"/>
				</div>
			</div>


			<div>
				<textarea placeholder="Description" name="message" tabindex="5" rows="10"></textarea>
			</div>
			<div>
				<input type="submit" value="Submit">
			</div>

		</form>

	</body>

</html>
