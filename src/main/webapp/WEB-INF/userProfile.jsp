<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.enchere.eni.m.messages.MessageReader"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="UTF-8">
<title>Profil</title>
</head>
<body class="container" max-width=80%>

	<h1 class="my-5">Profil</h1>

	
	<div class="row">
		<div class="col mr-2 col-3">
			<h4>Pseudo : </h4>
		</div>
		<div class="col mx-auto col-5">
			<h4>${requestScope.user.alias}</h4>
		</div>
	</div>
	<div class="row">
		<div class="col mr-2 col-3">
			<h6>Nom : </h6>
		</div>
		<div class="col mx-auto col-5">
			<h6>${requestScope.user.surname}</h6>
		</div>
	</div>
	<div class="row">
		<div class="col mr-2 col-3">
			<h6>Prénom : </h6>
		</div>
		<div class="col mx-auto col-5">
			<h6>${requestScope.user.firstName}</h6>
		</div>
	</div>
	<div class="row">
		<div class="col mr-2 col-3">
			<h6>Email : </h6>
		</div>
		<div class="col mx-auto col-5">
			<h6>${requestScope.user.email}</h6>
		</div>
	</div>
	<div class="row">
		<div class="col mr-2 col-3">
			<h6>Téléphone : </h6>
		</div>
		<div class="col mx-auto col-5">
			<h6>${requestScope.user.phone}</h6>
		</div>
	</div>
	<div class="row">
		<div class="col mr-2 col-3">
			<h6>Rue : </h6>
		</div>
		<div class="col mx-auto col-5">
			<h6>${requestScope.user.street}</h6>
		</div>
	</div>
	<div class="row">
		<div class="col mr-2 col-3">
			<h6>Code Postal : </h6>
		</div>
		<div class="col mx-auto col-5">
			<h6>${requestScope.user.zipCode}</h6>
		</div>
	</div>
	<div class="row">
		<div class="col mr-2 col-3">
			<h6>Ville : </h6>
		</div>
		<div class="col mx-auto col-5">
			<h6>${requestScope.user.city}</h6>
		</div>
	</div>
		
	<c:if test="${sessionScope.idUser == requestScope.user.idUser}">
		<div class="d-flex justify-content-center">
			<a href="create" class="btn btn-warning mt-5 mr-4 col-3">Modifier</a> 
		</div>
	</c:if>
</body>
</html>