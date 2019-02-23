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
<script type="text/javascript" src="js/labelChangerStatistics.js"></script>


<!-- Custom style import-->
<script type="text/javascript" src="js/styleInsert.js"></script>
<script type="text/javascript">
$(function(){
	var orientation = window.screen.orientation.type;
	var wd  = window.screen.availWidth;
	var h = window.screen.availHeight;
	var minDim = Math.min(wd,h);
	
	if(minDim<500)
		injectStyle("mobile/css/mobile.css");
	else
		injectStyle("css/admin.css");
	
});
</script>
<!-- Custom style import-->


</head>
<body>
	<%@ include file="navbars/adminNavbar.jsp"%>
	<div class="myCol-4"></div>
	<div class="form-container myCol-4" >
		<form action="statistics" method="post">

			<div class="panel-group">

				<div class="panel panel-primary">
					<div id="stat-label" class="panel-heading">Id Studente :</div>
					<div class="panel-body">
						<input type="text" name="id" placeholder="ID...">
					</div>
				</div>

				<div class="panel panel-success">
					<div class="panel-heading">Statistics type :</div>
					<div class="panel-body">
						
						<select id="select-stat" name="stat-type">
							<optgroup label="Studente">
								<option class="stud" value="last-month-books">
									Prenotazioni nell'ultimo mese
								</option>
							</optgroup>
							<optgroup label="Navetta">
								<option class="bus" value="bus-good-drivers">
									Prenotazioni per Autista
								</option>
							</optgroup>
						</select>
					</div>
				</div>

			</div>

			<input class="btn btn-success" type="submit" value="Go!">



		</form>
	</div>
</body>
</html>