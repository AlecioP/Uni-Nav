<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Oblitera Biglietto</title>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
<script type="text/javascript"
	src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>

<!-- JQuery import -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<!-- Custom javascript import -->
<script type="text/javascript" src="js/bootstrapConverter.js"></script>



<!-- Custom style import -->
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
	<video id="preview"></video>

	<script type="text/javascript" src="js/reader.js"></script>

	<form action="ObliteraManualmente" method="post">
		<input type="text" id="matricola" name="current-matricola" />
		<button type="submit" value="Submit">Submit</button>
	</form>
</body>
</html>