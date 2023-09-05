<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Création de Compte</title>
</head>
<body>
	<br>
	<br>
	<h1>Créer mon compte</h1>
	<br>
	<br>

	<form action="CreateAccount" method="POST">

		<div class="form-row ">
			<div class="form-group mx-auto col-md-5">
				<label for="email">Adresse mail</label> <input type="email"
					maxlength="50" class="form-control" id="email"
					aria-describedby="emailHelp" placeholder="Entrez votre email">
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="alias">Pseudo</label> <input type="text" maxlength="30"
					class="form-control" id="alias"
					placeholder="Sans caractère spécial ou ponctuation">
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUser">Mot de passe</label> <input
					type="password" maxlength="128" class="form-control"
					id="passwordUser" placeholder="Soyez créatif !">
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUserCheck">Confirmation mot de passe</label> <input
					type="password" maxlength="128" class="form-control"
					id="passwordUserCheck">
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="surname">Nom</label> <input type="text" maxlength="30"
					class="form-control" id="surname">
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="firstName">Prénom</label> <input type="text"
					maxlength="30" class="form-control" id="firstName">
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="zipCode">Code postal</label> <input type="text"
					maxlength="10" class="form-control" id="zipCode">
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="city">Ville</label> <input type="text" maxlength="50"
					class="form-control" id="city">
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="street">Rue</label> <input type="text" maxlength="30"
					class="form-control" id="street"
					placeholder="7 rue des bonnes affaires">
			</div>
			<div class="form-group mx-auto col-md-5">
				<label for="phone">Téléphone</label> <input type=tel maxlength="15"
					class="form-control" id="phone" pattern="[0-9]{15}">
			</div>
		</div>	
		<br>
		<a href="home" class="btn btn-info " role="button" type="submit ">Créer</a>
		
	</form>
<br><br>



	<a href="home">Annuler</a>

</body>
</html>