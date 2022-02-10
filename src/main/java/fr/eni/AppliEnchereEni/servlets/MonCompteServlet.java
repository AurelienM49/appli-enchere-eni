package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.AppliEnchereEni.bll.UtilisateurManager;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class monCompteServlet
 */
@WebServlet("/monCompte")
public class MonCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//recuperer l'utilisateur en session
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		String pseudo = request.getParameter("pseudo");
		
		//Si le pseudo passé en parametre de la requête est null ou avec un espace vide
		//On recupère le pseudo de l'utilisateur de la session et on peut donc afficher son profil perso
		if (pseudo == null || pseudo.isBlank()) {
			pseudo = user.getPseudo();
		}

		// appel au manager qui va lui faire appel à la BDD et chercher les info d'un utilisateur par son pseudo
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur profil = um.selectByPseudo2(pseudo);
		
		//2 possibilités : soit on set les info du User qui est en session, ou bien les infos du vendeur (profil) 
		request.setAttribute("profil", profil);
		request.setAttribute("utilisateur", user);
		//On delegue ensuite à monCompte.jsp
		request.getRequestDispatcher("/WEB-INF/jsp/monCompte.jsp").forward(request, response);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");				
		request.setAttribute("utilisateur", user);
		boolean verifCnx = true;
		request.setAttribute("verifCnx", verifCnx);
		request.getRequestDispatcher("/WEB-INF/jsp/monCompte.jsp").forward(request, response);

	}

}
