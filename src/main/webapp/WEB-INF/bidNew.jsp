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

<title>Nouvelle mise aux enchères</title>
<link rel="icon" type="image/x-icon" href="resources/assets/logo.ico">
</head>
<body class="container-fluid  justify-content-center text-center">
	<br>
	<h1>Nouvelle mise aux enchères</h1>

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
				<div class="justify-content-center text-center d-flex m-0 p-0">
					<img alt="Erreur rencontrée" src="resources/assets/error.png"
						style="width: 200px; height: auto">
				</div>
			</div>
		</div>

	</c:if>

	<form action="bidNew" method="POST">

		<div class="row justify-content-center">
			<label for="nameItem" class="col-lg-2 col-md-3 col-sm-4 col-xs-12">Nom
				de l'article :</label>
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
				<input type="text" maxlength="30" class="form-control" id="nameItem"
					name="nameItem" required>
			</div>
		</div>
		<br>
		<div class="row justify-content-center">
			<label class="col-lg-2 col-md-3 col-sm-4 col-xs-12"
				for="descriptionItem">Description :</label>
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
				<textarea maxlength="300" class="form-control" id="descriptionItem"
					name="descriptionItem"
					placeholder="Décrivez au maximum votre article (matière, couleur, état, fonctions...)"
					required></textarea>
			</div>
		</div>
		<br>
		<div class="row justify-content-center">
			<div
				class="col-lg-4 col-md-4 col-sm-4 col-xs-12 d-flex align-items-center">

				<label for="category" class="mr-2 mb-2">Catégorie :</label>

				<div class="col-auto">
					<select name="category" class="form-control" id="category" required>
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.idCategory}">${category.wording}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<br>
		<div class="row justify-content-center">
			<label class="col-lg-3 col-md-3 col-sm-6 col-xs-12" for="">Photo
				de l'article :</label> <input type="file"
				class="col-lg-4 col-md-4 col-sm-6 col-xs-12 " id="" name="file">
	</form>
	</div>
	<br>
	<div class="row justify-content-center">
    <label class="col-lg-2 col-md-3 col-sm-4 col-12" for="initialPrice">Mise à prix :</label>
    <input type="number" maxlength="10" class="col-lg-2 col-md-2 col-sm-5 col-12 form-control"
        id="initialPrice" name="initialPrice" min="0" required>
</div>
		<br>
		<div class="row justify-content-center">
			<label class="col col-lg-2" for="bidStartDate">Début de
				l'enchère :</label> <input type="datetime-local" class="col col-lg-2"
				id="bidStartDate" name="bidStartDate" required>
		</div>
		<br>
		<div class="row justify-content-center">
			<label class="col col-lg-2" for="bidEndDate">Fin de l'enchère
				:</label> <input type="datetime-local" class="col col-lg-2" id="bidEndDate"
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