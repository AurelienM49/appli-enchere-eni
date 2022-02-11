<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE>
	<html>

	<head>
		<link href="./assets/style.css" rel="stylesheet">
		<meta charset="UTF-8">
		<title>Detail vente</title>
	</head>

	<body>

		<h1 class="vente">Detail Vente</h1>
		<section class="detail-vente">

			<div class="image">
				<img src="./assets/img/imgEnchere.png" alt="Image de l'enchere en cours">
			</div>


			<div class="container">
				<div class="detail-article">
					<p>${article.nom_article}</p>
					<p>Description : ${article.description}</p>
					<p>Categorie : ${article.categorie.libelle}</p>
					<p>Meilleur offre :${article.enchere.montant_enchere}</p>
					<p>Mise à prix : ${article.prix_initial}</p>
					<p>Fin de l'enchère :${article.date_fin_encheres}</p>
					<p>Retrait : ${article.retrait.ville}</p>
					<p>Vendeur : ${article.utilisateur.pseudo}</p>
					<p>Ma proposition :</p>
						

					<form action="${pageContext.request.contextPath}/DetailVente" method="post">
						<input type="number" name="proposition" class="form-item"> <span
							class="erreur">${creditInsuffisant}</span> <span class="erreur">${nombrePositif}</span>
						<span class="erreur">${propositionInf}</span> <input type="hidden" name="no_article"
							value="${article.no_article}"> <input type="hidden" name="no_utilisateur_before"
							value="${article.enchere.utilisateur.no_utilisateur}">
						<button type="submit">Encherir</button>
					</form>
				</div>
			</div>

			<%-- <div class="details">
				<p>${article.nom_article}</p>
				<p>Description : ${article.description}</p>
				<p>Catégorie : ${article.categorie.libelle}</p>
				<p>Meilleur offre :${article.enchere.montant_enchere}</p>
				<p>Mise à prix : ${article.prix_initial}</p>
				<p>Fin de l'enchère :${article.date_fin_encheres}</p>
				<p>Retrait : ${article.retrait.ville}</p>
				<p>Vendeur : ${article.utilisateur.pseudo}</p>
				<p>Ma proposition :</p>
				</div>

				<form action="${pageContext.request.contextPath}/DetailVente" method="post">
					<input type="number" name="proposition" class="form-item"> <span
						class="erreur">${propositionInf}</span> <input type="hidden" name="no_article"
						value="${article.no_article}"> <input type="hidden" name="no_utilisateur_before"
						value="${article.enchere.utilisateur.no_utilisateur}">
					<button type="submit">Encherir</button>
				</form> --%>
		</section>

	</body>

	</html>