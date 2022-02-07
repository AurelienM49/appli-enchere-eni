<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<header>

    <div>
        <h1>ENI-Encheres</h1>
    </div>
    

    <div>
        <h2>Liste des enchères</h2>
    </div>

    <div>
        <nav class="menuNavConnecte">

            <a href="${pageContext.request.contextPath}/Enchere">Enchères</a>
            <a href="${pageContext.request.contextPath}/ArticleVendu">Vendre un article</a>
            <a href="${pageContext.request.contextPath}/monCompte">Mon profil</a>
            <a href="${pageContext.request.contextPath}/LogOut">Déconnexion</a>

         </nav>
    </div>

</header>