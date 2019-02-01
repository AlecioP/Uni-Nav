<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuova Prenotazione - ${username}</title>
<!-- JQuery import -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<!-- Custom javascript import -->
<script type="text/javascript" src="js/bootstrapConverter.js"></script>
<script type="text/javascript" src="js/nuovaPrenotazione.js"></script>

<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/nuovaPrenotazione.css">
<link rel="stylesheet" type="text/css" href="css/common.css">

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
					<li><a href="MostraPrenotazioni">Prenotazioni Effettuate</a></li>
					<li><a href="creaPrenotazione">Esegui Nuova Prenotazione</a></li>
					<li><a href="provaFeedback">Lascia un Feedback</a></li>
			</ul>
			<p class="navbar-text navbar-right"><a href="DoLogout">Logout</a></p>
		</div>
	</nav>

	<div class="mycol-1"></div>
	<div class="col-sx mycol-4">

		<div class="asynch-form panel-group">
			<div class="panel panel-primary">
				<div class="panel-heading">Partenza :</div>
				<div class="panel-body">
					<label class="checkbox-inline">
						<input id="start-geoloc" class="start" type="radio" name="start" value="geoloc" checked>
						<span>Posizione Attuale</span>
					</label> 
					<label class="checkbox-inline">
						<input id="start-map" class="start" type="radio" name="start" value="from-map">
						<span>Seleziona sulla mappa</span>
					</label>
				</div>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">Arrivo :</div>
				<div class="panel-body">
					<label class="checkbox-inline">
						<input id="stop-geoloc" class="stop" type="radio" name="stop" value="geoloc" >
						<span>Posizione Attuale</span>
					</label> 
					<label class="checkbox-inline">
						<input id="stop-map" class="stop" type="radio" name="stop" value="from-map" checked>
						<span>Seleziona sulla mappa</span>
					</label>
				</div>
			</div>
			<button id="compute-routes" class="btn btn-info" disabled>Calcola percorsi</button>
		</div>
	</div>
	<div class="mycol-1"></div>
	<div id="map-container"class="col-dx mycol-4">
		<div id="map"></div>
	</div>

</body>
</html>