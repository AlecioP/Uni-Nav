<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servio Navetta - Registrazione</title>
<%@include file="importBootstrap.jsp" %>


<!-- Custom style import - Desktop -->
<link rel="stylesheet"  
media="screen and (((min-device-width: 500px)and (width < heigth)) or ((min-device-heigth: 500px)and (width > heigth)))" 
 type="text/css" href="css/common.css">
<!-- Custom style import - Desktop -->


</head>
<body>
<form action="GET">
<h1>Servizio Navetta Unical : Registrati!</h1>
<div class="panel panel-registration">
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								class="form-control" type="text" name="name"
								placeholder="Inserisci Nome"><br>
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								class="form-control" type="text" name="surname"
								placeholder="Inserci Cognome"><br>
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								class="form-control" type="text" name="regNumber"
								placeholder="Inserisci Matricola"><br>
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input
								class="form-control" type="password" name="password"
								placeholder="Inserisci password"><br>
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input
								class="form-control" type="password" name="confermedPassword"
								placeholder="Conferma password"><br>
						</div>
					</div>
				</div>
				<input class="btn btn-success" type="submit" value="Registrati!">
</form>
</body>
</html>