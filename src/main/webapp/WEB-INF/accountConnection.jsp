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
<title>Connexion</title>
</head>

<body class="container" max-width=80%>
	<br>
	<br>
	<h1>Créer mon compte</h1>
	<br>
	<br>

	<c:if test="${!empty errorCodesList }">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4 text-danger">Erreur lors de la création de
					compte</h1>
				<c:forEach items="${errorCodesList }" var="code">
					<p class="lead">${MessageReader.getErrorMessage(code) }</p>

				</c:forEach>
			</div>
		</div>

	</c:if>
	<form action="connection" method="POST">

		<div class="form-row ">

			<div class="form-group mx-auto col-md-5">
				<label for="alias">Pseudo</label> <input type="text" maxlength="30"
					class="form-control" id="alias" name="alias"
					placeholder="Votre pseudo">
			</div>
		</div>
		<br>

		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUser">Mot de passe</label> <input
					type="password" maxlength="128" class="form-control"
					name="passwordUser" id="passwordUser" placeholder="Votre mot de passe">
			</div>
		</div>
		<br>

		
		<br>
		<br>
		<div class="d-flex justify-content-center">

			<input class="btn btn-success mr-3 col-3" type="submit" value="Connexion">
			<a href="create" class="btn btn-info mr-3 col-3">Créer mon compte</a>
			<a href="home" class="btn btn-danger col-3">Annuler</a>

		</div>
	</form>


</body>
</html>