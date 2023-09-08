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
<title>Nouvelle mise aux enchères</title>
</head>
<body class="container" max-width=80%>
	<h1>Nouvelle mise aux enchères</h1>

	<br>
	<br>
	<br>

	<c:if test="${!empty errorCodesList }">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4 text-danger">Erreur lors de la création de
					l'article</h1>
				<c:forEach items="${errorCodesList }" var="code">
					<p class="lead">${MessageReader.getErrorMessage(code) }</p>

				</c:forEach>
			</div>
		</div>

	</c:if>

	<form action="bidNew" method="POST">

		<div class="form container">
			<div class="row justify-content-md-center">
				<label for="nameItem" class="col col-lg-2">Nom de l'article
					:</label> <input type="text" maxlength="30" class="col col-lg-4"
					id="nameItem" name="nameItem" required>
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="descriptionItem">Description
					:</label> <input type="textarea" maxlength="300" class="col col-lg-4"
					id="descriptionItem" name="descriptionItem"
					placeholder="Décrivez au maximum votre article (matière, couleur, état, fonctions...)"
					required>
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="idCategory">Catégorie :</label> <input
					type="textarea" maxlength="300" class="col col-lg-4" id="idCategory" name="idCategory"
					required>
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="">Photo de l'article :</label> <input
					type="file" class="col col-lg-4" id="" name="">
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="initialPrice">Mise à prix :</label>
				<input type="number" maxlength="10" class="col col-lg-2"
					id="initialPrice" name="initialPrice" required>
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="bidStartDate">Début de
					l'enchère :</label> <input type="datetime-local" class="col col-lg-2"
					id="bidStartDate" name="bidStartDate" required>
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="bidEndDate">Fin de
					l'enchère :</label> <input type="datetime-local" class="col col-lg-2" id="bidEndDate"
					name="bidEndDate" required>
			</div>
		</div>
		<br>
		<div class="container border m-2 p-2" max-width=60%>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="street">Rue :</label> <input
					type="text" maxlength="30" class="col col-lg-2" id="street"
					name="street" value="${requestScope.user.street}" required>
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="bidEndDate">Code postal :</label> <input
					type="text" maxlength="15" class="col col-lg-2" id="zipCode"
					name="zipCode" value="${requestScope.user.zipCode}" required>
			</div>
			<br>
			<div class="row justify-content-md-center">
				<label class="col col-lg-2" for="city">Ville :</label> <input
					type="text" maxlength="30" class="col col-lg-2" id="city"
					name="city" value="${requestScope.user.city}" required>
			</div>
		</div>
		<br> <br>
		<div class="d-flex justify-content-center">

			<input class="btn btn-success mr-4 col-3" type="submit"
				value="Enregistrer"> <a href="home"
				class="btn btn-danger col-3">Annuler</a>

		</div>
	</form>
	<br>
	<br>


</body>
</html>