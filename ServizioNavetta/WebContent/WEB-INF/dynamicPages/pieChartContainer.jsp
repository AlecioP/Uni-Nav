<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chart</title>
<%@include file="importBootstrap.jsp"%>

<script type="text/javascript" src="js/bootstrapConverter.js"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/series-label.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript" src="js/pieChart.js"></script>

<script type="text/javascript">
var array;
document.addEventListener('DOMContentLoaded', function () {
	array = ${data};
	createPie();
});
</script>


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
			injectStyle("css/admin.css");
	}else{
		if(h<500)
			injectStyle("mobile/css/mobile.css");
		else
			injectStyle("css/admin.css");
	}
});
</script>
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@ include file="navbars/adminNavbar.jsp"%>
	<div class="chart-container">
		<div class="myCol-3"></div>
		<div id="mychart" style="display: inline-block; width: 50%;"></div>
	</div>
</body>
</html>