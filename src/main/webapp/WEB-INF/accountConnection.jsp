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
<link href="resources/CSS/style.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Connexion</title>
<link rel="icon" type="image/x-icon" href="resources/assets/logo.ico">
</head>

<body class="container" max-width=80%>
	<br>
	<br>
	<div class="row justify-content-center g-1 my-5">
	
    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 my-auto">
        <a href="home" class="d-none d-sm-block"><img alt="Logo Application" src="resources/assets/logo.png" class="img-thumbnail" style="width: 100px;"></a>
        <a href="home" class="d-sm-none mx-auto"><img alt="Logo Application" src="resources/assets/logo.png" class="img-thumbnail" style="width: 100px;"></a>
    </div>
    
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 p-0">
      <h1 align="left" class="d-none d-sm-block">ENI-Enchères</h1>
        <h1 class="d-sm-none">ENI-Enchères</h1>
		</div>
	</div>
	
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
			<div class="justify-content-center text-center d-flex m-0 p-0">
				<img alt="Erreur rencontrée" src="resources/assets/error.png" style="width:200px;height:auto">
			</div>
		</div>

	</c:if>
	<form action="connection" method="POST">

		<div class="form-row ">

			<div class="form-group mx-auto col-md-5">
				<label for="alias">Pseudo</label> <input type="text" maxlength="30"
					class="form-control" id="alias" name="alias"
					placeholder="Votre pseudo" required>
			</div>
		</div>
		<br>
		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<label for="passwordUser">Mot de passe</label> <input
					type="password" maxlength="20" class="form-control"
					name="passwordUser" id="passwordUser" placeholder="Votre mot de passe" required>
			</div>
		</div>
		<br>
		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<input type="checkbox" id="rememberMe" name="rememberMe">
				<label for="rememberMe">Se souvenir de moi</label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group mx-auto col-md-5">
				<a href="forgottenPwd">Mot de passe oublié</a>
			</div>
		</div>
		<br>
		<br>
		<div class="row justify-content-center">

			<input class="btn btn-success mr-3 mb-3 col-lg-3 col-md-6 col-sm-6 col-xs-9" type="submit" value="CONNEXION">
			<a href="creation" class="btn btn-info mr-3 mb-3 col-lg-3 col-md-6 col-sm-6 col-xs-9">CREER UN COMPTE</a>
			<a href="home" class="btn btn-danger mr-3 mb-3 col-lg-3 col-md-6 col-sm-6 col-xs-9">ANNULER</a>

		</div>
	</form>


</body>
</html>