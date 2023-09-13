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
<script 
src="resources/js/scriptCreateAccount.js"
type="text/javascript" defer></script>

<meta charset="UTF-8">
<title>Création de Compte</title>
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
		<h2 class="text-center">Créer mon compte</h2>
	<br>

	<c:if test="${!empty errorCodesList }">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4 text-danger">Erreur lors de la création de
					compte</h1>
				<c:forEach items="${errorCodesList }" var="code">
					<p class="lead">${MessageReader.getErrorMessage(code) }</p>

				</c:forEach>
				<div class="justify-content-center text-center d-flex m-0 p-0">
					<img alt="Erreur rencontrée" src="resources/assets/error.png" style="width:200px;height:auto">
				</div>
			</div>
			
		</div>
		
		<div class="justify-content-center text-center">
			<p>Image par <a href="https://pixabay.com/fr/users/prawny-162579/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=7705903">Prawny</a> de <a href="https://pixabay.com/fr//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=7705903">Pixabay</a></p>
		</div>

	</c:if>
	<form action="creation" method="POST" id="formCreate">

		<div class="form-row ">
			<div class="form-group mx-auto col-md-5">
				<label for="email">Adresse mail</label> <input type="email"
					maxlength="50" class="form-control" id="email" name="email"
					aria-describedby="emailHelp" placeholder="Entrez votre email" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="alias">Pseudo</label> <input type="text" maxlength="30"
					class="form-control" id="alias" name="alias"
					placeholder="Sans caractère spécial ou ponctuation" required>
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUser">Mot de passe</label> <input
					type="password" maxlength="20" class="form-control"
					name="passwordUser" id="passwordUser" placeholder="Soyez créatif !" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUserCheck">Confirmation mot de passe</label> <input
					type="password" maxlength="20" class="form-control"
					name="passwordUserCheck" id="passwordUserCheck" required>
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="surname">Nom</label> <input type="text" maxlength="30"
					class="form-control" id="surname" name="surname" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="firstName">Prénom</label> <input type="text"
					maxlength="30" class="form-control" id="firstName" name="firstName" required>
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="zipCode">Code postal</label> <input type="text"
					maxlength="10" class="form-control" id="zipCode" name="zipCode" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="city">Ville</label> <input type="text" maxlength="50"
					class="form-control" id="city" name="city" required>
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="street">Rue</label> <input type="text" maxlength="30"
					class="form-control" id="street"
					placeholder="7 rue des bonnes affaires" name="street">
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="phone">Téléphone</label> <input type=tel maxlength="15"
					class="form-control" id="phone" name="phone">
			</div>
		</div>
		<br>
		<br>
		<div class="d-flex justify-content-center">

			<input class="btn btn-success mr-4 col-3" type=button id="formSubmitBtn" value="Créer">
			<a href="home" class="btn btn-danger col-3">Annuler</a>

		</div>
	</form>
	<br>
	<br>




</body>
</html>