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
            
            <!--Formulaire de recherche-->
            <form action="${pageContext.request.contextPath}/HomeFiltre" method="get">
                <div class="container-filtre">
                    <div class="rechercheEtCategories">
	                    <div class="recherche">
	                        <input type="text" name="rechercheMotArt" placeholder="Le nom de l'article contient">
	                    </div>
	                    
	                    <!--Selection categorie-->
	                    <div class="categories">
	                        <label for="categorie">Categorie : </label>
	                        <select name="categorie" id="categorie">
	                        	<option value="all">Toutes</option>
	                    	    <option value="informatique">Informatique</option>
	                    	    <option value="ameublement">Ameublement</option>
	                    	    <option value="vêtement">Vêtement</option>
	                    	    <option value="sport&loisirs">Sport&Loisirs</option>
	                         </select>
	                    </div>
                    </div>

                    <!--Button "radio" pour selection filtre "Achat" / "Mes Ventes"-->
                    <div class="radio">

                        <!--Achat + checkbox pour approfondir filtre "Achat"-->
                        <div class="achats">
                            <label for="achats">Achats</label>
                            <input type="radio" id="radioAchats" name="choixRadio" value="Achats" checked="checked">

                            <input type="checkbox" id="encheresOuvertes" name="checkBoxFiltre1" value="encheresOuvertes">
                            <label for="encheresOuvertes">Enchères ouvertes</label>

                            <input type="checkbox" id="mesEncheresEnCours" name="checkBoxFiltre2" value="mesEncheresEnCours">
                            <label for="mesEncheresEnCours">Mes enchères en cours</label>

                            <input type="checkbox" id="mesEncheresRemportees" name="checkBoxFiltre3" value="mesEncheresRemportees">
                            <label for="mesEncheresRemportees">Mes enchères remportées</label>
                        </div>

                        <!--Achat + checkbox pour approfondir filtre "Achat"-->
                        <div class="ventes">
                            <label for="mesVentes">Ventes</label>
                            <input type="radio" id="radioVentes" name="choixRadio" value="Mes ventes">

                            <input type="checkbox" id="mesVentesEnCours" name="checkBoxFiltre4" value="mesVentesEnCours">
                            <label for="mesVentesEnCours">Mes ventes en cours</label>

                            <input type="checkbox" id="ventesNonDebutees" name="checkBoxFiltre5" value="ventesNonDebutees">
                            <label for="ventesNonDebutees">Ventes non débutées</label>

                            <input type="checkbox" id="ventesTerminees" name="checkBoxFiltre6" value="ventesTerminees">
                            <label for="ventesTerminees">Ventes Terminées</label>
                        </div>
                        
                    </div>
                    
                    <!--Boutton "submit" recherche-->
                </div>
                    <div>
                        <button type="submit" class="bouton-rechercher">Rechercher</button>
                    </div>
            </form>
            
            <!--Liste encheres en cours-->
            <div>


                        <!--image de l'annonce-->
                        	<div class="articlesContainer">
                            <c:forEach var="listeDesArticles" items="${articles}">
                            		<div class="article">
                            			<img class="imageArticle" src="./assets/img/imgEnchere.png" alt="Image de l'enchere en cours"/>
                        				<div class="texteContainer">
                        				<div class="texteArticle">
                        					<a href="${pageContext.request.contextPath}/DetailVente?no_article=${listeDesArticles.no_article}"><c:out value="${listeDesArticles.nom_article}"></c:out></a><br>
                        					Prix : <c:out value="${listeDesArticles.prix_initial}"></c:out><br>
                        					Fin de l'enchère : <c:out value="${listeDesArticles.date_fin_encheres}"></c:out><br><br>
                        					Vendeur :<a href="${pageContext.request.contextPath}/monCompte?pseudo=${listeDesArticles.utilisateur.pseudo}"><c:out value="${listeDesArticles.utilisateur.pseudo}"></c:out></a><br><br>
                        				</div>
                        				</div>
                        			</div>
                        	</c:forEach>
                        	</div>
                        
                        

            </div>

            
        </div>
    </main>