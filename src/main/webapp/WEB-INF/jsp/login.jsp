<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<form action="" method="post">

<label for="identifiant">Identifiant : </label>
<input type="text" name="identifiant" id="identifiant" class="form-item" required>

<label for="mdp">Mot de passe : </label>
<input type="password" name="mdp" id="mdp" class="form-item" required>

<button type="submit">Connexion</button>

<label for="seSouvenir">Se souvenir de moi</label>
<input type="checkbox" name="seSouvenir" id="seSouvenir" class="form-item">

<a href="<%=request.getContextPath()%>/motDePasseOublie">Mot de passe oubli�</a>



<a href="<%=request.getContextPath()%>/register">Cr�er un compte</a>




</form>
</body>
</html>