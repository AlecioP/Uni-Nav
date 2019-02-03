<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick Bus - ${username}</title>
<%@include file="importBootstrap.jsp" %>
</head>
<body>

<%@include file="studNavbar.jsp"%>

<h1>Seleziona le navette che vuoi prenotare per raggiungere l&#39;Arrivo</h1>

<form>
	<!-- Iteration using an index, cause it's needed to iterate contemporary bus collection -->
	<c:forEach var="i" begin="0" end="${tratti.length}">
		<c:set var="tratto" value="${tratti[i]}" />
		<label>From ${tratto.partenza.nome} to ${tratto.arrivo.nome}</label>
		
		<c:set var="navette" value="${ listeNavette[i] } " />
		<select>
			<c:forEach var="j" begin="0" end="${navette[i].length}">
				<option>Navetta : ${navette[j]} </option>
			</c:forEach>
		</select>
	</c:forEach>
	<input type="submit" value="Prenota">
</form>
	
</body>
</html>