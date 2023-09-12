<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
<script 
	type="text/javascript"
	src="resources/js/scriptHome.js" defer></script>
	
<title>Accueil</title>
<link rel="icon" type="image/x-icon" href="resources/assets/logo.ico">
</head>
<body class="container" max-width="80%">
	
	<div class="d-flex justify-content-center align-text-middle my-5">
		<a href="home"><img alt="Logo Application" src="resources/assets/logo.png" class="img-thumbnail mr-3 align-self-left" style="width:100px;"></a>
		<h1 class="">ENI-Enchères</h1>
	</div>
	

	<c:choose>
		<c:when test="${sessionScope.idUser == null}">
			<ul class="nav justify-content-center">
				<li class="nav-item mr-2">
					<a href="connection" class="nav-link" active>S'inscrire / Se connecter</a>
				</li>
		 	</ul>			
		</c:when>
		<c:otherwise>
		<ul class="nav justify-content-center">
		  <li class="nav-item mr-2">
		    <a href="home" class="nav-link" active>Enchères</a>
		  </li>
		  <li class="nav-item mr-2">
		    <a href="bidNew" class="nav-link">Vendre un article</a>
		  </li>
		  <li class="nav-item mr-2">
		    <a href="userProfile" class="nav-link">Mon profil</a>
		  </li>
		  <li class="nav-item mr-2">
		    <a href="logout" class="nav-link">Déconnexion</a>
		  </li>
		</ul>
		<input type="hidden" id="hiddenID" value="${sessionScope.idUser}">
		</c:otherwise>
	</c:choose>


	<br>

	<form action="home" method="post">
		<div class="row justify-content-center m-4">
			<label for="site-search"></label> <input type="search"
				id="site-search" name="itemName" placeholder="Rechercher sur le site…"
				class="form-control col-5" />

			<button class="form-control col-2 btn btn-outline-success" type=submit>Search</button>
		</div>
	</form>

	<div class="row justify-content-center">
		<label for="category" class="p-1 ml-1">Catégorie : </label> 
		<form action="" method="">
			<select	name="category" id="category">
				<option value="0" selected>Toutes</option>
				<c:forEach items="${requestScope.listCategory}" var="category">
					<option value="${pageScope.category.idCategory }">${pageScope.category.wording}</option>
				</c:forEach>
			</select>
		</form>
	</div>
	
		<c:if test="${sessionScope.idUser != null }">


			<form action="" method="POST">
				<div class="row d-flex justify-content-center mx-auto ">
					<!-- TODO CENTRER CE BOUT DE FORMULAIRE A LA C0N :D -->
					<div class="col ">
						<div class="row ">
							<div class="col ">
								<input type="radio" id="buy" name="filter"
									onclick="handleFilter(this)" checked> <label for="buy"
									class="p-1 ml-1 ">Achats</label>
							</div>
							<div class="col">
								<input type="radio" id="sell" name="filter"
									onclick="handleFilter(this)"> <label for="sell"
									class="p-1 ml-1">Mes Ventes</label>
							</div>
						</div>
						<div class="row ml-2">
							<div class="col">
								<input type="checkbox" id="openBids"
									onclick="handleCheckboxFilter()" checked /> <label
									for="openBids" class="ml-1">enchères ouvertes</label>
							</div>
							<div class="col">
								<input type="checkbox" id="currentSell"
									onclick="handleCheckboxFilter()" disabled /> <label
									for="currentSell" class="ml-1">mes ventes en cours</label>
							</div>
						</div>
						<div class="row ml-2">
							<div class="col">
								<input type="checkbox" id="myBids"
									onclick="handleCheckboxFilter()" /> <label for="myBids"
									class="ml-1">mes enchères</label>
							</div>
							<div class="col">
								<input type="checkbox" id="pendingSell"
									onclick="handleCheckboxFilter()" disabled /> <label
									for="pendingSell" class="ml-1">ventes non débutées</label>
							</div>
						</div>
						<div class="row ml-2">
							<div class="col">
								<input type="checkbox" id="myWonBids"
									onclick="handleCheckboxFilter()" /> <label for="myWonBids"
									class="ml-1">mes enchères remportées</label>
							</div>
							<div class="col">
								<input type="checkbox" id="concludedSell"
									onclick="handleCheckboxFilter()" disabled /> <label
									for="concludedSell" class="ml-1">ventes terminées</label>
							</div>
						</div>
					</div>
				</div>
				</form>
		</c:if>
	
	

	<!--<c:if test="${sessionScope.idUser != null}">-->
	<!--</c:if>-->

	<c:choose>
		<c:when test="${listItem.size() > 0 }">
			<tbody>
				<div class="container">
					<div class="row justify-content-md-center">
						<c:forEach items="${requestScope.listItem }" var="itemSold">
							<div class="card col-3 m-4" style="width: 18rem;display:block" name="${pageScope.itemSold.category.idCategory}">
								<input type="hidden" value="itemSold.user.idUser">
								<img class="card-img-top" src="..." alt="">
								<div class="card-body">
									<h5 class="card-title">
										<c:choose>
											<c:when test="${sessionScope.idUser == null }">
												${pageScope.itemSold.nameItem}
											</c:when>
											<c:otherwise>
												<a href="bidDetails?idItem=${pageScope.itemSold.idItem}"
													class="bidDetails">${pageScope.itemSold.nameItem}</a>
											</c:otherwise>
										</c:choose>
									</h5>
									<p class="card-text">Prix : ${pageScope.itemSold.initialPrice} points</p>
									<fmt:parseDate value="${pageScope.itemSold.bidEndDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
									
									<p class="card-text">Fin de l'enchère :</p>
									<p>
										<fmt:formatDate pattern="dd/MM/yyyy' - 'HH:mm" value="${pageScope.parsedDateTime}" />
									</p>
									<p class="card-text">
									<c:choose>
										<c:when test="${sessionScope.idUser == null }">
											Vendeur : ${pageScope.itemSold.user.alias}
										</c:when>
										<c:otherwise>
											<a href="userProfile?idUser=${pageScope.itemSold.user.idUser}"
												class="bidDetails">${pageScope.itemSold.user.alias}</a>
										</c:otherwise>
									</c:choose>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</tbody>
		</c:when>
	</c:choose>
	<footer>
		<p>
			Logo by: <a href="https://pixabay.com/fr/users/geralt-9301/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=728409">Gerd Altmann</a> de <a href="https://pixabay.com/fr//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=728409">Pixabay</a>
		</p>
		<a href="admin">admin Panel</a>
	</footer>
</body>
</html>