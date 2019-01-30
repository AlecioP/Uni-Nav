<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servizio Navetta - Driver Homepage</title>
<!-- JQuery import -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/driver.css">
<!-- Custom javascript import -->
<script type="text/javascript" src="js/bootstrapConverter.js"></script>

<script type="text/javascript" src="js/driverMap.js"></script>

<!-- Leaftlet imports -->

<!--  Leaftlet css-->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.4.0/dist/leaflet.css"/>
<!--  Leaftlet js-->
<!-- Make sure you put this AFTER Leaflet's CSS -->
 <script type="text/javascript" src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js"></script>
 
 <!-- Leaftlet Routing Machine -->
 
 <!-- Leaftlet Routing Machine css -->
 <link rel="stylesheet" href="leaflet/leafletRoutingMachine/dist/leaflet-routing-machine.css" />
 <!-- Leaftlet Routing Machine js -->
 <script type="text/javascript" src="leaflet/leafletRoutingMachine/dist/leaflet-routing-machine.js"></script>
 
 <!-- Leaflet Geometry Util import -->
 <script type="text/javascript" src="leaflet/leafletGeometryUtil/src/leaflet.geometryutil.js"></script>


</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li><a href="Home">Home</a></li>
				<li><a href="ObliteraBiglietto">Oblitera Biglietto</a></li>
				<li><a href="#">Prenotazione Manuale</a></li>
			</ul>
		</div>
	</nav>
	<div class="myCol-1"></div>
	<div class="myCol-10">
		<div id="next-stop" class="row">
			<span>Prossima fermarta : </span> <span id="next-stop-value">Fermata.Nome</span>
		</div>
		<div class="row">
			
			<div id="map" class="myCol-12">
				<!-- Temporary --
				
					<img
						src="https://snazzy-maps-cdn.azureedge.net/assets/127403-no-label-bright-colors.png?v=20171101110035">
				
				-- Temporary -->
				<div id="mapid">
				</div>
			</div>
		</div>
	</div>
</body>
</html>