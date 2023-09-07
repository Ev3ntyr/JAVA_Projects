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
<title>Modification de Compte</title>
</head>
<body class="container" max-width=80%>
	<br>
	<br>
	<h1>Modifier mon profil</h1>
	<br>
	<br>

	<c:if test="${!empty errorCodesList }">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4 text-danger">Erreur lors de la mise à jour du
					compte</h1>
				<c:forEach items="${errorCodesList }" var="code">
					<p class="lead">${MessageReader.getErrorMessage(code) }</p>

				</c:forEach>
			</div>
		</div>

	</c:if>
	<form action="update" method="POST">

		<div class="form-row ">
			<div class="form-group mx-auto col-md-5">
				<label for="email">Adresse mail</label> <input type="email"
					maxlength="50" class="form-control" id="email" name="email"
					aria-describedby="emailHelp" placeholder="Entrez votre email" value="${requestScope.user.email}" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="alias">Pseudo</label> <input type="text" maxlength="30"
					class="form-control" id="alias" name="alias"
					placeholder="Sans caractère spécial ou ponctuation" value="${requestScope.user.alias}" required>
			</div>
		</div>
		
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="surname">Nom</label> <input type="text" maxlength="30"
					class="form-control" id="surname" name="surname" value="${requestScope.user.surname}" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="firstName">Prénom</label> <input type="text"
					maxlength="30" class="form-control" id="firstName" name="firstName" value="${requestScope.user.firstName}" required>
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="zipCode">Code postal</label> <input type="text"
					maxlength="10" class="form-control" id="zipCode" name="zipCode" value="${requestScope.user.zipCode}" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="city">Ville</label> <input type="text" maxlength="50"
					class="form-control" id="city" name="city" value="${requestScope.user.city}" required>
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="street">Rue</label> <input type="text" maxlength="30"
					class="form-control" id="street"
					placeholder="7 rue des bonnes affaires" name="street" value="${requestScope.user.street}" required>
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="phone">Téléphone</label> <input type=tel maxlength="15"
					class="form-control" id="phone" name="phone" value="${requestScope.user.phone}" required>
			</div>
		</div>
		<br>
		
		<div class="form-row d-flex">
			<div class="form-group justify-content-start ml-5 col-md-5">
				<label for="passwordUser">Mot de passe actuel</label> <input
					type="password" maxlength="20" class="form-control"
					name="passwordUser" id="passwordUser" placeholder="Soyez créatif !" value="password" required>
			</div>
		</div>
		
		<br>
		
		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="newPasswordUser">Nouveau mot de passe</label> <input
					type="password" maxlength="20" class="form-control"
					name="newPasswordUser" id="newPasswordUser" placeholder="Soyez créatif !">
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="newPasswordUserCheck">Confirmation du nouveau mot de passe</label> <input
					type="password" maxlength="128" class="form-control"
					name="newPasswordUserCheck" id="newPasswordUserCheck">
			</div>
		</div>
		
		<br>
		<br>
		
		<div class="d-flex justify-content-center">

			<input class="btn btn-warning mr-4 col-3" type="submit" value="Modifier">
			<a href="home" class="btn btn-danger col-3">Annuler</a>

		</div>
	</form>
	<br>
	<br>




</body>
</html>