<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <!DOCTYPE>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Register</title>
            </head>

            <body>
 
                <h1>Register</h1>
                <form action="<%=request.getContextPath()%>/register" method="post">
                    <label for="pseudo">Pseudo : </label>
                    <input type="text" name="pseudo" id="pseudo" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyPseudo']}</span>
                   	<span class="erreur">${listeErreurs['pseudoCarSpeciaux']}</span>
                    <span class="erreur">${listeErreurs['existPseudo']}</span>
                    
                    <label for="nom">Nom : </label>
                    <input type="text" name="nom" id="nom" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyNom']}</span>
                    
                    <label for="prenom">Prenom : </label>
                    <input type="text" name="prenom" id="prenom" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyPrenom']}</span>
                    
                    <label for="email">Email : </label>
                    <input type="email" name="email" id="email" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyEmail']}</span>
                    <span class="erreur">${listeErreurs['existEmail']}</span>
                    
                    
                    <label for="tel">Telephone : </label>
                    <input type="tel" name="tel" id="tel" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyTel']}</span>
                    
                    <label for="rue">Rue : </label>
                    <input type="text" name="rue" id="rue" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyRue']}</span>
                    
                    <label for="cpo">Code Postal : </label>
                    <input type="text" name="cpo" id="cpo" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyCpo']}</span>
                    
                    <label for="ville">Ville : </label>
                    <input type="text" name="ville" id="ville" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyVille']}</span>
                    
                    <label for="mdp">Mot de passe : </label>
                    <input type="password" name="mdp" id="mdp" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyMdp']}</span>
                    
                    <label for="mdp-confirm">Confirmation : </label>
                    <input type="password" name="mdp-confirm" id="mdp-confirm" class="form-item" required>
                    <span class="erreur">${listeErreurs['emptyMdpConfirm']}</span>
                    <span class="erreur">${listeErreurs['mdpDifferents']}</span>



                    <button type="submit">Créer</button>
                    <button type="reset">Annuler</button>
                </form>



            </body>

            </html>