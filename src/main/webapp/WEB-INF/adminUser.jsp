<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
src="../resources/js/scriptAdminCategory.js"
type="text/javascript" defer></script>

<meta charset="UTF-8">
<title>Administration - Utilisateurs</title>
<link rel="icon" type="image/x-icon" href="../resources/assets/logo.ico">
</head>
<body class="container" max-width=80%>
	<br>
	<br>
	<div class="d-flex justify-content-center align-text-middle my-5">
		<a href="../home"><img alt="Logo Application" src="../resources/assets/logo.png" class="img-thumbnail mr-3 align-self-left" style="width:100px;"></a>
		<h1 class="">ENI-Enchères</h1>
	</div>
	
		<br>
			<h2 class="text-center">Gestion des utilisateurs</h2>
		<br>


		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">USER ID</th>
					<th scope="col">USER ALIAS</th>
					<th scope="col">USER EMAIL</th>
					<th scope="col">ACTIONS</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.users}" var="user">
					<tr>
						<th scope="row">${pageScope.user.idUser}</th>
						<td>${pageScope.user.alias}</td>
						<td>${pageScope.user.email}</td>
						<td class="row">
							<form>
								<c:choose>
									<c:when test="${pageScope.user.isActive}">
										<button class="btn btn-outline-warning rounded-circle mr-2" title="Désactiver l'utilisateur" id="deactivate" type="button"><i class="bi bi-pause-fill"></i></button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-outline-success rounded-circle mr-2" title="Réactiver l'utilisateur" id="activate" type="button"><i class="bi bi-arrow-clockwise"></i></button>
									</c:otherwise>
								</c:choose>
								<button class="btn btn-outline-danger rounded-circle" title="Supprimer l'utilisateur" id="delete" type="button"><i class="bi bi-x-lg"></i></button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<div>
	<br>

	</div>
		<br>


		<br>

</body>
</html>