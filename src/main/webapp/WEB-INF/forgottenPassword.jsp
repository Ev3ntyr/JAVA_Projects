<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.enchere.eni.m.messages.MessageReader"%>
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
<title>Connexion</title>
<script 
src="resources/js/scriptForgottenPassword.js"
type="text/javascript" defer></script>
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
	<h2 class="text-center">Réinitialisation du mot de passe</h2>
	<br>
	
	<br>

	<c:if test="${!empty errorCodesList }">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4 text-danger">Erreur lors de la connexion au
					compte</h1>
					<c:forEach items="${errorCodesList }" var="code">
					<p class="lead">${MessageReader.getErrorMessage(code) }</p>

				</c:forEach>
			</div>
			<div class="justify-content-center text-center d-flex m-0 p-0">
				<img alt="Erreur rencontrée" src="resources/assets/error.png" style="width:200px;height:auto">
			</div>
		</div>

	</c:if>
	<form action="forgottenPwd" method="POST" id="formResetPwd">

		<div class="form-row ">

			<div class="form-group mx-auto col-md-5">
				<label for="alias">Pseudo</label> <input type="text" maxlength="30"
					class="form-control" id="alias" name="alias"
					placeholder="Votre pseudo" required>
			</div>
		</div>
		<br>
		<div class="form-row ">

			<div class="form-group mx-auto col-md-5">
				<label for="alias">Email</label> <input type="email" maxlength="30"
					class="form-control" id="email" name="email"
					placeholder="Votre email" required>
			</div>
		</div>
		<br>
		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUser">Nouveau mot de passe</label> <input
					type="password" maxlength="20" class="form-control"
					name="passwordUser" id="passwordUser" placeholder="Votre mot de passe" required>
			</div>
		</div>
		<br>
		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUser">Confirmation du mot de passe</label> <input
					type="password" maxlength="20" class="form-control"
					name="passwordUserCheck" id="passwordUserCheck" placeholder="Votre mot de passe" required>
			</div>
		</div>
		<br>
		<br>
		<div class="d-flex justify-content-center">
			<input class="btn btn-success mr-3 col-3" type="button" id="updatePwd" value="Valider">
			<a href="home" class="btn btn-danger col-3">Annuler</a>
		</div>
	</form>


</body>
</html>