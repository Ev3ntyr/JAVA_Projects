<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<title>Administration</title>
<link rel="icon" type="image/x-icon" href="resources/assets/logo.ico">
</head>

<body class="container" max-width=80%>
	<br>
	<br>
	<div class="d-flex justify-content-center align-text-middle my-5">
		<a href="home"><img alt="Logo Application" src="resources/assets/logo.png" class="img-thumbnail mr-3 align-self-left" style="width:100px;"></a>
		<h1 class="">ENI-Enchères</h1>
	</div>
	<br>
	<h2 class="text-center">Panneau d'administration</h2>
	
	<a href="admin/categories" class="btn btn-dark">Gestion des catégories</a>
	<br>
	<a href="admin/users" class="btn btn-dark">Gestion des utilisateurs</a>
	<br>
	<a href="admin/logs" class="btn btn-dark">Logs d'erreurs</a>
	

</body>
</html>