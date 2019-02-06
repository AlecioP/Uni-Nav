<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback</title>
<%@include file="importBootstrap.jsp"%>
<link rel="stylesheet" type="text/css" href="css/genera.css">
<link rel="stylesheet" type="text/css" href="css/common.css">

<script>
	$(window).on('load', function() {
		$("#comment").hide();
		$("#feedback").show();
	});
	function updateFeedback(text) {
		var element = document.getElementById("qrcode");
		document.getElementById("codicePren").value = text;
		$("#feedback").hide();
		$("#comment").show();
	}
	function indietro() {
		document.getElementById("codicePren").value = null;
		$("#comment").hide();
		$("#feedback").show();
	}
</script>
</head>
<body>
	<%@include file="studNavbar.jsp"%>
	<div id=feedback>
		<br> <br> <br>
		<c:if test="${message-error != null }">
			<c:set var="message" value="message-error" />
			<span class="error-message"><c:out
					value="${sessionScope[message]}" /></span>
		</c:if>
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
					<td>${pren.tratto.partenza.nome}</td>
					<td>${pren.tratto.arrivo.nome}</td>
					<td>${pren.dateTime.getTime()}</td>
					<td><input id="bottone" class="btn btn-warning" type="button"
						value="Lascia Feedback" onclick="updateFeedback( ${pren.ID} )" /></td>

				</tr>

			</c:forEach>
		</table>
	</div>
	<div class="form-horizontal" id="comment">
		<form action="lasciaFeedback" method="post">
			<div class="form-group" id=prenotazioneID>
				<input id="codicePren" name="preno" type="text"
					style="display: none;" />
			</div>
			<br> <br> <br>
			<textarea name="commento" rows="15" cols="50"></textarea>

			<br> <br> <input class="btn btn-success" type="submit"
				value="Invia Feedback" name="cico">

		</form>
		<button class="btn btn-danger" id="bottone" type="button"
			onclick="indietro()">Indietro</button>
	</div>
</body>
</html>