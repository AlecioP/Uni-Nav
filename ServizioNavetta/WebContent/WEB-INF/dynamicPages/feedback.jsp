<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css" media="screen">
</style>
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
Latest compiled JavaScript
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
<script>
	$(window).on('load', function() {
		alert("carica");
		$("#comment").hide();
		$("#feedback").show();
		alert(document.getElementById("pal").value);
	});
</script>
<script>
	$(function() { /* DOM ready */
		$(".myButton").click(function() {
			alert("ww");
			$("#comment").hide();
			$("#feedback").show();
		});
	});
</script>
</head>
<body>
	<div id=feedback>
		<table>
			<tr>
				<th>Prenotazione</th>
				<th>Autista</th>
				<th>Giro</th>
				<th>Navetta</th>
				<th>Partenza</th>
				<th>Arrivo</th>
			</tr>
			<c:forEach items="${prenotazione}" var="pren" varStatus="i">
				<tr>
					<td>${pren.ID}</td>
					<td>${pren.autista.ID}</td>
					<td>${pren.giro}</td>
					<td>${pren.navetta.ID}</td>
					<td>${pren.tratto.partenza.nome}</td>
					<td>${pren.tratto.arrivo.nome}</td>
					<td><input id="feed" type="button" value="Lascia Feedback"
						onclick=" updateFeedback('${pren.ID}');" /></td>
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
	<div class="form-horizontal" id=comment style="display: none">
		<form action="lasciaFeedback" method="post">
			<!-- 
		Your name: <br> <input type="text" name="nome"><br>
		<br> Your email: <br> <input type="text" name="email"><br> -->
			<div class="form-group" id=prenotazioneID>
				<input id=codicePren name="preno" type="text" style="display: none" />
			</div>
			<%-- <br> Commenti corsa ${prenotazione.ID}: <br> --%>
			<textarea name="commento" rows="15" cols="50"></textarea>
			<br> <br> <input class=myButton type="submit" value="Submi"
				name="cico">
			<button id="bottone" type="button" onclick="indietro()">Indietro</button>
		</form>
	</div>
	<script type="text/javascript">
		function updateFeedback(text) {
			alert(text);
			var element = document.getElementById("qrcode");
			document.getElementById("codicePren").value = text;
			alert(document.getElementById("codicePren").value);
			/* 
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
			 } */
			//$("#demo").hide();
			$("#feedback").hide();
			$("#comment").show();
		}
	</script>
	<script type="text/javascript">
		function indietro() {
			document.getElementById("codicePren").value = null;
			alert(document.getElementById("codicePren").value);
			$("#comment").hide();
			$("#feedback").show();
		}
	</script>
</body>
</html>