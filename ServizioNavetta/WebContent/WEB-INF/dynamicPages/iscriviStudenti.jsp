<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Iscrivi Studente</title>
<%@include file="importBootstrap.jsp"%>

<script src="js/loadStudent.js"></script>

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
		else{
			injectStyle("css/common.css");
			injectStyle("css/iscriviStudente.css");
		}
	}else{
		if(h<500)
			injectStyle("mobile/css/mobile.css");
		else{
			injectStyle("css/common.css");
			injectStyle("css/iscriviStudente.css");
		}
	}
});
</script>
<!-- Custom style import - Desktop -->


</head>
<body>
	<header>
		<form action="home" method="get">
			<div class="row" id="back-button">
				<div class="myCol-1"></div>
				<div class="myCol-1">
					<button type="submit" class="btn btn-info">
						<i class="glyphicon glyphicon-menu-left"></i> <span>Home</span>
					</button>
				</div>
			</div>
		</form>
		<h1>Iscrivi un nuovo studente</h1>
	</header>
	<form id="modulo1" name="myForm" class="form-horizontal" method="post"
		action=inviaDatiStudente>
		<div class="row">
			<div class="myCol-1"></div>
			<div class="panel-group myCol-4">
				<div class="panel panel-success">
					<div class="panel-heading ">Nome :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list-alt"></i></span> <input
								class="form-control" name="nome" type="text" />
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading ">Cognome :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list-alt"></i></span> <input
								class="form-control" name="cognome" type="text" />
						</div>
					</div>
				</div>
				<div class="panel panel-success">
					<div class="panel-heading ">Email :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span> <input
								class="form-control" name="email" type="email" />
						</div>
					</div>
				</div>
			</div>
			<div class="myCol-1"></div>
			<div class="panel-group myCol-4">
				<div class="panel panel-primary">
					<div class="panel-heading ">Matricola :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-education"></i></span> <input
								class="form-control" name="matricola" type="text" />
						</div>
					</div>
				</div>
				<div class="panel panel-success">
					<div class="panel-heading ">Password :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input
								class="form-control" name="password" type="password" />
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading ">Conferma Password :</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input
								class="form-control" name="password2" type="password" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="myCol-9"></div>
			<div class="myCol-3">
				<input type="submit" value="Iscriviti" class="btn btn-success">
			</div>
		</div>
	</form>

</body>
</html>