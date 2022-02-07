<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE>
<html>
<head>
<link href="./assets/formLogin.css" rel="stylesheet">

<meta charset="UTF-8">
<title></title>
</head>
<body>
<section id="contact">
<div class="login-form">
<form action="${pageContext.request.contextPath}/login"  method="post">
    <h1>Login</h1>
	<div class="content">
		<div class="input-field">
			<input type="text" name="identifiant" id="identifiant" placeholder="Email ou pseudo" class="form-item" required>
		</div>	
		<div class="input-field">
			<input type="password" name="mdp" id="mdp" placeholder="Mot de passe"class="form-item" required>
		</div>
					<span class="erreur">${error}</span>
		<div class="other">
			<div class="remember">
				<label for="seSouvenir">Se souvenir de moi</label>
				<input type="checkbox" name="seSouvenir" id="seSouvenir" class="form-item">	
			</div>	
		</div>
				
		<div class="action">
			<button type="submit" class="btn-connexion">Connexion</button>
			<div class="mdp-forgot">
				<a href="<%=request.getContextPath()%>/motDePasseOublie" class="link">Mot de passe oublié</a>	
			</div>	
		</div>		
		<div class="txt-account">
			<span>Vous n'êtes pas membre ? </span><a href="<%=request.getContextPath()%>/register" class="create-account">Créer un compte</a>	
		</div>	
	</div>
</form>
</div>
</section>
</body>
</html>