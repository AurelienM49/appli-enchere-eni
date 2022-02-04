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

                <!--input pour le nom de l'article-->
                <label for="nomArticle">Nom :</label>
                <input type="text" id="nomArticle" name="nomArticle">
                
                <!--Input pour la descrption de l'article-->
                <label for="descriptionArticle">Description :</label>
                <input type="text" id="descriptionArticle" name="descriptionArticle">
                
                <label for="categorie">Selectionnez une catégorie</label>

                <!--Bouton deroulant selection de la categorie-->
				<select name="categorie" id="categorie">
 					 <option value="informatique">Informatique</option>
 					 <option value="Ameublement">Ameublement</option>
  					 <option value="vetement">Vêtement</option>
					 <option value="sportLoisir"> Sport&Loisirs</option>
				</select>

                <!--Input pour indiquer prix de mise en vente-->
                <label for="prix">Prix :</label>
                <input type="number" id="prix" name="prix">

                <!--Input pour selectionner la date du debut de l'enchère-->
                <label for="dateDebutEnchere">L'enchère commencera le :</label>
                <input type="date" id="dateDebutEnchere" name="dateDebutEnchere" min="2022-02-04" >

                <!--Input pour selectionner la date de fin de l'enchere-->
                <label for="dateFinEnchere">L'enchère se terminera le :</label>
                <input type="date" id="dateFinEnchere" name="dateFinEnchere" max="2099-12-31">

                <!--Bouton submit pour confirmer la mise en vente-->
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