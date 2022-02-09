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
            <li>Pseudo : ${utilisateur.pseudo}</li>
            <li>Nom : ${utilisateur.nom}</li>
            <li>Prenom : ${utilisateur.prenom}</li>
            <c:if test="${verifCnx}">
	            <li>Email : ${utilisateur.email}</li>
	            <li>Telephone : ${utilisateur.telephone}</li>
	            <li>Rue : ${utilisateur.rue}</li>
	            <li>Code postal : ${utilisateur.code_postal}</li>
	            <li>Ville : ${utilisateur.ville}</li>
	            <a href="${pageContext.request.contextPath}/monProfil"><button type="button">Modifier profil</button></a>
            </c:if>
        </ul>
     </main>
     
     	<!-- Footer -->

    <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
        <jsp:param value="footer" name="footer"></jsp:param>
    </jsp:include>
        

    </body>

    </html>