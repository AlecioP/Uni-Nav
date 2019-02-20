<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="importBootstrap.jsp" %>
</head>
<body>
	<h1>Hai superato il numero massimo di flag!</h1>
	<!-- <h2>Descrizione : </h2> -->
	<h3>
		D'ora in poi non potrai pi� effettuare nuove prenotazioni.<br>
		Questo ban � dovuto al fatto che hai superato la soglia massima di
		prenotazioni senza aver obliterato il biglietto.<br> Potrai
		effettuare nuove prenotazioni mandando una domanda di riabilitazione
		cliccando sul pulsante qui in basso.<br> Una volta accettata la
		richiesta dall'amministratore potrai tornare di nuovo ad effettuare
		prenotazioni.
	</h3>
	<form action="InviaDomanda" method="post">
		<h3>Invia domanda di riabilitazione</h3>
		<button type="submit" class="btn btn-info">Invia Domanda</button>
	</form>
	<form action="HomeSenzaErrori" method="post">
		<button type="submit" class="btn btn-primary">indietro</button>
	</form>
</body>
</html>