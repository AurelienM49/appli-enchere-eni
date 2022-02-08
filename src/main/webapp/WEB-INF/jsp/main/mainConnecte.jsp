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
                <form action="${pageContext.request.contextPath}/filtre" method="get">
                    <div>
                        <input type="text" name="rechercheMotArt" placeholder="Le nom de l'article contient">
                    </div>
                    
                    <!--Selection categorie-->
                    <div>
                        <label for="categorie">Categorie : </label>
                        <select name="categorie" id="categorie">
                        	<option value="all">Toutes</option>
                    	    <option value="informatique">Informatique</option>
                    	    <option value="ameublement">Ameublement</option>
                    	    <option value="vêtement">Vêtement</option>
                    	    <option value="sport&loisirs">Sport&Loisirs</option>
                         </select>
                    </div>

                    <!--Button "radio" pour selection filtre "Achat" / "Mes Ventes"-->
                    <div>

                        <!--Achat + checkbox pour approfondir filtre "Achat"-->
                        <div>
                            <label for="achats">Achats</label>
                            <input type="radio" id="radioAchats" name="choixRadio" value="Achats" checked="checked">

                            <label for="encheresOuvertes">Enchères ouvertes</label>
                            <input type="checkbox" id="encheresOuvertes" name="checkBoxFiltre1" value="encheresOuvertes">

                            <label for="mesEncheresEnCours">Mes enchères en cours</label>
                            <input type="checkbox" id="mesEncheresEnCours" name="checkBoxFiltre2" value="mesEncheresEnCours">

                            <label for="mesEncheresRemportees">Mes enchères remportées</label>
                            <input type="checkbox" id="mesEncheresRemportees" name="checkBoxFiltre3" value="mesEncheresRemportees">
                        </div>

                        <!--Achat + checkbox pour approfondir filtre "Achat"-->
                        <div>
                            <label for="mesVentes">Mes ventes</label>
                            <input type="radio" id="radioVentes" name="choixRadio" value="Mes ventes">

                            <label for="mesVentesEnCours">Mes ventes en cours</label>
                            <input type="checkbox" id="mesVentesEnCours" name="checkBoxFiltre4" value="mesVentesEnCours">

                            <label for="ventesNonDebutees">Ventes non débutées</label>
                            <input type="checkbox" id="ventesNonDebutees" name="checkBoxFiltre5" value="ventesNonDebutees">

                            <label for="ventesTerminees">Ventes Terminées</label>
                            <input type="checkbox" id="ventesTerminees" name="checkBoxFiltre6" value="ventesTerminees">
                        </div>
                        
                    </div>
                    
                    <!--Boutton "submit" recherche-->
                    <div>
                        <button type="submit">Rechercher</button>
                    </div>
                </form>
            </div>
            
            <!--Liste encheres en cours-->
            <div>
                <ul class="listeEnchereEnCours">
                    
                    
                    <li class="annonceEnchereEnCours">

                        <!--image de l'annonce-->
                        <div>
                            <img src="/temporaire/imgEnchereEnCours.png" alt="Image de l'enchere en cours">
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/DetailVente">${articleVendu.nom}</a>
                            <p>Prix : ${articleVendu.prix}</p>
                            <p>Fin de l'enchère : ${articleVendu.dateFinEnchere}</p>
                            <p>Vendeur : <a href="#"></a></p>
                        </div>
                        
						
                    </li>
                    
                    <li class="annonceEnchereEnCours">

                        <!--image de l'annonce-->
                        <div>
                            <img src="/temporaire/imgEnchereEnCours.png" alt="Image de l'enchere en cours">
                        </div>
                        <div>
                            <p>Prix :</p>
                            <p>Fin de l'enchère :</p>
                            <p>Vendeur :</p>
                        </div>

                    </li>
                    <li class="annonceEnchereEnCours">

                        <!--image de l'annonce-->
                        <div>
                            <img src="/temporaire/imgEnchereEnCours.png" alt="Image de l'enchere en cours">
                        </div>
                        <div>
                            <p>Prix :</p>
                            <p>Fin de l'enchère :</p>
                            <p>Vendeur :</p>
                        </div>

                    </li>
                </ul>
            </div>

            
        </div>
    </main>