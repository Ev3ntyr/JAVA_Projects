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
	integrity="sha384-JZR6Spejh5U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
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
	<c:if test="${!empty errorCodesList }">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4 text-danger">Erreur lors de l'enchère</h1>
				<c:forEach items="${errorCodesList}" var="code">
					<p class="lead">${MessageReader.getErrorMessage(code)}</p>
				</c:forEach>
				<div class="justify-content-center text-center d-flex m-0 p-0">
					<img alt="Erreur rencontrée" src="resources/assets/error.png" style="width:200px;height:auto">
				</div>
			</div>
		</div>

	</c:if>
	<br>
 	<c:choose>
		<c:when test="${requestScope.item.stateItem==2 && sessionScope.idUser==requestScope.bid.user.idUser}">
		<h4>Vous avez remporté la vente !</h4>
		</c:when>
		<c:when test="${requestScope.item.stateItem==2 && sessionScope.idUser!=requestScope.bid.user.idUser}">
		<h4> ${requestScope.bid.user.alias} a remporté la vente !</h4>
		</c:when>


		<c:otherwise>
			<h2 class="text-center">Détail vente</h2>
			<br>
			<br>
		</c:otherwise> 	
		</c:choose>


	<div class="form ">
		<div class="col col-4 mx-auto text-center">
			<h4>${requestScope.item.nameItem}</h4>
		</div>
	</div>

	<div class="form-row">
		<div class="col col-lg-2 mx-auto">
			<h5>Description :</h5>
		</div>
		<div class="col col-lg-8 ">
			<h5>${requestScope.item.descriptionItem}</h5>
		</div>
	</div>

	<div class="form-row">
		<div class="col col-lg-2 mx-auto">
			<h5>Catégorie :</h5>
		</div>
		<div class="col col-lg-8 ">
			<h5>${requestScope.item.category.wording}</h5>
		</div>
	</div>
	<div class="form-row">
		<div class="col col-lg-2 mx-auto">
			<h5>Meilleure offre :</h5>
		</div>
		<div class="col col-lg-8 ">
			<h5>${requestScope.bid.bidAmount }</h5>
		</div>
	</div>
	<div class="form-row">
		<div class="col col-lg-2 mx-auto">
			<h5>Mise à prix :</h5>
		</div>
		<div class="col col-lg-8 ">
			<h5>${requestScope.item.initialPrice} points</h5>
		</div>
	</div>
	<div class="form-row">
		<div class="col col-lg-2 mx-auto">
			<h5>Fin de l'enchère :</h5>
		</div>
		<div class="col col-lg-8 ">
			<fmt:parseDate value="${requestScope.item.bidEndDate}"
				pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
			<h5>
				<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm"
					value="${pageScope.parsedDateTime}" />
			</h5>
		</div>
	</div>
	<div class="form-row">
		<div class="col col-lg-2 mx-auto">
			<h5>Retrait :</h5>
		</div>
		<div class="col col-lg-8 ">
			<h5>${requestScope.item.withdraw.street}</h5>
			<h5>${requestScope.item.withdraw.zipCode}
				${requestScope.item.withdraw.street}</h5>
		</div>
	</div>

	<div class="form-row">
		<div class="col col-lg-2 mx-auto">
			<h5>Vendeur :</h5>
		</div>
		<div class="col col-lg-8 ">
			<h5>
				<a href="userProfile?idUser=${requestScope.item.user.idUser}"
					class="bidDetails">${requestScope.item.user.alias}</a>
			</h5>
		</div>
	</div>
	<br>
	<c:choose>
		<c:when test="${requestScope.item.stateItem == 0 && sessionScope.idUser != requestScope.item.user.idUser}">
			<div class="form-row">
				<fmt:parseDate value="${requestScope.item.bidStartDate}"
					pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />

				<h5 class="col col-lg-10 mr-auto">
					La vente débutera le
					<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm"
						value="${pageScope.parsedStartDateTime}" />
				</h5>
			</div>
		</c:when>
		<c:when test="${requestScope.item.stateItem == 0 && sessionScope.idUser == requestScope.item.user.idUser}">

			<div class="form-row">
				<fmt:parseDate value="${requestScope.item.bidStartDate}"
					pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />

				<h5 class="col col-lg-10 mr-auto">
					La vente débutera le
					<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm"
						value="${pageScope.parsedStartDateTime}" />
				</h5>
				<a href="updateBid?idItem=${requestScope.item.idItem}" class="btn btn-warning col-4 ml-4">Modifier ma vente</a>
				<a href="deleteBid?idItem=${requestScope.item.idItem}" class="btn btn-danger col-4 ml-4">Supprimer ma vente</a>
			</div>
		</c:when>
		<c:when
			test="${requestScope.item.stateItem==1 && sessionScope.idUser!=requestScope.item.user.idUser}">
			<form action="bidDetails" method="POST" id="BidDetails">
				<div class="form-row">
					<div class="col col-lg-2 mr-auto">
						<h5>Ma proposition :</h5>
					</div>
					<input id="idItem" type="hidden" name="idItem" value="${requestScope.item.idItem}">
					
					<input type="number" maxlength="10" class="col col-lg-2 mr-auto" value="${requestScope.item.sellingPrice+1}"
						id="bidAmount" name="bidAmount" min="${requestScope.item.sellingPrice+1}" required> 
						
						<input
						class="btn btn-success mr-4 col-3 mr-auto" type=submit
						id="formSubmitBtn" value="Enchérir">
				</div>
			</form>
		</c:when>

		<c:otherwise>
			<div class="form">
				<div class="col col-4 mx-auto text-center">
					<br> <a href="home" class="btn btn-danger col-4">Retour</a>
				</div>
			</div>
		</c:otherwise>

	</c:choose>


</body>
</html>