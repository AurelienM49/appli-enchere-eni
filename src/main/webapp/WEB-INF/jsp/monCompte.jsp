<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <!DOCTYPE>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>MonCompte</title>
    </head>

    <body>
    
    	<!-- Header -->
    <jsp:include page="/WEB-INF/jsp/header/headerConnecte.jsp">
        <jsp:param value="headerDeconnecte" name="headerDeconnecte"></jsp:param>
    </jsp:include>
    
    <!-- Main -->
    <main>
        <ul>
    <!-- Si le numero d'utilisateur dans le profil est le même que celui en session, on affiche le profil complet -->
            <c:if test="${profil.no_utilisateur == utilisateur.no_utilisateur}">
            	
            	<li>Pseudo : ${profil.pseudo}</li>
            	<li>Nom : ${profil.nom}</li>
            	<li>Prenom : ${profil.prenom}</li>
	            <li>Email : ${profil.email}</li>
	            <li>Telephone : ${profil.telephone}</li>
	            <li>Rue : ${profil.rue}</li>
	            <li>Code postal : ${profil.code_postal}</li>
	            <li>Ville : ${profil.ville}</li>
	            <a href="${pageContext.request.contextPath}/monProfil"><button type="button">Modifier profil</button></a>
            </c:if>
     <!-- Si le numero d'utilisateur dans le profil est différent que celui en session, on affiche que certaines info du profil-->
            <c:if test="${profil.no_utilisateur != utilisateur.no_utilisateur}">
            	<li>Id : ${profil.no_utilisateur}</li>
            	<li>Pseudo : ${profil.pseudo}</li>
            	<li>Nom : ${profil.nom}</li>
            	<li>Prenom : ${profil.prenom}</li>
	            <li>Email : ${profil.email}</li>
            </c:if>
            
        </ul>
     </main>
     
     	<!-- Footer -->

    <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
        <jsp:param value="footer" name="footer"></jsp:param>
    </jsp:include>
        

    </body>

    </html>