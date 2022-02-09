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
<p>Catégorie : ${categorie.libelle}</p>
<p>Meilleur offre : </p>
<p>Mise à prix : </p>
<p>Fin de l'enchère : </p>
<p>Retrait : </p>
<p>Vendeur : </p>
<p>Ma proposition : </p>
<input type="number" name="proposition" class="form-item">
</body>
</html>