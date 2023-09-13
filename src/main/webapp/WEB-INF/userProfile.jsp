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
<link href="resources/CSS/style.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Profil</title>
<link rel="icon" type="image/x-icon" href="resources/assets/logo.ico">
</head>
<body class="container-fluid  justify-content-center text-center">

	<div class="row justify-content-center g-1 my-5 entete">

		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 my-auto">
			<a href="home"><img alt="Logo Application" align="right"
				src="resources/assets/logo.png"
				class="img-thumbnail mr-3 align-self-right" style="width: 100px;"></a>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 p-0">
			<h1 align="left">ENI-Enchères</h1>
		</div>
	</div>

	<br>
	<h2 class="text-center">Profil</h2>
	<br>



	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Pseudo :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11 ">
			<h3>${requestScope.user.alias}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Nom :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h3>${requestScope.user.surname}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Prénom :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h3>${requestScope.user.firstName}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Email :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h3>${requestScope.user.email}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Téléphone :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h3>${requestScope.user.phone}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Rue :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h3>${requestScope.user.street}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Code postal :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h3>${requestScope.user.zipCode}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h4>Ville :</h4>
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-11">
			<h3>${requestScope.user.city}</h3>
		</div>
	</div>

	<c:if test="${sessionScope.idUser == requestScope.user.idUser}">
		<div class="d-flex justify-content-center">
			<a href="update" class="btn btn-warning mt-5 mr-4 col-3">MODIFIER</a>
		</div>
	</c:if>
</body>
</html>