package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.AppliEnchereEni.bll.ArticleManager;
import fr.eni.AppliEnchereEni.bo.ArticleVendu;

/**
 * Servlet implementation class indexServlet
 */
@WebServlet("")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ======= > pour afficher la liste en mode connecté sans les filtres 
		
					//appel a ArticleManager
					ArticleManager am = ArticleManager.getInstance();
					
					
					//appel à la methode afficher10Article, les 10 articles dont les dates d'enchères sont les plus proches
					List<ArticleVendu> listeArticles = am.afficher10Articles();
					
					
					request.setAttribute("articles", listeArticles);
					
		
		
		
		request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
