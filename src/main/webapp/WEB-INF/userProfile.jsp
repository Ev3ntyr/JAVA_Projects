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
<link rel="icon" type="image/x-icon" href="resources/assets/logo.ico">
</head>
<body class="container" max-width=80%>

	<div class="d-flex justify-content-center align-text-middle my-5">
		<a href="home"><img alt="Logo Application" src="resources/assets/logo.png" class="img-thumbnail mr-3 align-self-left" style="width:100px;"></a>
		<h1 class="">ENI-Enchères</h1>
	</div>
	<br>
		<h2 class="text-center">Profil</h2>
	<br>
	<c:if test="${!empty errorCodesList}">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4 text-danger">Erreur lors de la suppression de
					compte</h1>
				<c:forEach items="${errorCodesList}" var="code">
					<p class="lead">${MessageReader.getErrorMessage(code)}</p>
				</c:forEach>
				<p class="lead">Ventes en cours :</p>
				<ul>
					<c:forEach items="${requestScope.pendingSells}" var="sell">
						<li>${pageScope.sell.nameItem}</li>
					</c:forEach>
				</ul>
				<div class="justify-content-center text-center d-flex m-0 p-0">
					<img alt="Erreur rencontrée" src="resources/assets/error.png" style="width:200px;height:auto">
				</div>
			</div>
			
		</div>
		
		<div class="justify-content-center text-center">
			<p>Image par <a href="https://pixabay.com/fr/users/prawny-162579/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=7705903">Prawny</a> de <a href="https://pixabay.com/fr//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=7705903">Pixabay</a></p>
		</div>

	</c:if>

	
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
			<a href="update" class="btn btn-warning mt-5 mr-4 col-3">Modifier</a> 
		</div>
	</c:if>
</body>
</html>