<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.bean.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="./Css/AddArtistProductForm.css">
	 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="./js/Ajax.js"></script>
	</head>

	<body onload="retrieveArtists()">

		<h2>Add Product</h2>

		<form method="post" action="admin/InsertProduct" enctype="multipart/form-data">

			<div class="group">
				<div class="left">
					<input type="text" value="" placeholder="Product Name" name="name"/>
				</div>
				<div class="right">
					<select name="artist" id="artists" class="select">
					</select>
				</div>
			</div>

			<div class="group">
				<div class="left">
					<input type="number" step="1" min="0" value="" placeholder="Items in stock" name="remaining"/>
				</div>
				<div class="right">
				<select name="type" class="select">
					<option value="patch">Patch</option>
					<option value="shirt">Shirt</option>
					<option value="other">Other</option>
				</select>
				</div>
			</div>

			<div class="group">
				<div class="left">
				<select name="tag" class="select">
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
				<textarea placeholder="Description" name="description" tabindex="5" rows="10"></textarea>
			</div>

			<div>
				<input type="file" accept="image/*" name="productImage" multiple/>
			</div>

			<div>
				<input type="submit" value="Submit">
			</div>

		</form>

	</body>

</html>
