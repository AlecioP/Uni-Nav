<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="importBootstrap.jsp"%>
<script src="js/qrcode.js"></script>
<script src="js/html5-qrcode.js"></script>

<style type="text/css" media="screen">
body {
	text-align: center;
}
</style>

<link rel="stylesheet" type="text/css" href="css/genera.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
	<%@include file="studNavbar.jsp"%>


	<div id="prenotazioni">
		<br> <br> <br>
		<table class="table table-bordered">
			<tr>
				<th>Prenotazione</th>
				<th>Autista</th>
				<th>Giro</th>
				<th>Navetta</th>
				<th>Partenza</th>
				<th>Arrivo</th>
				<th>Data</th>
			</tr>
			<c:forEach items="${prenotazione}" var="pren" varStatus="i">
				<tr>
					<td>${pren.ID}</td>
					<td>${pren.autista.ID}</td>
					<td>${pren.giro}</td>
					<td>${pren.navetta.ID}</td>
					<td class="text-left">${pren.tratto.partenza.nome}</td>
					<td class="text-left">${pren.tratto.arrivo.nome}</td>
					<td>${pren.dateTime.getTime()}</td>
					<td><input id="qr" class="btn btn-warning" type="button"
						value="Genera Biglietto"
						onclick=" updateQRCode('${codici[i.index]}');" /></td>
					<!-- <th>Nome</th>
			<th>Cognome</th> -->
				</tr>
				<%-- <tr>
				<td>${studente.matricola}</td>
			<td>${studente.nome}</td>
			<td>${studente.cognome}</td>
				<td><input id="qr" type="button" value="Genera Biglietto"
					onclick=" updateQRCode('${studente.matricola}'+'${pren.ID}');" /></td>
			</tr> --%>
			</c:forEach>
		</table>
	</div>
	<!-- <form action="generaCodice" method="post">
				<button type="submit">Submit</button>
			</form>
			  <a href="generaCodice">Genera codice</a>
			  -->

	<div class="cointaner" align="center" id="pallino"
		style="display: none">
		<br> <br> <br>
		<div id="qrcode"></div>
		<button id="bottone" class="btn btn-danger" type="button"
			onclick="indietro()">Indietro</button>
	</div>
	<script type="text/javascript">
		function updateQRCode(text) {
			//alert("erf");
			var element = document.getElementById("qrcode");

			var bodyElement = document.body;
			if (element.lastChild) {
				element.replaceChild(showQRCode(text), element.lastChild);
				$("#prenotazioni").hide();
				$("#pallino").show();
				//alert("ee");
			} else {
				element.appendChild(showQRCode(text));
				$("#prenotazioni").hide();
				$("#pallino").show();
			}

		}
	</script>
	<script type="text/javascript">
		function indietro() {
			$("#pallino").hide();
			$("#prenotazioni").show();
		}
	</script>
</body>
</html>
