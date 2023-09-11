<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

<title>Détail de la vente</title>

<link rel="icon" type="image/x-icon" href="resources/assets/logo.ico">

</head>
<body class="container" max-width=80%>
	<div class="d-flex justify-content-center align-text-middle my-5">
		<a href="home"><img alt="Logo Application"
			src="resources/assets/logo.png"
			class="img-thumbnail mr-3 align-self-left" style="width: 100px;"></a>
		<h1 class="">ENI-Enchères</h1>
	</div>
	<br>
	<h2 class="text-center">Détail vente</h2>
	<br>
	<br>

	<div class="row">
		<div class="col mx-auto col-12">
			<h4>${requestScope.item.nameItem}</h4>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col col-lg-2 mx-auto">
			<h4>Description :</h4>
		</div>
		<div class="col col-lg-8 ">
			<h4>${requestScope.item.descriptionItem}</h4>
		</div>
	</div>

	<div class="row">
		<div class="col col-lg-2 mx-auto">
			<h4>Catégorie :</h4>
		</div>
		<div class="col col-lg-8 ">
			<h4>${requestScope.item.category.wording}</h4>
		</div>
	</div>
	<div class="row">
		<div class="col col-lg-2 mx-auto">
			<h4>Meilleure offre :</h4>
		</div>
		<div class="col col-lg-8 ">
			<h4>! à coder !</h4>
		</div>
	</div>
	<div class="row">
		<div class="col col-lg-2 mx-auto">
			<h4>Mise à prix :</h4>
		</div>
		<div class="col col-lg-8 ">
			<h4>${requestScope.item.initialPrice}points</h4>
		</div>
	</div>
	<div class="row">
		<div class="col col-lg-2 mx-auto">
			<h4>Fin de l'enchère :</h4>
		</div>
		<div class="col col-lg-8 ">
			<fmt:parseDate value="${requestScope.item.bidEndDate}"
				pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
			<h4>
				<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm"
					value="${pageScope.parsedDateTime}" />
			</h4>
		</div>
	</div>
	<div class="row">
		<div class="col col-lg-2 mx-auto">
			<h4>Retrait :</h4>
		</div>
		<div class="col col-lg-8 ">
					<h4>${requestScope.item.withdraw.street}</h4>
					<h4>${requestScope.item.withdraw.zipCode} ${requestScope.item.withdraw.street}</h4>
		</div>
	</div>


</body>
</html>