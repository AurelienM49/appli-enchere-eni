<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Vendu</title>
</head>
<body>

	<!-- Header -->
    <jsp:include page="/WEB-INF/jsp/header/headerConnecte.jsp">
        <jsp:param value="headerDeconnecte" name="headerDeconnecte"></jsp:param>
    </jsp:include>
    
    <!-- Main -->
    <main>

        <!--Creation du formulaire pour mettre un article en vente-->
        <div>
            <form action="${pageContext.request.contextPath}/ArticleVendu"  method="post">
                <label for="nomArticle">nom :</label>
                <input type="text" id="nomArticle" name="nomArticle">
                
                <label for="descriptionArticle">Description :</label>
                <input type="text" id="descriptionArticle" name="descriptionArticle">
                
                <label for="cars">Choose a car:</label>

				<select name="categorie" id="categorie">
 					 <option value=""></option>
 					 <option value=""></option>
  					 <option value=""></option>
					 <option value=""></option>
				</select>
                
                <button type="submit">Mettre en vente</button>
            </form>

        </div>
    
    </main>
    
    <!-- Footer -->

    <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
        <jsp:param value="footer" name="footer"></jsp:param>
    </jsp:include>

</body>
</html>