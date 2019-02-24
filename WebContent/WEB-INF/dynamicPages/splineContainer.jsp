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
<script src="https://code.highcharts.com/modules/series-label.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript" src="js/splineChart.js"></script>

<script type="text/javascript">
var array;
document.addEventListener('DOMContentLoaded', function () {
	array = ${data};
	setChart();
});
</script>


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
	<div class="chart-container row">
		<div class="myCol-3"></div>
		
		<div id="mychart" style="display: inline-block; width: 50%;"></div>
		
		
	</div>
</body>
</html>