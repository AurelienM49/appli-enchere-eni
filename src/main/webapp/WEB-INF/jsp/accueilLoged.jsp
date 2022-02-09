<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">

<link href="./assets/style.css" rel="stylesheet">
<title>Home</title>
</head>
<body>

	<!-- Header -->
    <jsp:include page="/WEB-INF/jsp/header/headerConnecte.jsp">
        <jsp:param value="headerDeconnecte" name="headerDeconnecte"></jsp:param>
    </jsp:include>
    
	<!-- Main-->
    <jsp:include page="/WEB-INF/jsp/main/mainConnecte.jsp">
        <jsp:param value="mainDeconnecte" name="mainDeconnecte"></jsp:param>
    </jsp:include>
	
	<!-- Footer -->

    <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
        <jsp:param value="footer" name="footer"></jsp:param>
    </jsp:include>

    <script src="./assets/js/accueil.js"></script>

</body>
</html>