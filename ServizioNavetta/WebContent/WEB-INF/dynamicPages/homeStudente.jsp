<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servizio Navetta - Studente Homepage</title>
<%@include file="importBootstrap.jsp" %>

<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/studente.css">
<!-- Custom javascript import -->
<script type="text/javascript" src="js/bootstrapConverter.js"></script>

</head>
<body>
	<%@ include file="studNavbar.jsp" %>
	
	<h1>Bentornato ${stud.nome} !</h1>
	
	<div id="col-sx">
		<img src="img/user.png"/>
	</div>
	<div id="col-dx">
		<h2>Account : </h2>
		<c:set var="nome" value="${stud.nome}"></c:set>
		<c:set var="cognome" value="${stud.cognome}"></c:set>
		<c:set var="matricola" value="${stud.matricola}"></c:set>
		<c:set var="email" value="${stud.email}"></c:set>
		<ul>
			<li> <span class="key">Nome : </span> ${nome}</li>
			<li> <span class="key">Cognome : </span> ${cognome}</li>
			<li> <span class="key">Matricola : </span> ${matricola}</li>
			<li> <span class="key">Email : </span> ${email}</li>
		</ul>
	</div>
	
	
</body>
</html>