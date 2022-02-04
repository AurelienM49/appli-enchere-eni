<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE>
<html>
<head>
<link href="./assets/style.css" rel="stylesheet">

<meta charset="UTF-8">
<title></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" class="form-login" method="post">

	<div class="form-box">
		<div class="big-container-login">
			<div class="container-login">
				<div class="input-identifiant">
					<label for="identifiant">Identifiant : </label>
					<input type="text" name="identifiant" id="identifiant" class="form-item" required>
				</div>	
				<div class="input-mdp">
					<label for="mdp">Mot de passe : </label>
					<input type="password" name="mdp" id="mdp" class="form-item" required>
					<span class="erreur">${error}</span>
				</div>
			</div>	
				<button type="submit" class="btn-connexion">Connexion</button>
		</div>			
					<label for="seSouvenir">Se souvenir de moi</label>
					<input type="checkbox" name="seSouvenir" id="seSouvenir" class="form-item">
					
					<a href="<%=request.getContextPath()%>/motDePasseOublie">Mot de passe oublié</a>	
					<a href="<%=request.getContextPath()%>/register">Créer un compte</a>
	</div>
</form>
</body>
</html>