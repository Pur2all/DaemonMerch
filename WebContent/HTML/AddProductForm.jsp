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

		<!-- TODO: Animare sta roba -->
		<%if(request.getHeader("Referer").contains("DaemonMerch/AddProductForm"))
			out.print("Added with success");%>
		<form method="post" action="admin/InsertProduct" enctype="multipart/form-data" id="insertForm">

				<div class="left spec">
					<input type="text" value="" placeholder="Product Name" name="name"/>
				</div>
				<div class="right spec">
					<select name="artistId" id="artists" class="select">
					<option value="" style="color: gray;">-- select an artist --</option>
					</select>
				</div>

				<div class="left spec">
					<input type="number" step="1" min="0" value="" placeholder="Items in stock" name="remaining"/>
				</div>
				<div class="right spec">
					<input type="number" step="0.01" min="0" value="" placeholder="Price" name="price"/>
				</div>
					
				<div class="left spec">
					<select name="productType" class="select product-type" onchange="selectUpdate()">
						<option value="other">Other</option>
						<option value="patch">Patch</option>
						<option value="shirt">Shirt</option>
					</select>
				</div>

				<div class="right spec">
					<select name="tag" class="select">
						<option value="thrash">Thrash Metal</option>
						<option value="death">Death Metal</option>
						<option value="black">Black Metal</option>
						<option value="hc">Hardcore Punk</option>
					</select>
				</div>

			<div id="patch-specific" style="display: none;">
					<div class="left spec">
						<select name="patchType" class="select">
              <option value="BACKPATCH">Backpatch</option>
							<option value="BACKSHAPE">Backshape</option>
							<option value="PATCH">Patch</option>
						</select>
					</div>

					<div class="right spec">
						<input type="text" value="" name="material" placeholder="Patch Texture">
					</div>

					<div class="center spec">
						<input type="text" placeholder="Patch Measures" name="measures">
					</div>
				</div>


			<div id="shirt-specific" style="display: none;">
					<div class="left spec">
						<select name="size" class="select">
							<option value="XS">XS</option>
							<option value="S">S</option>
							<option value="M">M</option>
							<option value="L">L</option>
							<option value="XL">XL</option>
							<option value="XXL">XXL</option>
						</select>
					</div>

					<div class="right spec">
						<select name="printType" class="select">
							<option value="FRONT">Front</option>
							<option value="BACK">Back</option>
							<option value="BACKFRONT">Front and Back</option>
						</select>
					</div>

					<div class="center spec">
						<select name="category" class="select">
							<option value="TSHIRT">T-Shirt</option>
							<option value="LONGSLEEVE">Longsleeve</option>
							<option value="HOODIE">Hoodie</option>
						</select>
					</div>
			</div>


			<div id="imageInput">
				<textarea placeholder="Description" name="description" tabindex="5" rows="10"></textarea>
			</div>

			<div id="imageInput">
				<input type="file" accept="image/*" name="productImage" multiple/>
			</div>

			<div class="left">
				<input id="cancel-button1" type="button" value="Cancel">
			</div>
			
			<div class="right">
				<input id="submit-button1" type="submit" value="Submit">
			</div>
		</form>

	</body>

</html>
