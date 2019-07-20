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

		<h2>Add Artist</h2>

		<form method="post" action="/admin/InsertArtist" enctype="multipart/form-data">

			<div class="group">
					<input type="text" placeholder="Artist Name" name="name"/>
			</div>

      <!-- TODO Bisogna checkare che il nome del file del logo sia uguale a quello della band, se no zompa tutto-->
			<div class="group">
				<div class="left text">
					Band Logo:
				</div>
				<div class="right">
					<input type="file" accept="image/*" name="logo"/>
				</div>
			</div>


			<div class="group">
				<div class="left text">
					Band Picture:
				</div>
				<div class="right">
					<input type="file" accept="image/*" name="artist-picture"/>
				</div>
			</div>

			<div class="centered-button">
				<input type="submit" value="Submit">
			</div>

		</form>


	</body>

</html>
