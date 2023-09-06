<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>

	<h1>Accueil - liste enchères</h1>

	<ul>
		<li><a href="connection">S'inscrire/Se connecter</a></li>
	</ul>
	<ul>
		<li><a href="">Enchères</a></li>
		<li><a href="bidNew">Vendre un article</a></li>
		<li><a href="userProfile">Mon profil</a></li>
		<li><a href="">Déconnexion</a></li>
	</ul>

	<br>

	<a href="bidDetails">Consulter l'enchère</a>

	<c:choose>
		<c:when test="${listItemSold.size() > 0 }">
			<c:forEach items="${listItemSold }" var="itemSold">
				<div class="card" style="width: 18rem;">
					<img class="card-img-top" src="..." alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>

					</div>
				</div>
				</c:forEach>
		</c:when>
	</c:choose>

</body>
</html>