<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reclaims for ${username}</title>
<%@include file="importBootstrap.jsp"%>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>
<script type="text/javascript" src="js/hoverStyle.js"></script>


<!-- Custom style import -->
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
<!-- Custom style import -->


</head>
<body>
	<%@ include file="navbars/adminNavbar.jsp"%>
	<div class="generic-container">
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="info">
					<th>ID</th>
					<th>Data</th>
					<th>Studente</th>
					<th>Vedi statistiche</th>
					<th>Riabilita</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(domande) <= 0 }">
					<tr class="not-hover">
						<td colspan="6">Non ci sono domande per te in questo momento</td>
					</tr>
				</c:if>
				<c:if test="${fn:length(domande) > 0 }">
					<c:forEach items="${domande}" var="domanda">
						<tr class="not-hover">
							<td>${domanda.ID }</td>
							<td>${domanda.dateTime }</td>
							<td>${domanda.studente.matricola}</td>
							<td>
								<!-- VEDI STATISTICHE -->
								<form action="statistics" method="post">
									<input type="hidden" name="id" 		value="${domanda.studente.matricola}" >
									<input type="hidden" name="stat-type" value="last-month-books"> 
									<input class="btn btn-warning" type="submit" value="Stats...">
								</form>
							</td>
							<td>
								<form action="reclaim" method="post">
									<input type="hidden" name="reclaim-id" value="${domanda.ID}">
									<input class="btn btn-success" type="submit" value="Riabilita">
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>