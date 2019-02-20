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


<!-- Custom style import - Desktop -->
<link rel="stylesheet"  
media="screen and (((min-device-width: 500px)and (width < heigth)) or ((min-device-heigth: 500px)and (width > heigth)))" 
 type="text/css" href="css/admin.css">
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@ include file="navbars/adminNavbar.jsp"%>
	<div class="chart-container">
		<div class="myCol-3"></div>
		<div id="mychart"></div>
	</div>
</body>
</html>