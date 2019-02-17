<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home - ${ username } </title>
<%@include file="importBootstrap.jsp" %>
<script type="text/javascript" src="js/bootstrapConverter.js"></script>
<script type="text/javascript" src="js/anime.js"></script>
<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/admin.css">
</head>
<body>
<%@ include file="navbars/adminNavbar.jsp" %>


<div class="container generic-container">
  <div class="jumbotron">
    <h1>Bentornato ${username} !</h1>
    <h2>Hai gi&aacute; visto questo 少年 (Shōnen)? : </h2> 
    <p id="anime">
    
    </p> 
  </div>
  
</div>

</body>
</html>