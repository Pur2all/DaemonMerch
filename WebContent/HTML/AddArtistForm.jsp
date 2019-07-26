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
		
		<!-- TODO: Animare sta roba -->
		<%if(request.getHeader("Referer").contains("DaemonMerch/AddArtistForm"))
			out.print("Added with success");%>
		<form method="post" action="admin/InsertArtist" enctype="multipart/form-data">

			<div class="group centered">
					<input type="text" placeholder="Artist Name" name="name"/>
			</div>

				<div class="left text">
					Band Logo:
				</div>
				<div class="right">
					<input type="file" accept="image/*" name="logo"/>
				</div>


				<div class="left text">
					Band Picture:
				</div>
				<div class="right">
					<input type="file" accept="image/*" name="artist-picture" multiple/>
				</div>

			<div class="left button">
				<input id="cancel-button" type="button" value="Cancel">
			</div>
			
			<div class="right button">
				<input id="submit-button" type="submit" value="Submit">
			</div>
		</form>


	</body>

</html>
