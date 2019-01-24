<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servizio Navetta - Home</title>
<!-- JQuery import -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<!-- Custom javascript import -->
<script type="text/javascript" src="js/loginbutton.js"></script>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>

<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
	<h1>Servizio Navetta Unical : Benvenuto!</h1>
	<div class="sx-col myCol-6"></div>
	<div class="dx-col myCol-6">
		<form action="GET">
			<div class="panel-group">
				<div class="panel panel-primary">
					<div class="panel-heading">Tipo login :</div>
					<div class="panel-body">
						<label class="checkbox-inline"><input class="login-type"
							type="radio" name="login-type" value="student" checked="checked">
							Studente</label> <label class="checkbox-inline"><input
							class="login-type" type="radio" name="login-type" value="driver">
							Autista</label>
					</div>
				</div>

				<div class="panel panel-success">
					<div class="panel-heading">
						<span>Dati :</span>  
						<c:if test="${login-error != null }">
							<c:set var="message" value="login-error" />
							<span class="error-message"><c:out value="${sessionScope[message]}"/></span>
						</c:if>
						
					</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								class="form-control" type="text" name="id"
								placeholder="Insert id"><br>
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input
								class="form-control" type="password" name="pass"
								placeholder="Insert password"><br>
						</div>
					</div>
				</div>
			</div>
			<fieldset>

				<input class="btn btn-success" type="submit" value="LOGIN!">
				<input class="register btn btn-info" type="button"
					value="Registrati">
			</fieldset>
		</form>
	</div>
</body>
</html>