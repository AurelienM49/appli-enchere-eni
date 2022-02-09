<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detail vente</title>
</head>
<body>

<h1>Detail Vente</h1>

 <div>
   <img src="/temporaire/imgEnchereEnCours.png" alt="Image de l'enchere en cours">
</div>


<p>${article.nom_article}</p>
<p>Description : ${article.description}</p>
<p>Catégorie : ${article.categorie.libelle}</p>
<p>Meilleur offre :${article.enchere.montant_enchere}</p>
<p>Mise à prix : ${article.prix_initial}</p>
<p>Fin de l'enchère :${article.date_fin_encheres} </p>
<p>Retrait : ${article.retrait.ville}</p>
<p>Vendeur : ${article.utilisateur.pseudo}</p>
<p>Ma proposition : </p>
<input type="number" name="proposition" class="form-item">
</body>
</html>