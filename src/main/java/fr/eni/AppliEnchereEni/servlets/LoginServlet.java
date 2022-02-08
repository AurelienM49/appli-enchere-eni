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
import fr.eni.AppliEnchereEni.helpers.HashPassword;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperer les paramètres du formulaire
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		Utilisateur utilisateur = new Utilisateur();
		
		//determiner si l'identifiant choisi par l'utilisateur est un pseudo ou une adresse mail
		//on utilise la methode indexOf() si un @ est présent dans l'identifiant ==> il s'agit 
		//d'un Email / si indexof() retourne -1 c'est qu'il n'y pas d'@ dans l'identitifiant donc 
		// il s'agit d'un pseudo
		String logErr = null;
		int index = identifiant.indexOf('@');
		if (index == -1) {
			utilisateur.setPseudo(identifiant);
			logErr = utilisateur.getPseudo();
		} else {
			utilisateur.setEmail(identifiant);
			logErr = utilisateur.getEmail();
		}
		utilisateur.setMot_de_passe(HashPassword.hashpassword(mdp));
	
		
		UtilisateurManager um = UtilisateurManager.getInstance();
		if(um.loginUtilisateur(utilisateur)!=null) {
			HttpSession session = request.getSession();
			utilisateur = um.loginUtilisateur(utilisateur);
			utilisateur.setMot_de_passe(null);
			session.setAttribute("utilisateur", utilisateur);
			request.getRequestDispatcher("/HomeSansFiltre").forward(request, response); 
			
		}else {
			
			request.setAttribute("loginError", logErr);
			request.setAttribute("error", "identifiant ou mot de pas incorrect");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
		
	}

}
