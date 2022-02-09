<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<link href="./assets/register.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Mon Profil</title>
</head>
<body>
<section id="contact">
		<div class="formulaire">
			<form action="${pageContext.request.contextPath}/monProfil"
				method="post">
				<div class="center">
					<h1 class="title">Modifier mon profil</h1>
					<div class="both">
						<div class="leftForm">

							<div class="champ">
								<input placeholder="Pseudo" type="text" name="pseudo"
									id="pseudo" value="${user.pseudo}" class="form-item">
							</div>
							<span class="erreur">${listeErreurs['emptyPseudo']}</span> 
							<span class="erreur">${listeErreurs['pseudoCarSpeciaux']}</span>
							 <span class="erreur">${listeErreurs['existPseudo']}</span>

							<div class="champ">
								<input placeholder="Prenom" type="text" name="prenom"
									id="prenom" value="${user.prenom}" class="form-item">

							</div>
								<span class="erreur">${listeErreurs['emptyTel']}</span>
								
							<div class="champ">
								<input placeholder="Telephone" type="tel" name="tel" id="tel"
									value="${user.telephone}" class="form-item">
							</div>
							<span class="erreur">${listeErreurs['emptyTel']}</span>

							<div class="champ">
								<input placeholder="Code postal" type="text" name="cpo" id="cpo"
									value="${user.code_postal}" class="form-item">
							</div>
							<span class="erreur">${listeErreurs['cpoInconnu']}</span>

							<div class="champ">
								<input placeholder="Ville" type="text" name="ville" id="ville"
									value="${user.ville}" class="form-item">
							</div>
							<span class="erreur">${listeErreurs['emptyVille']}</span>


						</div>


						<div class="rightForm">
							<div class="champ">
								<input placeholder="Nom" type="text" name="nom" id="nom"
									value="${user.nom}" class="form-item">
							</div>
							<span class="erreur">${listeErreurs['emptyNom']}</span>

							<div class="champ">
								<input placeholder="Email" type="email" name="email" id="email"
									value="${user.email}" class="form-item">
							</div>
							<span class="erreur">${listeErreurs['emptyEmail']}</span> <span
								class="erreur">${listeErreurs['existEmail']}</span>


							<div class="champ">
								<input placeholder="Rue" type="text" name="rue" id="rue"
									value="${user.rue}" class="form-item">
							</div>
							<span class="erreur">${listeErreurs['emptyRue']}</span>
							
							<div class="champ">
								<input
									type="password" name="mdpActuel" id="mdpActuel"
									class="form-item" placeholder="Mot de passe actuel: ">
							</div>
							<span class="erreur">${listeErreurs['mdpActuelFalse']}</span>
						


							<div class="champ">
								<input
									type="password" name="nouveauMdp" id="nouveauMdp"
									class="form-item" placeholder="Nouveau mot de passe :  ">
							</div>
							
							
							<div class="champ">
								<input
									type="password" name="confirmation" id="confirmation"
									class="form-item" placeholder="Confirmation nouveau mot de passe :">
							</div>
							<span class="erreur">${listeErreurs['mdpDifferents']}</span>
						</div>
					</div>
					
					<p>Crédit : ${utilisateur.credit}</p>
					
					<div class="buttons">
						<button type="submit" class="create">Enregistrer</button>
						<a href="${pageContext.request.contextPath}/supression?no_utilisateur=${user.no_utilisateur}"><button
								type="button" class="supprimer">Supprimer</button></a>					
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
</html>