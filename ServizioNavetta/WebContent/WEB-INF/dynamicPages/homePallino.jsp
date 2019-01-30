<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="js/qrcode.js"></script>
<script src="js/html5-qrcode.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css" media="screen">
body {
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="js/biglietto.js"></script>
<!-- Custom style import -->
<!--<link rel="stylesheet" type="text/css" href="css/common.css">-->
</head>
<body>
	<h1>${studente.nome}</h1>
	<table>
		<tr>
			<th>Prenotazione</th>
			<th>Autista</th>
			<th>Giro</th>
			<th>Navetta</th>
			<th>Partenza</th>
			<th>Arrivo</th>
		</tr>
		<c:forEach items="${prenotazione}" var="pren">
			<tr>
				<td>${pren.ID}</td>
				<td>${pren.autista.ID}</td>
				<td>${pren.giro}</td>
				<td>${pren.navetta.ID}</td>
				<td>${pren.tratto.partenza.nome}</td>
				<td>${pren.tratto.arrivo.nome}</td>
				<td><input id="qr" class="biglietti" type="button"
					value="Genera Biglietto"
					onclick=" updateQRCode('${studente.matricola}'+'${pren.ID}');" /></td>
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
	<!-- <form action="generaCodice" method="post">
				<button type="submit">Submit</button>
			</form>
			  <a href="generaCodice">Genera codice</a>
			  -->
	<div id="qrcode"></div>
	<script type="text/javascript">
		function updateQRCode(text) {
			alert("erf");
			var element = document.getElementById("qrcode");

			var bodyElement = document.body;
			if (element.lastChild)
				element.replaceChild(showQRCode(text), element.lastChild);
			else
				element.appendChild(showQRCode(text));

		}
	</script>
</body>
</html>
