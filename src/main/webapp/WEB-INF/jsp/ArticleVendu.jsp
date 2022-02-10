<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <link href="./assets/formLogin.css" rel="stylesheet">
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
            <div class="vente-article">
                <div class="login-form">
                    <form action="${pageContext.request.contextPath}/ArticleVendu" method="post">
                        <div class="center">
                            <div class="both">
                                <h1 class="title">Vendre un article</h1><br>
                                <div class="content">
                                    <!--input pour le nom de l'article-->
                                    <label for="nomArticle">Nom :</label>
                                    <div class="input-field">
                                        <input type="text" id="nomArticle" name="nomArticle" class="form-item">
                                    </div>

                                    <!--Input pour la descrption de l'article-->
                                    <label for="descriptionArticle">Description :</label>

                                    <div class="input-field">
                                        <input type="text" id="descriptionArticle" name="descriptionArticle"
                                            class="form-item">
                                    </div>

                                    <label for="categorie">Selectionnez une catégorie</label>
                                   
                                    <!--Bouton deroulant selection de la categorie-->
                                    <div class="input-field">
                                    <select name="categorie" id="categorie">
                                        <option value="informatique">Informatique</option>
                                        <option value="Ameublement">Ameublement</option>
                                        <option value="vetement">Vêtement</option>
                                        <option value="sport&loisirs"> Sport&Loisirs</option>
                                    </select><br><br>
                                    </div>

                                    <!--Input pour indiquer prix de mise en vente-->
                                    <label for="prix">Prix :</label>
                                    <div class="input-field">
                                        <input type="number" id="prix" name="prix" class="form-item">
                                    </div>
                                    <!--Input pour selectionner la date du debut de l'enchère-->
                                    <label for="dateDebutEnchere">L'enchère commencera le :</label>
                                    <div class="input-field">
                                        <input type="date" id="dateDebutEnchere" name="dateDebutEnchere"
                                            min="${dateMinEnchere}" class="form-item">
                                    </div>
                                    <!--Input pour selectionner la date de fin de l'enchere-->
                                    <label for="dateFinEnchere">L'enchère se terminera le :</label>
                                    <div class="input-field">
                                        <input type="date" id="dateFinEnchere" name="dateFinEnchere" max="2099-12-31"
                                            class="form-item">
                                    </div>
                                    <!-- Input pour selectionner l'adresse de retrait de l'enchère -->

                                    <div id="adresseDeRetrait">
                                        <h2>Retrait</h2>

                                        <!-- Input pour selectionner la rue de l'adresse de retrait -->
                                        <label for="rue"> Rue :</label>

                                        <div class="input-field">
                                            <input type="text" id="rue" name="rue" value="${user.rue}"
                                                class="form-item">
                                        </div>

                                        <!-- Input pour selectionner le code postal de l'adresse de retrait -->
                                        <label for="codePostal"> Code postal :</label>
                                        <div class="input-field">
                                            <input type="text" id="codePostal" name="codePostal"
                                                value="${user.code_postal}" class="form-item">
                                        </div>
                                        <!-- Input pour selectionner la ville de l'adresse de retrait -->
                                        <label for="ville"> Ville :</label>
                                        <div class="input-field">
                                        <input type="text" id="ville" name="ville" value="${user.ville}"
                                            class="form-item"><br>
                                        </div>    
                                    </div>
                                    <!--Bouton submit pour confirmer la mise en vente-->
                                    <div class="action">
                                        <button type="submit" class="btn-connexion">Mettre en vente</button>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                </div>
                </form>

                </div>
            </section>

        </main>

        <!-- Footer -->

        <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
            <jsp:param value="footer" name="footer"></jsp:param>
        </jsp:include>

    </body>

    </html>