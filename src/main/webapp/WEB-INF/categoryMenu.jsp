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
<title>Administration - Catégories</title>
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
			<h2 class="text-center">Gestion des catégories</h2>
		<br>


		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">CATEGORY ID</th>
					<th scope="col">CATEGORY LABEL</th>
					<th scope="col">ACTIONS</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.categories}" var="category">
					<tr>
						<th scope="row">${pageScope.category.idCategory}</th>
						<td>${pageScope.category.wording}</td>
						<td class="row">
							<input type="hidden" name="wording" value="${pageScope.category.wording}">
							<button class="btn btn-outline-warning rounded-circle" title="Modifier la catégorie" id="update" value="${pageScope.category.wording}" type="button" onclick="updateCategory(this)"><i class="bi bi-pencil-square"></i></button>
							<a href="deleteCategory?idCategory=${pageScope.category.idCategory}" class="btn btn-outline-danger rounded-circle" title="Supprimer la catégorie" id="delete"><i class="bi bi-x-lg"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form method="POST" action="updateCategory" id="updateCategory">
			<input type="hidden" id="oldCategoryLabel" name="oldCategoryLabel" value=""/>
			<input type="hidden" id="newCategoryLabel" name="newCategoryLabel" value=""/>
		</form>
	<div>
	<br>
		<h2 class="text-center">Nouvelle catégorie</h2>
	<br>
	<form action="categories" method="POST" id="formCreateCategory">
		<div class="form-row justify-content-start">
			<div class="form-group mx-auto">
				<label for="label">Libellé de la nouvelle catégorie : </label>
				<input type="text"
					maxlength="50" class="form-control" id="label" name="label"
					placeholder="Nom de la catégorie" required>
				<button type="submit" class="btn btn-outline-success mt-2"><i class="bi bi-plus"></i> Ajouter</button>
			</div>
		</div>
	</form>
	</div>
		<br>


		<br>

</body>
</html>