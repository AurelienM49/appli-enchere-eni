<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <!DOCTYPE>
            <html>


            <head>
                <link href="./assets/style.css" rel="stylesheet">
                <meta charset="UTF-8">
<title>Ajouter une catégories</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/ajout-categorie" class="form-ajoutCat" method="post">
			<label for="libelle">Libelle :</label>
	        <input type="text" name="libelle" id="libelle" class="form-item">
	</form>
	



</body>
</html>