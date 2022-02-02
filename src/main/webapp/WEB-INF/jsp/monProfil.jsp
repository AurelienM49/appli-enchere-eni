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
                    <input type="text" name="pseudo" id="pseudo" class="form-item">
                    <label for="nom">Nom : </label>
                    <input type="text" name="nom" id="nom" class="form-item">
                    <label for="prenom">Prenom : </label>
                    <input type="text" name="prenom" id="prenom" class="form-item">
                    <label for="email">Email : </label>
                    <input type="email" name="email" id="email" class="form-item">
                    <label for="tel">Telephone : </label>
                    <input type="tel" name="tel" id="tel" class="form-item">
                    <label for="rue">Rue : </label>
                    <input type="text" name="rue" id="rue" class="form-item">
                    <label for="cpo">Code Postal : </label>
                    <input type="text" name="cpo" id="cpo" class="form-item">
                    <label for="ville">Ville : </label>
                    <input type="text" name="ville" id="ville" class="form-item">
                    <label for="mdpActuel">Mot de passe actuel: </label>
                    <input type="password" name="mdpActuel" id="mdpActuel" class="form-item">
                    <label for="nouveauMdp">Nouveau mot de passe : </label>
                    <input type="password" name="NouveauMdp" id="NouveauMdp" class="form-item">
					<label for="confirmation">Confirmation : </label>
                    <input type="password" name="confirmation" id="confirmation" class="form-item">
                    
                    <p>Crédit : ${utilisateur.credit}</p>
					
                    <button type="submit">Enregistrer</button>
                    <button type="reset">Supprimer mon compte</button>
                </form>

</body>
</html>