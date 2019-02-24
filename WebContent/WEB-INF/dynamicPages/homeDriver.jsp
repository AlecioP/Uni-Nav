<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scegli navetta - ${username}</title>
<%@include file="importBootstrap.jsp"%>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>

<link rel="stylesheet"  media="screen" href="css/chooseBus.css">
</head>
<body>
<div class="myCol-1"></div>
	<div id="main" class="myCol-10">
		<form action="initbus" method="get">
			<div class="panel panel-primary">
				<div class="panel-heading">Scegli una navetta :</div>
				<div class="panel-body">
					<select name="nav-id">
						<c:forEach items="${navette}" var="navetta">
							<option>${navetta.ID}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<input class="btn btn-success"type="submit" value="invia">
		</form>
	</div>
</body>
</html>