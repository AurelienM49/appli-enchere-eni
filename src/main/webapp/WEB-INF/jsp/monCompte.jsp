<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>MonCompte</title>
    </head>

    <body>
        <ul>
            <li>Pseudo : ${utilisateur.pseudo}</li>
            <li>Nom : ${utilisateur.nom}</li>
            <li>Prenom : ${utilisateur.prenom}</li>
            <li>Email : ${utilisateur.email}</li>
            <li>Telephone : ${utilisateur.telephone}</li>
            <li>Rue : ${utilisateur.rue}</li>
            <li>Code postal : ${utilisateur.code_postal}</li>
            <li>Ville : ${utilisateur.ville}</li>
        </ul>
        

    </body>

    </html>