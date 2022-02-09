<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE>
<html>
<head>
<link href="./assets/headerAndFooter.css" rel="stylesheet">
<link href="./assets/AffichageArticles.css" rel="stylesheet">

<!-- Google Font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">

<meta charset="UTF-8">

<title>Header Connecté</title>
</head>

<header>

    <div>
        <h1>ENI-Encheres</h1>
    </div>
    

    <div>
        <h2>Liste des enchères</h2>
    </div>

    <div class="menuNavConnecte">
        <nav >

            <a href="${pageContext.request.contextPath}/Enchere">Enchères</a>
            <a href="${pageContext.request.contextPath}/ArticleVendu">Vendre un article</a>
            <a href="${pageContext.request.contextPath}/monCompte">Mon profil</a>
            <a href="${pageContext.request.contextPath}/LogOut">Déconnexion</a>

         </nav>
    </div>

</header>