<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <main>
        <div>
        	<div>
            	<p>Filtres :</p>
            </div>

            <!--Filtres-->
            <div>

                <!--Formulaire de recherche-->
                <form action="${pageContext.request.contextPath}/HomeFiltre" method="get">
                   <div class="container-filtre">
                        <div class="rechercheEtCategories">
                            <div class="rechercher">
                                    <input type="text" name="rechercheMotArt" placeholder="Le nom de l'article contient">
                            </div>
                            
                            <!--Selection categorie-->
                            <div class="categories">
                                <label for="categorie">Categorie : </label>
                                <select name="categorie" id="categorie">
                                    <option value="all">-- Tous les articles --</option>
                                    <option value="informatique">Informatique</option>
                                    <option value="ameublement">Ameublement</option>
                                    <option value="vêtement">Vêtement</option>
                                    <option value="sport&Loisir">Sport&Loisirs</option>
                                </select>
                            </div>
                        </div>
                            <!--Boutton "submit" recherche-->
                            <div>
                                <button type="submit" class="bouton-rechercher">Rechercher</button>
                            </div>
                    
                </form>
            </div>
            

                
                        <!--image de l'annonce-->
                  
                        	<div class="articlesContainer">
                            <c:forEach var="listeDesArticles" items="${articles}">
                            		<div class="article">
                            			<img class="imageArticle" src="./assets/img/imgEnchere.png" alt="Image de l'enchere en cours"/>
                        				<div class="texteContainer">
                        				<div class="texteArticle">
                        					<c:out value="${listeDesArticles.nom_article}"></c:out><br>
                        					Prix : <c:out value="${listeDesArticles.prix_initial}"></c:out><br>
                        					Fin de l'enchère : <c:out value="${listeDesArticles.date_fin_encheres}"></c:out><br><br>
                        					Vendeur : <c:out value="${listeDesArticles.utilisateur.pseudo}"></c:out><br><br>
                        				</div>
                        				</div>
                        			</div>
                        	</c:forEach>
                        	</div>


           

            
        </div>
    </main>