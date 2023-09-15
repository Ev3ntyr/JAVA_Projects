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
<link href="resources/CSS/style.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="container-fluid  justify-content-center text-center">

	<div class="d-flex justify-content-center align-text-middle my-3">
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
					<img alt="Erreur rencontrée" src="resources/assets/error.png"
						style="width: 200px; height: auto">
				</div>
			</div>
		</div>
	</c:if>
	<br>

	<c:choose>
		<c:when
			test="${requestScope.item.stateItem==2 && sessionScope.idUser==requestScope.bid.user.idUser}">
			<h4 class="imptxt text-center mb-4 " mb-4>Vous avez remporté la vente !</h4>
		</c:when>
		<c:when
			test="${requestScope.item.stateItem==2 && requestScope.bid.user.idUser==null}">
			<h4 class="imptxt text-center mb-4">Personne n'a remporté la vente !</h4>
		</c:when>
		<c:when
			test="${requestScope.item.stateItem==2 && sessionScope.idUser!=requestScope.bid.user.idUser}">
			<h4 class="imptxt text-center mb-4">${requestScope.bid.user.alias} a remporté la vente !</h4>
		</c:when>

		<c:otherwise>
			<h2 class="text-center">Détail vente</h2>
			<br>
			<br>
		</c:otherwise>
	</c:choose>


	<div class="form ">
		<div class="row justify-content-center">
		<div class="mb-3 mt-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-center imptxt">
			<h3>${requestScope.item.nameItem}</h3>
		</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
			<h4>Description :</h4>
		</div>
		<div class="mb-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-left">
			<h3>${requestScope.item.descriptionItem}</h3>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
			<h4>Catégorie :</h4>
		</div>
		<div class="mb-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-left">
			<h3>${requestScope.item.category.wording}</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
			<h4>Meilleure offre :</h4>
		</div>
		<div class="mb-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-left">
			<h3>${requestScope.bid.bidAmount }</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
			<h4>Mise à prix :</h4>
		</div>
		<div class="mb-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-left">
			<h3>${requestScope.item.initialPrice}&nbsp;points</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
			<h4>Fin de l'enchère :</h4>
		</div>
		<div class="mb-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-left ">
			<fmt:parseDate value="${requestScope.item.bidEndDate}"
				pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
			<h3>
				<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm"
					value="${pageScope.parsedDateTime}" />
			</h3>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
			<h4>Retrait :</h4>
		</div>
		<div class="mb-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-left ">
			<h3>${requestScope.item.withdraw.street}</h3>
			<h3>${requestScope.item.withdraw.zipCode}
				${requestScope.item.withdraw.city}</h3>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
			<h4>Vendeur :</h4>
		</div>
		<div class="mb-2 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-left">
			<h3>
				<a href="userProfile?idUser=${requestScope.item.user.idUser}"
					class="bidDetails">${requestScope.item.user.alias}</a>
			</h3>
		</div>
	</div>
	<br>
	<c:choose>
		<c:when
			test="${requestScope.item.stateItem == 0 && sessionScope.idUser != requestScope.item.user.idUser}">
			<div class="row justify-content-center">
			
		
				<fmt:parseDate value="${requestScope.item.bidStartDate}"
					pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />

				<h5 class="mb-3 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-center imptxt">
					La vente débutera le
					<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm"
						value="${pageScope.parsedStartDateTime}" />
				</h5>
			</div>
		</c:when>
		<c:when
			test="${requestScope.item.stateItem == 0 && sessionScope.idUser == requestScope.item.user.idUser}">

			<div class="row justify-content-center">
				<fmt:parseDate value="${requestScope.item.bidStartDate}"
					pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />

				<h5 class="mb-5 col-lg-6 col-md-6 col-sm-6 col-xs-11 mx-auto text-center imptxt">
					La vente débutera le
					<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm"
						value="${pageScope.parsedStartDateTime}" />
				</h5>
				</div>
				<div class="row justify-content-center">
				<a href="updateBid?idItem=${requestScope.item.idItem}"
					class="btn btn-warning mr-4 ml-4 mb-3 col-lg-3 col-md-4 col-sm-6 col-xs-12">MODIFIER MA VENTE</a> <a
					href="deleteBid?idItem=${requestScope.item.idItem}"
					class="btn btn-danger mr-4 ml-4 mb-3 col-lg-3 col-md-4 col-sm-6 col-xs-12">SUPPRIMER MA VENTE</a>
			</div>
		</c:when>
		<c:when
			test="${requestScope.item.stateItem==1 && sessionScope.idUser!=requestScope.item.user.idUser && requestScope.userConnected.isActive}">
			<form action="bidDetails" method="POST" id="BidDetails">
				<div class="row justify-content-center">
					<div class="col-lg-3 col-md-4 col-sm-7 col-xs-11 mx-auto text-right">
						<h4>Ma proposition :</h4>
					</div>

					<input id="idItem" type="hidden" name="idItem"
						value="${requestScope.item.idItem}"> 				
					<input type="hidden" id="currentBidder" name="currentBidder" value="${requestScope.bid.user.idUser}">
					<input type="hidden" id="currentBidAmount" name="currentBidAmount" value="${requestScope.bid.bidAmount}">
				
					<input type="number"
						maxlength="10" class="mb-4 col-lg-6 col-md-4 col-sm-4 col-xs-11 mx-auto text-left"
						value="${requestScope.item.sellingPrice+1}" id="bidAmount"
						name="bidAmount" min="${requestScope.item.sellingPrice+1}"
						required> 	
					</div>		
						<div class="row justify-content-center">

						<input
						class="btn btn-success mb-4 col-lg-4 col-md-4 col-sm-4 col-xs-11 mx-auto text-center" type=submit
						id="formSubmitBtn" value="ENCHERIR">
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<div class="form">
				<div class="col col-4 mx-auto text-center">
					<br> <a href="home" class="btn btn-danger col-4">RETOUR</a>
				</div>
			</div>
		</c:otherwise>

	</c:choose>


</body>
</html>