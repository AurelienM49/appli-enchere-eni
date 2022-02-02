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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
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
		//recuperer les param�tres du formulaire
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		Utilisateur utilisateur = new Utilisateur();
		
		//determiner si l'identifiant choisi par l'utilisateur est un pseudo ou une adresse mail
		//on utilise la methode indexOf() si un @ est pr�sent dans l'identifiant ==> il s'agit 
		//d'un Email / si indexof() retourne -1 c'est qu'il n'y pas d'@ dans l'identitifiant donc 
		// il s'agit d'un pseudo
		int index = identifiant.indexOf('@');
		if(index == -1) {
			utilisateur.setPseudo(identifiant);
		} else {
			utilisateur.setEmail(identifiant);
		}
		utilisateur.setMot_de_passe(mdp);
		
		UtilisateurManager um = UtilisateurManager.getInstance();
		if(um.loginUtilisateur(utilisateur)) {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath()+"/monCompte");
		}else {
			request.setAttribute("error", "identifiant ou mot de pas incorrect");
			request.getRequestDispatcher("/WEB-INF/jsp/erreurLogin.jsp").forward(request, response);;
		}
		
	}

}