<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servizio Navetta - Driver Homepage</title>
<%@ include file="leafletImport.jsp" %> 

<!-- Custom javascript import -->
<script type="text/javascript" src="js/bootstrapConverter.js"></script>

<script type="text/javascript" src="js/driverMap.js"></script>


<!-- Custom style import - Desktop -->
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
		injectStyle("css/driver.css");
	
});
</script>
<!-- Custom style import - Desktop -->
 

</head>
<body>
	<%@ include file="navbars/driverNavbar.jsp" %> 
	<div class="myCol-1"></div>
	<div class="myCol-10">
		<div id="next-stop" class="row">
			<span>Prossima fermarta : </span> <span id="next-stop-value">Fermata.Nome</span>
		</div>
		<div class="row">
			
			<div id="map" class="myCol-12">
				<!-- Temporary --
				
					<img
						src="https://snazzy-maps-cdn.azureedge.net/assets/127403-no-label-bright-colors.png?v=20171101110035">
				
				-- Temporary -->
				<div id="mapid">
				</div>
			</div>
		</div>
	</div>
</body>
</html>