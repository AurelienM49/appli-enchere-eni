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
                <form action="" method="get">
                    <div>
                        <input type="text" placeholder="Le nom de l'article contient">
                    </div>
                    
                    <!--Selection categorie-->
                    <div>
                        <label for="categorie">Categorie : </label>
                        <select name="categorie" id="categorie">
                    	    <option value="informatique">Informatique</option>
                    	    <option value="ameublement">Ameublement</option>
                    	    <option value="vêtement">Vêtement</option>
                    	    <option value="sport&Loisir">Sport&Loisirs</option>
                         </select>
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
                            <a href="#">Lien de redirection vers annonce de l'enchere</a>
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
                            <a href="#">Lien de redirection vers annonce de l'enchere</a>
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
                            <a href="#">Lien de redirection vers annonce de l'enchere</a>
                            <p>Prix :</p>
                            <p>Fin de l'enchère :</p>
                            <p>Vendeur :</p>
                        </div>

                    </li>
                </ul>
            </div>

            
        </div>
    </main>