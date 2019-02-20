<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Oblitera - ${ username } </title>
<%@include file="importBootstrap.jsp" %>
<script src="js/prenotazioni.js"></script>
<script>
	$(window).on('load', function() {
		
		var y = document.getElementById("pal").value;
		var x = {
			id : y
		};
		$.ajax({
			type : "POST",
			url : "controlloObliterazione",
			datatype : "json",
			data : JSON.stringify(x),
			success : function(data) {
				var coppia = JSON.parse(data);
				
				if (coppia.x == 1)
					document.getElementById("entrata").disabled = true;
				if (coppia.y == 1)
					document.getElementById("uscita").disabled = true;
				
			}
		});
	});
</script>
<!-- Custom style import-->
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
			injectStyle("css/driver.css");
	}else{
		if(h<500)
			injectStyle("mobile/css/mobile.css");
		else
			injectStyle("css/driver.css");
	}
});
</script>
<!-- Custom style import-->
</head>
<body>
	<%@include file="navbars/driverNavbar.jsp" %>
	<input id=pal type="text" name="fname" value="${prenotazione.ID}"
		style="display: none;">
	<table class="table">
		<tr class="warning">
			<th>Prenotazione</th>
			<th>Autista</th>
			<th>Giro</th>
			<th>Navetta</th>
			<th>Partenza</th>
			<th>Arrivo</th>
			<th></th>
			<th></th>
		</tr>
		<tr class="success">
			<td>${prenotazione.ID}</td>
			<td>${prenotazione.giro}</td>
			<td>${prenotazione.navetta.ID}</td>
			<td>${prenotazione.tratto.partenza.nome}</td>
			<td>${prenotazione.tratto.arrivo.nome}</td>
			<td>${prenotazione.tratto.arrivo.nome}</td>
			<td><input id=entrata class="biglietti-pren"
				value="Oblitera Entrata" type="button"
				onclick="obliteraEntrataUscita('${prenotazioneID}')" /></td>
			<td><input id=uscita value="Oblitera Uscita" type="button"
				onclick="obliteraEntrataUscita('${prenotazioneID}')" /></td>
		</tr>
	</table>
	
</body>
</html>