<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Mes enchères</title>

	<c:forEach var="mesAnnonces" items="${listeDeMesAnnonces}" >
	<c:out value="${mesAnnonces.nom_article}"></c:out><br>
	<c:out value="${mesAnnonces.description}"></c:out><br>
	<c:out value="${mesAnnonces.prix_initial}"></c:out><<br>
	<c:out value="${mesAnnonces.prix_vente}"></c:out><br>
	<c:out value="${mesAnnonces.date_debut_encheres}"></c:out><br>
	<c:out value="${mesAnnonces.date_fin_encheres}"></c:out><br>
	</c:forEach>

</head>
<body>

</body>
</html>