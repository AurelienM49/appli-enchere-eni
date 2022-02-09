<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE>
<html>
<head>
<link href="./assets/headerAndFooter.css" rel="stylesheet">
<link href="./assets/style.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Enchères en cours</title>
</head>
<body>



	
	<!-- Header -->
    <jsp:include page="/WEB-INF/jsp/header/headerConnecte.jsp">
        <jsp:param value="headerDeconnecte" name="headerDeconnecte"></jsp:param>
    </jsp:include>

	<!-- Main -->
	<main>
	
		<div class="articlesContainer">
			<c:forEach var="enchereEnCours" items="${listeDesEncheresEnCours}" >
				<div class="article">
					<a href="${pageContext.request.contextPath}/DetailVente?no_article=${enchereEnCours.no_article}"><c:out value="${enchereEnCours.nom_article}"></c:out><br></a>
					Description : <c:out value="${enchereEnCours.description}"></c:out><br>
					Prix initial : <c:out value="${enchereEnCours.prix_initial}"></c:out><br>
					Prix dernière enchère : <c:out value="${enchereEnCours.prix_vente}"></c:out><br>
					Débute le : <c:out value="${enchereEnCours.date_debut_encheres}"></c:out><br>
					Se termine le : <c:out value="${enchereEnCours.date_fin_encheres}"></c:out><br><br>
				</div>
			</c:forEach>
		</div>
	
	
	</main>
	
	<!-- footer -->
    <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
        <jsp:param value="footer" name="footer"></jsp:param>
    </jsp:include>
    
</body>
</html>