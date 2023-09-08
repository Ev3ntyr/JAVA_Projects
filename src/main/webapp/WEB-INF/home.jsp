<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    
   
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
<title>Accueil</title>
</head>
<body class="container" max-width="80%">


	
	<h1 class="row justify-content-center">Accueil - liste enchères</h1>
	<img alt="Logo Application" src="resources/assets/logo.png" class="img-thumbnail" style="width:100px;">
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
		</c:otherwise>
	</c:choose>
	

	<br>
	
	<form>
	<div class="row justify-content-center m-4"  >
	<label for="site-search" ></label>
<input  type="search" id="site-search" name="q" placeholder="Rechercher sur le site…" class="form-control col-5" />

<button class=" form-control col-2 btn btn-outline-success">Search</button></div></form>

<div class="row justify-content-center">
<label for="category" class="p-1 ml-1" >Catégorie : </label>


<select name="" id="category" >
  <option value="Toutes">Toutes</option>
	<c:forEach items="${listCategory}" var="category">
	<option value="${category.idCategory }">${category.wording}</option>
	
	</c:forEach>
</select>
</div>
	

	<c:choose>
		<c:when test="${listItem.size() > 0 }">
			<tbody>
				<div class="container">
				<div class="row justify-content-md-center">
				<c:forEach items="${listItem }" var="itemSold">



					<div class="card col-3 m-4" style="width: 18rem;">
						<img class="card-img-top" src="..." alt="">
						<div class="card-body">
							<h5 class="card-title">
								<a href="bidDetails">${itemSold.nameItem}</a>
							</h5>
							<p class="card-text">Prix : ${itemSold.initialPrice} points</p>
							<p class="card-text">Fin de l'enchère :
								${itemSold.bidEndDate}</p>
							<p class="card-text">
								Vendeur : <a href="userProfile?idUser=${itemSold.user.idUser}" class="bidDetails ">${itemSold.user.alias}</a>
							</p>

						</div>
					</div>
				</c:forEach>
				</div>
				</div>
			</tbody>
		</c:when>
	</c:choose>
	<footer>Logo by: <a href="https://pixabay.com/fr/users/geralt-9301/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=728408">Gerd Altmann</a> de <a href="https://pixabay.com/fr//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=728408">Pixabay</a></footer>
</body>
</html>