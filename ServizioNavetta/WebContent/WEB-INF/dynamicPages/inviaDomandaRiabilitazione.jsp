<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="importBootstrap.jsp"%>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>

<link rel="stylesheet"  media="screen" href="css/domanda.css">
</head>
<body>
	<%@include file="navbars/studNavbar.jsp" %>
	<div class="myCol-1"></div>
	<div id="main" class="myCol-10">
		<h1 class="alert alert-danger">Hai superato il numero massimo di
			flag!</h1>
		<!-- <h2>Descrizione : </h2> -->
		<h3 class="alert alert-info">
			D'ora in poi non potrai più effettuare nuove prenotazioni.<br>
			Questo ban è dovuto al fatto che hai superato la soglia massima di
			prenotazioni senza aver obliterato il biglietto.<br> Potrai
			effettuare nuove prenotazioni mandando una domanda di riabilitazione
			cliccando sul pulsante qui in basso.<br> Una volta accettata la
			richiesta dall'amministratore potrai tornare di nuovo ad effettuare
			prenotazioni.
		</h3>
		<form action="InviaDomanda" method="post">
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3>Invia domanda di riabilitazione</h3>
				</div>
				<div class="panel-body">
					<button type="submit" class="btn btn-info">Invia Domanda</button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>