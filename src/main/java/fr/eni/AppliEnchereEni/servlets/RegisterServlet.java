package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.AppliEnchereEni.bll.UtilisateurManager;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.helpers.HashPassword;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**Methode doGet permettant de deleguer la requ�te � la jsp
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

	/**Methode dopost permettant de recuperer les informations saisies dans le formulaire, de faire appel � la bll,
	 * et de traiter les �ventuelles erreurs de saisie.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur user = new Utilisateur ();	
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//on r�cup�re toutes les infos que l'utilisateur rentr�es lors de l'inscription	
		user.setPseudo(request.getParameter("pseudo"));
		user.setNom(request.getParameter("nom"));
		user.setPrenom(request.getParameter("prenom"));
		user.setEmail(request.getParameter("email"));
		user.setTelephone(request.getParameter("tel"));
		user.setRue(request.getParameter("rue"));
		user.setCode_postal(request.getParameter("cpo"));
		user.setVille(request.getParameter("ville"));
		user.setMot_de_passe(HashPassword.hashpassword(request.getParameter("mdp")));
		user.setMot_de_passe_cofirm(HashPassword.hashpassword(request.getParameter("mdp-confirm")));

		//Appel � la m�thode validationUser qui retourne une liste d'erreurs
		HashMap<String, String> listeErreurs = um.validationUser(user);
		
		//si la liste est vide alors on ajoute l'utilisateur sinon on retourne la map 
		//qui contient les erreurs
		if(listeErreurs.isEmpty()) {
			um.ajouterUtilisateur(user);
			request.getRequestDispatcher("/").forward(request, response);
		}else {
			request.setAttribute("userError", user);
			request.setAttribute("listeErreurs", listeErreurs);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		}
		
		


	}

}