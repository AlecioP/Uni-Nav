<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="lasciaFeedback" method="POST">
		<!-- 
		Your name: <br> <input type="text" name="nome"><br>
		<br> Your email: <br> <input type="text" name="email"><br> -->
		<br> Commenti corsa ${prenotazione.ID}: <br>
		<textarea name="commento" rows="15" cols="50"></textarea>
		<br> <br> <input type="submit" value="Submit">
	</form>
</body>
</html>