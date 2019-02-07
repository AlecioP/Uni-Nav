<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback - ${username}</title>
<%@include file="importBootstrap.jsp" %>
<script type="text/javascript" src="js/feedback.js"></script>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>
<link rel="stylesheet" type="text/css" href="css/feedback.css">

</head>
<body>

	<%@include file="studNavbar.jsp" %>
	<div id=feedback>
		<c:if test="${message-error!=null && message-error!=0 }">
			<span class="error-message"> ${message-error}</span>
		</c:if>
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
			<c:set var="j" value="${0}"/>
			<c:forEach items="${prenotazione}" var="pren" varStatus="i">
				<c:set var="j" value="${j+1}"/>
				<c:set var="classVar" value=" "/>
				
				<c:if test="${ (j%2)!=0}">
					<c:set var="classVar" value="info"/>
				</c:if>
				<tr class="${classVar}">
					<td>${pren.ID}</td>
					<td>${pren.autista.ID}</td>
					<td>${pren.giro}</td>
					<td>${pren.navetta.ID}</td>
					<td>${pren.tratto.partenza.nome}</td>
					<td>${pren.tratto.arrivo.nome}</td>
					<td><input type="button" value="Lascia Feedback"
						onclick=" updateFeedback( ${pren.ID} )" class="btn btn-warning"/></td>

				</tr>

			</c:forEach>
		</table>
	</div>
	<div class="form-horizontal" id="comment" style="display: none;">
		<form action="lasciaFeedback" method="post">
			<div class="form-group" id="prenotazioneID">
				<input id="codicePren" name="preno" type="text" style="display: none;" />
			</div>
			
			<textarea id="comment-area" name="commento" rows="15" cols="50" placeholder="Inserisci un commento..."></textarea>
			<div class="row">
				<span class="myCol-1"></span>
				<input class="myButton btn btn-success myCol-3" type="submit"	value="Invia Feedback" name="cico">
			</div>
			
		</form>
		<div class="row">
			<span class="myCol-2"></span>
			<input class="btn btn-info myCol-1" id="bottone" type="button" onclick="indietro()" value="Indietro"/>
		</div>
	</div>
</body>
</html>