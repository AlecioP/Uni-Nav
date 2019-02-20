<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servizio Navetta - Studente Homepage</title>
<%@include file="importBootstrap.jsp"%>

<!-- Custom javascript import -->
<script type="text/javascript" src="js/bootstrapConverter.js"></script>


<!-- Custom style import - Desktop -->
<script type="text/javascript" src="js/styleInsert.js"></script>
<script type="text/javascript">
$(function(){
	var orientation = window.screen.orientation.type;
	var wd  = window.screen.availWidth;
	var h = window.screen.availHeight;
	if(orientation==="landscape-primary"){
		if(wd<500)
			injectStyle("mobile/css/mobile.css");
		else
			injectStyle("css/studente.css");
	}else{
		if(h<500)
			injectStyle("mobile/css/mobile.css");
		else
			injectStyle("css/studente.css");
	}
});
</script>
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@ include file="navbars/studNavbar.jsp"%>




	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid ">

			<div class="row">
				<div class="myCol-4">
					<img class="img-fluid img-circle img-thumbnail" src="img/user.png">
				</div>
				<div class="myCol-2"></div>
				<div class="myCol-6">
					<h1 id="name">Bentornato ${stud.nome} !</h1>
					<c:set var="nome" value="${stud.nome}"></c:set>
					<c:set var="cognome" value="${stud.cognome}"></c:set>
					<c:set var="matricola" value="${stud.matricola}"></c:set>
					<c:set var="email" value="${stud.email}"></c:set>
					<ul class="list-group">
						<li class="list-group-item list-group-item-info"><span>Account
								:</span></li>
						<li class="list-group-item list-group-item-success"><span
							class="key">Nome : </span><span class="value"> ${nome}</span></li>
						<li class="list-group-item list-group-item-success"><span
							class="key">Cognome : </span><span class="value">
								${cognome}</span></li>
						<li class="list-group-item list-group-item-success"><span
							class="key">Matricola : </span><span class="value">
								${matricola}</span></li>
						<li class="list-group-item list-group-item-success"><span
							class="key">Email : </span><span class="value"> ${email}</span></li>
					</ul>

				</div>
			</div>

		</div>
	</div>



</body>
</html>