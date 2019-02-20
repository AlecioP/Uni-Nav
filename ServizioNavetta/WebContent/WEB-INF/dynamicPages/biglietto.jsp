<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Biglietto - ${username}</title>
<%@include file="importBootstrap.jsp"%>

<script type="text/javascript" src="js/bootstrapConverter.js"></script>


<!-- Custom style import - Desktop -->
<script type="text/javascript" src="js/styleInsert.js"></script>
<script type="text/javascript">
	$(function() {
		var orientation = window.screen.orientation.type;
		var wd = window.screen.availWidth;
		var h = window.screen.availHeight;
		if (orientation === "landscape-primary") {
			if (wd < 500)
				injectStyle("mobile/css/mobile.css");
			else {
				/*injectStyle("css/common.css");*/
				injectStyle("css/driver.css");
			}
		} else {
			if (h < 500)
				injectStyle("mobile/css/mobile.css");
			else {
				/*injectStyle("css/common.css");*/
				injectStyle("css/driver.css");
			}
		}
	});
</script>
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@include file="navbars/driverNavbar.jsp"%>

	<div class="myCol-1"></div>
	<div class="myCol-5">
		<form id="biglietto" action="bigliettonavetta" method="post">
			<div class="panel-group">
				<div class="panel panel-success">
					<div class="panel-heading">Matricola studente :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span> 
							<input class="form-control" type="text" name="id" placeholder="Insert id">
						</div>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">Fermata Arrivo : </div>
					<div class="panel-body">
						<select>
							<c:forEach items="${prossime}" var="fermata">
								<option> ${ fermata.nome } </option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div>
				<input type="submit" value="Prenota" class="btn btn-primary">
			</div>
		</form>
	</div>
</body>
</html>