<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistics - ${username }</title>
<%@include file="importBootstrap.jsp" %>
</head>
<body>
<%@ include file="navbars/adminNavbar.jsp" %>
<div>
<form action="statistics" method="post">

<input type="submit" value="Go!">
</form>
</div>
</body>
</html>