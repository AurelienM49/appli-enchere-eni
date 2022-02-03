<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <!DOCTYPE>
            <html>


            <head>
                <link href="./assets/style.css" rel="stylesheet">
                <meta charset="UTF-8">
                <title>Register</title>
            </head>

            <body>

                <h1>Créer un nouveau compte</h1>

                <form action="${pageContext.request.contextPath}/register" method="post">
                    
                    <div class="center">
                        <div class="both">
	                        <div class="leftForm">
	                            
	                            <div class="champ">
	                            	<label for="pseudo">Pseudo :</label>
	                            	<input type="text" name="pseudo" id="pseudo" class="form-item">
	                            	<span class="erreur">${listeErreurs['emptyPseudo']}</span>
	                            	<span class="erreur">${listeErreurs['pseudoCarSpeciaux']}</span>
	                            	<span class="erreur">${listeErreurs['existPseudo']}</span>
								</div>
	                            
	                            <div class="champ">
		                            <label for="prenom">Prenom :</label>
		                            <input type="text" name="prenom" id="prenom" class="form-item">
		                            <span class="erreur">${listeErreurs['emptyPrenom']}</span>
								</div>
								
								<div class="champ">
		                            <label for="tel">Telephone :</label>
		                            <input type="tel" name="tel" id="tel" class="form-item">
		                            <span class="erreur">${listeErreurs['emptyTel']}</span>
								</div>
								
								<div class="champ">
		                            <label for="cpo">Code Postal :</label>
		                            <input type="text" name="cpo" id="cpo" class="form-item">
		                            <span class="erreur">${listeErreurs['emptyCpo']}</span>
								</div>
								
								<div class="champ">
		                            <label for="mdp">Mot de passe :</label>
		                            <input type="password" name="mdp" id="mdp" class="form-item">
		                            <span class="erreur">${listeErreurs['emptyMdp']}</span>
	                            </div>
	                        </div>
	
	
	                        <div class="rightForm">
	                            <div class="champ">
	                            	<label for="nom">Nom :</label>
	                            	<input type="text" name="nom" id="nom" class="form-item">
	                            	<span class="erreur">${listeErreurs['emptyNom']}</span>
	                            </div>
	                            
	                            <div class="champ">
	                            	<label for="email">Email :</label>
	                            	<input type="email" name="email" id="email" class="form-item">
	                            	<span class="erreur">${listeErreurs['emptyEmail']}</span>
	                            	<span class="erreur">${listeErreurs['existEmail']}</span>
								</div>
								
								<div class="champ">
		                            <label for="rue">Rue :</label>
		                            <input type="text" name="rue" id="rue" class="form-item">
		                            <span class="erreur">${listeErreurs['emptyRue']}</span>
								</div>
								
								<div class="champ">
		                            <label for="ville">Ville :</label>
		                            <input type="text" name="ville" id="ville" class="form-item">
		                            <span class="erreur">${listeErreurs['emptyVille']}</span>
								</div>
								
								<div class="champ">
		                            <label for="mdp-confirm">Confirmation :</label>
		                            <input type="password" name="mdp-confirm" id="mdp-confirm" class="form-item">
		                            <span class="erreur">${listeErreurs['emptyMdpConfirm']}</span>
		                            <span class="erreur">${listeErreurs['mdpDifferents']}</span>
	                        	</div>
	                        </div>
	                        
                </div>

                <div class="buttons">
                    <button type="submit">Créer</button>
                    <a href="${pageContext.request.contextPath }/"><button type="button">Annuler</button></a>
                </div>    
            </form>




            </body>

            </html>