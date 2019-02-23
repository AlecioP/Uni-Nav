<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servizio Navetta - Home</title>
<%@include file="importBootstrap.jsp" %>

<!-- Custom javascript import -->
<script type="text/javascript" src="js/loginbutton.js"></script>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>


<!-- Custom style import-->
<script type="text/javascript" src="js/styleInsert.js"></script>
<script type="text/javascript">
var wd,h,orientation;
document.addEventListener('DOMContentLoaded', function () {
	orientation = window.screen.orientation.type;
	wd  = window.screen.availWidth;
	h = window.screen.availHeight;	
	var minDim = Math.min(wd,h);
	if(minDim<500)
		injectStyle("mobile/css/mobile.css");
	else
		injectStyle("css/common.css");
	
});
</script>
<!-- Custom style import-->


</head>
<body>
	<h1>Servizio Navetta Unical : Benvenuto!</h1>
	<div class="sx-col myCol-6"></div>
	<div class="dx-col myCol-6">
		<form action="doLogin" method="post">
			<div class="panel-group">
				<div class="panel panel-primary">
					<div class="panel-heading">Tipo login :</div>
					<div class="panel-body">
						<label class="checkbox-inline">
							<input class="login-type" type="radio" name="login-type" value="student" checked="checked">
						Studente</label> 
						<label class="checkbox-inline">
							<input class="login-type" type="radio" name="login-type" value="driver">
						Autista</label>
						<label class="checkbox-inline">
							<input class="login-type" type="radio" name="login-type" value="admin">
						Admin</label>
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
			</fieldset>
		</form>
		<form action="moduloIscrizione" method="get">
			<div class="register-div">
				<input class="register btn btn-info" type="submit"
					value="Registrati">
			</div>
		</form>
	</div>
</body>
</html>