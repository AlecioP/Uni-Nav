<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prenotazioni ${username} </title>
<%@include file="importBootstrap.jsp"%>
<script src="js/qrcode.js"></script>
<script src="js/html5-qrcode.js"></script>
<script type="text/javascript" src="js/generaPrenotazioni.js"></script>


<!-- Custom style import - Desktop -->
<link rel="stylesheet"  
media="screen and (((min-device-width: 500px)and (width < heigth)) or ((min-device-heigth: 500px)and (width > heigth)))" 
 type="text/css" href="css/genera.css">
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@include file="navbars/studNavbar.jsp"%>

	<h1>${studente.nome}</h1>

	<div id="prenotazioni">
		<table class="table table-bordered table-hover">
			<tr class="warning">
				<th>Prenotazione</th>
				<th>Autista</th>
				<th>Giro</th>
				<th>Navetta</th>
				<th>Partenza</th>
				<th>Arrivo</th>
				<th></th>
			</tr>
			<c:set var="j" value="${0}"></c:set>
			<c:forEach items="${prenotazione}" var="pren" varStatus="i">
				<c:set var="j" value="${j+1}"></c:set>
				<c:set var="classVar" value=" "></c:set>
				<c:choose>
					<c:when test="${ (j%2)!=0}">
						<c:set var="classVar" value="success"></c:set>
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>

				<tr class="${classVar }">

					<td>${pren.ID}</td>
					<td>${pren.autista.ID}</td>
					<td>${pren.giro}</td>
					<td>${pren.navetta.ID}</td>
					<td>${pren.tratto.partenza.nome}</td>
					<td>${pren.tratto.arrivo.nome}</td>
					<td><button id="qr" class="biglietti btn btn-warning"
						type="button"
						onclick=" updateQRCode(${codici[i.index]})" ><i class="glyphicon glyphicon-qrcode"></i> Genera Biglietto</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<div class="cointaner" align="center" id="pallino" >
		<div id="qrcode"></div>
		<input class="btn btn-primary" id="bottone" type="button" onclick="indietro()" value="Indietro"/>
	</div>

</body>
</html>
