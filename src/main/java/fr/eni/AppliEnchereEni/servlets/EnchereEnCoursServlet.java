package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.AppliEnchereEni.bll.EnchereManager;
import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class EnchereEnCoursServlet
 */
@WebServlet("/EnchereEnCours")
public class EnchereEnCoursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnchereEnCoursServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//Recuperation des informations de l'utilisateur à partir de la session pour
		//comparer l'id de l'utilisateur à celui des autres annonces et afficher les annonces
		//qui ne sont pas de l'utilisateur, où la date du jour est incluse entre la date de début et
		//fin de l'enchere
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		EnchereManager enchereManager = EnchereManager.getInstance();
		List <ArticleVendu> enchereEnCours = enchereManager.listerEnchereEnCours(utilisateur);

		request.setAttribute("listeDesEncheresEnCours",enchereEnCours);
		request.getRequestDispatcher("/WEB-INF/jsp/enchereEnCours.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
