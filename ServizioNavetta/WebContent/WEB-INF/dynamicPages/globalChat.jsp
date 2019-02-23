<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Global Chat</title>
<%@include file="importBootstrap.jsp" %>
<script type="text/javascript" src="js/chat.js"></script>
<script type="text/javascript" src="js/chatUpdate.js"></script>

<script type="text/javascript" src="js/bootstrapConverter.js"></script>


<!-- Custom style import-->
<script type="text/javascript" src="js/styleInsert.js"></script>
<script type="text/javascript">
var wd,h,orientation;
document.addEventListener('DOMContentLoaded', function () {
	orientation = window.screen.orientation.type;
	wd  = window.screen.availWidth;
	h = window.screen.availHeight;	
	var minDim = Math.min(wd,h);
	if(minDim<500)
		injectStyle("mobile/css/mobileChat.css");
	else
		injectStyle("css/chat.css");
	
});
</script>
<!-- Custom style import-->


</head>
<body>

<c:set var="tipo" value="${tipo_login}"/>
<c:if test = "${tipo == 'admin'}">
            <%@include file="navbars/adminNavbar.jsp" %>
</c:if>
<c:choose>
         
         <c:when test = "${tipo == 'admin'}">
            <%@include file="navbars/adminNavbar.jsp" %>
         </c:when>
         
         <c:when test = "${tipo == 'driver'}">
            <%@include file="navbars/driverNavbar.jsp" %>
         </c:when>
         
         <c:when test = "${tipo == 'student'}">
            <%@include file="navbars/studNavbar.jsp" %>
         </c:when>
         
         <c:otherwise>
           	
         </c:otherwise>
</c:choose>


<div id="main">

<div id="feed">
	<c:forEach items="${ messages}" var="message">
		<div class="row message-box">
			<span class="user-img"> </span>
			<span class="user-type">${message.type }</span>
			<span class="user-id"> ${message.ID }</span>:
			<span class="message-content row "> ${message.message }</span>
		</div>
	</c:forEach>
</div>
<div id="post-message">

<input id="new-message-content"type="text" placeholder="Scrivi qualcosa...">
<button id="new-message" >Invia</button>

</div>

</div>

</body>
</html>