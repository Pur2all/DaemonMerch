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
		<script src="./js/scriptJS.js"></script>
		<script src="./js/Ajax.js"></script>
	</head>

	<body onload="addProductDynamic()">

		<h2>Add Product</h2>
		<%if(request.getAttribute("success")!=null)
			out.print((int) request.getAttribute("success"));%>
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
					<input type="number" step="0.01" min="0" value="" placeholder="Price" name="price"/>
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
					<select name="productType" class="select product-type">
						<option value="patch">Patch</option>
						<option value="shirt">Shirt</option>
						<option value="other">Other</option>
					</select>
				</div>
			</div>

		
			<div id="patch-specific">
				<div class="group">
					<div class="left">
						<select name="patchType" class="select">
							<option value="backpatch">Backpatch</option>
							<option value="backshape">Backshape</option>
							<option value="patch">Patch</option>
						</select>
					</div>
		
					<div class="right">
						<input type="text" value="" name="material" placeholder="Patch Texture">
					</div>
				</div>

				<div class="group">
					<div class="center">
						<input type="text" placeholder="Patch Measures" name="measures">
					</div>
				</div>
			</div>

		
			<div id="shirt-specific">
				<div class="group">
					<div class="left">
						<select name="size" class="select">
							<option value="xs">XS</option>
							<option value="s">S</option>
							<option value="m">M</option>
							<option value="l">L</option>
							<option value="xl">XL</option>
							<option value="xxl">XXL</option>
						</select>
					</div>
		
					<div class="right">
						<select name="printType" class="select">
							<option value="backpatch">Front</option>
							<option value="backshape">Back</option>
							<option value="patch">Front and Back</option>
						</select>
					</div>
				</div>

				<div class="group">
					<div class="center">
						<select name="category" class="select">
							<option value="shirt">T-Shirt</option>
							<option value="longsleeve">Longsleeve</option>
							<option value="hoodie">Hoodie</option>
						</select>
					</div>
				</div>
			</div>


			<div>
				<textarea placeholder="Description" name="description" tabindex="5" rows="10"></textarea>
			</div>

			<div>
				<input type="file" accept="image/*" name="productImage" multiple/>
			</div>

			<div class="input-button">
				<input type="submit" value="Submit">
			</div>

		</form>

	</body>

</html>
