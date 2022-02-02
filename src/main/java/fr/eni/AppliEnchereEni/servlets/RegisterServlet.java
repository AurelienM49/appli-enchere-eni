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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur user = new Utilisateur ();	
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
			
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

		
		HashMap<String, String> listeErreurs = um.validationUser(user);
		
		if(listeErreurs.isEmpty()) {
			um.ajouterUtilisateur(user);
			request.getRequestDispatcher("/").forward(request, response);
		}else {
			request.setAttribute("listeErreurs", listeErreurs);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		}
		
		


	}

}
