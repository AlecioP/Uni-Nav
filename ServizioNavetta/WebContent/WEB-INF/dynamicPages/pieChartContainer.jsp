<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chart</title>
<%@include file="importBootstrap.jsp"%>

<script type="text/javascript" src="js/bootstrapConverter.js"></script>
<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/admin.css">

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
</head>
<body>
	<%@ include file="navbars/adminNavbar.jsp"%>
	<div class="chart-container">
		<div class="myCol-3"></div>
		<div id="mychart"></div>
	</div>
</body>
</html>