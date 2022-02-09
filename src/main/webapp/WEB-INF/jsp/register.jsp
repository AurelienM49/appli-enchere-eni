
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <!DOCTYPE>
            <html>


            <head>
                <link href="./assets/register.css" rel="stylesheet">
                <meta charset="UTF-8">
                <title>Register</title>
            </head>

            <body>

             
			<section id="contact">	
			   <div class="formulaire">				
                <form action="${pageContext.request.contextPath}/register" method="post">
                    <div class="center">
                      <h1 class="title">Créer un nouveau compte</h1>
                        <div class="both">
	                        <div class="leftForm">
	                            
	                            <div class="champ">
	                            	<input placeholder="Pseudo" type="text" name="pseudo" id="pseudo" value="${userError.pseudo}" class="form-item">
								</div>
	                            	<span class="erreur">${listeErreurs['emptyPseudo']}</span>
	                            	<span class="erreur">${listeErreurs['pseudoCarSpeciaux']}</span>
	                            	<span class="erreur">${listeErreurs['existPseudo']}</span>
	                            
	                            <div class="champ">
		                            <input placeholder="Prenom" type="text" name="prenom" id="prenom" value="${userError.prenom}" class="form-item">
		                            
								</div>
		                            <span class="erreur">${listeErreurs['emptyPrenom']}</span>
								
								<div class="champ">
		                            <input placeholder="Telephone" type="tel" name="tel" id="tel" value="${userError.telephone}" class="form-item">
								</div>
		                            <span class="erreur">${listeErreurs['emptyTel']}</span>
								
								<div class="champ">
		                            <input placeholder="Code postal" type="text" name="cpo" id="cpo" value="${userError.code_postal}" class="form-item">
								</div>
		                            <span class="erreur">${listeErreurs['emptyCpo']}</span>
		                            
								<div class="champ">
		                            <input placeholder="Ville" type="text" name="ville" id="ville" value="${userError.ville}" class="form-item">
								</div>
		                            <span class="erreur">${listeErreurs['emptyVille']}</span>
								
								
	                        </div>
	
	
	                        <div class="rightForm">
	                            <div class="champ">
	                            	<input placeholder="Nom" type="text" name="nom" id="nom" value="${userError.nom}" class="form-item">
	                            </div>
	                            	<span class="erreur">${listeErreurs['emptyNom']}</span>
	                            
	                            <div class="champ">
	                            	<input placeholder="Email" type="email" name="email" id="email" value="${userError.email}" class="form-item">
								</div>
	                            	<span class="erreur">${listeErreurs['emptyEmail']}</span>
	                            	<span class="erreur">${listeErreurs['existEmail']}</span>
								
								<div class="champ">
		                            <input placeholder="Rue" type="text" name="rue" id="rue" value="${userError.rue}" class="form-item">
								</div>
		                            <span class="erreur">${listeErreurs['emptyRue']}</span>
								<div class="champ">
		                            <input placeholder="Mot de passe" type="password" name="mdp" id="mdp"  class="form-item">
	                            </div>
		                            <span class="erreur">${listeErreurs['emptyMdp']}</span>
								
								
								<div class="champ">
		                            <input placeholder="Confirmation mot de passe" type="password" name="mdp-confirm" id="mdp-confirm" class="form-item">
	                        	</div>
		                            <span class="erreur">${listeErreurs['emptyMdpConfirm']}</span>
		                            <span class="erreur">${listeErreurs['mdpDifferents']}</span>
	                        </div>
	                </div> 
	            <div class="buttons">
                    <button type="submit" class="create">Créer</button>
                    <a href="${pageContext.request.contextPath }/"><button type="button" class="reset">Annuler</button></a>
                </div>           
                </div>             
            </form>
          </div>      
		</section>



            </body>

            </html>
