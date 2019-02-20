<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Domanda gi&aacute; inviata</title>
<%@include file="importBootstrap.jsp"%>


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
			injectStyle("css/common.css");
	}else{
		if(h<500)
			injectStyle("mobile/css/mobile.css");
		else
			injectStyle("css/common.css");
	}
});
</script>
<!-- Custom style import - Desktop -->


</head>
<body>
	<%@include file="navbars/studNavbar.jsp"%>
	<h1 style="color: red;">Hai già effettuato la Domanda di Riabilitazione</h1>
</body>
</html>