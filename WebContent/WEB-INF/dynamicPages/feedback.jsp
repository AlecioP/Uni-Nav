<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback - ${username}</title>
<%@include file="importBootstrap.jsp"%>
<script type="text/javascript" src="js/feedback.js"></script>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>


<!-- Custom style import - Desktop -->
<script type="text/javascript" src="js/styleInsert.js"></script>
<script type="text/javascript">
$(function(){
	var orientation = window.screen.orientation.type;
	var wd  = window.screen.availWidth;
	var h = window.screen.availHeight;
	var minDim = Math.min(wd,h);
	
	if(minDim<500)
		injectStyle("mobile/css/mobile.css");
	else
		injectStyle("css/feedback.css");
	
});
</script>
<!-- Custom style import - Desktop -->


</head>
<body>

	<%@include file="navbars/studNavbar.jsp"%>
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
			<c:set var="j" value="${0}" />
			<c:forEach items="${prenotazione}" var="pren" varStatus="i">

				<c:set var="classVar" value="success" />

				<c:if test="${ (j%2)!=0}">
					<c:set var="classVar" value="info" />
				</c:if>
				<c:if test="${ feeds[j]==null }">
					<c:out value="${ fn:length(feeds)}"></c:out>
					<tr class="${classVar}">
						<td>${pren.ID}</td>
						<td>${pren.autista.ID}</td>
						<td>${pren.giro}</td>
						<td>${pren.navetta.ID}</td>
						<td>${pren.tratto.partenza.nome}</td>
						<td>${pren.tratto.arrivo.nome}</td>
						<td><input type="button" value="Lascia Feedback"
							onclick=" updateFeedback( ${pren.ID} )" class="btn btn-warning" /></td>

					</tr>
				</c:if>
				<c:set var="j" value="${j+1}" />
			</c:forEach>
		</table>
	</div>
	<div class="form-horizontal" id="comment" style="display: none;">
		<form action="lasciaFeedback" method="post">
			<div class="form-group" id="prenotazioneID">
				<input id="codicePren" name="preno" type="text"
					style="display: none;" />
			</div>
			<div class="row">
				<div class="row">
					<textarea id="comment-area" name="commento" rows="15" cols="50"
						placeholder="Inserisci un commento..."></textarea>
				</div>
				<div class="row">
					<input id="feedback-send"
						class="myButton btn btn-success myCol-3" type="submit"
						value="Invia Feedback" name="cico">
				</div>
			</div>
		</form>
		<div class="row">
			<input class="btn btn-info"
				id="bottone" type="button" onclick="indietro()" value="Indietro" />
		</div>
	</div>
</body>
</html>