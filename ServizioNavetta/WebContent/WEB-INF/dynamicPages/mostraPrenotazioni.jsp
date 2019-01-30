<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- <script src="js/prenotazioni.js"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<th>Prenotazione</th>
			<th>Prenotazione</th>
			<th>Autista</th>
			<th>Giro</th>
			<th>Navetta</th>
			<th>Partenza</th>
			<th>Arrivo</th>
		</tr>
		<c:forEach items="${prenotazioni}" var="pren">
			<tr>
				<td>${pren.ID}</td>
				<td>${pren.autista.ID}</td>
				<td>${pren.giro}</td>
				<td>${pren.navetta.ID}</td>
				<td>${pren.tratto.partenza.nome}</td>
				<td>${pren.tratto.arrivo.nome}</td>
				<td>${pren.tratto.arrivo.nome}</td>
				<td><input id=entrata class="biglietti-pren"
					value="Oblitera Entrata" type="button"
					onclick="obliteraEntrata('${pren.ID}');" /></td>
				<td><input id=uscita value="Oblitera Uscita" type="button"
					onclick="alert('${pren.ID}')" /></td>
			</tr>
		</c:forEach>
	</table>
	<%-- <h1>${prenotazioni.giro}</h1> --%>
</body>
</html>