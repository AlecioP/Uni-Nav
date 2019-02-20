<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Oblitera Biglietto</title>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
<script type="text/javascript"
	src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>


<%@include file="importBootstrap.jsp"%>

<!-- Custom javascript import -->
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
				injectStyle("css/genera.css");
			}
		} else {
			if (h < 500)
				injectStyle("mobile/css/mobile.css");
			else {
				/*injectStyle("css/common.css");*/
				injectStyle("css/genera.css");
			}
		}
	});
</script>
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@include file="navbars/driverNavbar.jsp"%>
	<div class="row">
		<div class="myCol-1"></div>
		<div class="myCol-3">

			<form action="obliteraManualmente" method="post">
				<div id="manuale" class="panel panel-primary">
					<div class="panel-heading">Matricola :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span>
							<input class="form-control" type="text" id="matricola" name="current-matricola" />
						</div>
						
					</div>
					<button id="bottone" class="btn btn-warning" type="submit"
						value="Submit">Obliterazione manuale</button>
				</div>
			</form>
			<c:if test="${registration-error != null }">
				<c:set var="message" value="registration-error" />
				<span class="error-message"><c:out
						value="${sessionScope[message]}" /></span>
			</c:if>
		</div>
		<div class="myCol-1"></div>

		<div id="scanner" class="myCol-6">
			<video id="video"></video>
		</div>
		<script type="text/javascript" src="js/reader.js"></script>
	</div>
</body>
</html>