<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home - ${ username }</title>
<%@include file="importBootstrap.jsp"%>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>
<script type="text/javascript" src="js/anime.js"></script>


<!-- Custom style import - Desktop -->
<link rel="stylesheet"  
media="screen and (((min-device-width: 500px)and (width < heigth)) or ((min-device-heigth: 500px)and (width > heigth)))" 
 type="text/css" href="css/admin.css">
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@ include file="navbars/adminNavbar.jsp"%>


	<div class="container generic-container">
		<div class="jumbotron">
			<div class="row">
				<div class="myCol-6">
					<h2>Bentornato ${username} !</h2>
					<h3>Hai gi&aacute; visto questo 少年 (Shōnen)? :</h3>
				</div>
				<div class="myCol-4">
					<p id="anime"></p>
				</div>
			</div>
		</div>

	</div>

</body>
</html>