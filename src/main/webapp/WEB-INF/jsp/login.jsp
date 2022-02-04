<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">

	<label for="identifiant">Identifiant : </label>
	<input type="text" name="identifiant" id="identifiant" class="form-item" required>
	
	<label for="mdp">Mot de passe : </label>
	<input type="password" name="mdp" id="mdp" class="form-item" required>
	<span class="erreur">${error}</span>
	
	
	
	<button type="submit">Connexion</button>
	
	<label for="seSouvenir">Se souvenir de moi</label>
	<input type="checkbox" name="seSouvenir" id="seSouvenir" class="form-item">
	
	<a href="<%=request.getContextPath()%>/motDePasseOublie">Mot de passe oublié</a>
	
	<a href="<%=request.getContextPath()%>/register">Créer un compte</a>

</form>
</body>
</html>