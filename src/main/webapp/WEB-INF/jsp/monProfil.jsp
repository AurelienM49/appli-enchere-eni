<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon Profil</title>
</head>
<body>
<h1>Mon Profil</h1>

                <form action="<%=request.getContextPath()%>/monProfil" method="post">
                    <label for="pseudo">Pseudo : </label>
                    <input type="text" name="pseudo" id="pseudo" value="${user.pseudo}" class="form-item">
                    <span class="erreur">${listeErreurs['emptyPseudo']}</span>
                   	<span class="erreur">${listeErreurs['pseudoCarSpeciaux']}</span>
                    <span class="erreur">${listeErreurs['existPseudo']}</span>
 
                    <label for="nom">Nom : </label>
                    <input type="text" name="nom" id="nom" value="${user.nom}" class="form-item">
                   	<span class="erreur">${listeErreurs['emptyNom']}</span>
                    
                    <label for="prenom">Prenom : </label>
                    <input type="text" name="prenom" id="prenom" value="${user.prenom}" class="form-item">
                     <span class="erreur">${listeErreurs['emptyPrenom']}</span>
                     
                    <label for="email">Email : </label>
                    <input type="email" name="email" id="email" value="${user.email}" class="form-item">
                    <span class="erreur">${listeErreurs['emptyEmail']}</span>
                    <span class="erreur">${listeErreurs['existEmail']}</span>
                    
                    
                    
                    <label for="tel">Telephone : </label>
                    <input type="tel" name="tel" id="tel" value="${user.telephone}" class="form-item">
                    <span class="erreur">${listeErreurs['emptyTel']}</span>
                    
                    
                    <label for="rue">Rue : </label>
                    <input type="text" name="rue" id="rue" value="${user.rue}" class="form-item">
                    <span class="erreur">${listeErreurs['emptyRue']}</span>
                    
                    <label for="cpo">Code Postal : </label>
                    <input type="text" name="cpo" id="cpo" value="${user.code_postal}" class="form-item">
                    <span class="erreur">${listeErreurs['cpoInconnu']}</span>
                    
                    <label for="ville">Ville : </label>
                    <input type="text" name="ville" id="ville" value="${user.ville}" class="form-item">
                    <span class="erreur">${listeErreurs['emptyVille']}</span>
                    
                    <label for="mdpActuel">Mot de passe actuel: </label>
                    <input type="password" name="mdpActuel" id="mdpActuel" class="form-item">
                    <span class="erreur">${listeErreurs['mdpActuelFalse']}</span>
                    
                    <label for="nouveauMdp">Nouveau mot de passe : </label>
                    <input type="password" name="nouveauMdp" id="nouveauMdp" class="form-item">
                    
					<label for="confirmation">Confirmation : </label>
                    <input type="password" name="confirmation" id="confirmation" class="form-item">
                    <span class="erreur">${listeErreurs['mdpDifferents']}</span>
                    
                    
                    <p>Crédit : ${utilisateur.credit}</p>
					
                    <button type="submit">Enregistrer</button>
                    <button type="reset">Supprimer mon compte</button>
                </form>

</body>
</html>