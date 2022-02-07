<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Enchères</title>
</head>
<body>

	<!-- Header -->
    <jsp:include page="/WEB-INF/jsp/header/headerConnecte.jsp">
        <jsp:param value="headerDeconnecte" name="headerDeconnecte"></jsp:param>
    </jsp:include>
    
    <!-- Main -->

    <main>
        <div class=enchere id="mesEncheres">

            <p>Mes enchères :</p>
            <a href=#>Tout voir</a>

        </div>

        <div class=enchere id="enchereEnCours">

            <p>Enchères en cours :</p>
            <a href="#">Tout voir</a>

        </div>
    </main>
	
	<!-- Footer -->

    <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
        <jsp:param value="footer" name="footer"></jsp:param>
    </jsp:include>

</body>
</html>