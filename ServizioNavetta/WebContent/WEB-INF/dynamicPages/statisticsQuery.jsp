<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistics - ${username }</title>
<%@include file="importBootstrap.jsp"%>

<script type="text/javascript" src="js/bootstrapConverter.js"></script>
<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/admin.css">
</head>
<body>
	<%@ include file="navbars/adminNavbar.jsp"%>
	<div class="myCol-4"></div>
	<div class="form-container myCol-4" >
		<form action="statistics" method="post">

			<div class="panel-group">

				<div class="panel panel-primary">
					<div class="panel-heading">Id Studente :</div>
					<div class="panel-body">
						<input type="text" name="id" placeholder="Matricola...">
					</div>
				</div>

				<div class="panel panel-success">
					<div class="panel-heading">Statistics type :</div>
					<div class="panel-body">
						<select name="stat-type">
							<option value="last-month-books">Prenotazioni
								nell'ultimo mese</option>
						</select>
					</div>
				</div>

			</div>

			<input class="btn btn-success" type="submit" value="Go!">



		</form>
	</div>
</body>
</html>