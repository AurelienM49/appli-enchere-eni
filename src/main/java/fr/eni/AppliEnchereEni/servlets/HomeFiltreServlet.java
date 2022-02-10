package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.AppliEnchereEni.bll.ArticleManager;
import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class FiltreRechercheServlet
 */
@WebServlet("/HomeFiltre")
public class HomeFiltreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeFiltreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager am = ArticleManager.getInstance();
		List<ArticleVendu> listeArticles = null;
		List<ArticleVendu> articles = null;

		
		if (request.getSession().getAttribute("utilisateur")==null) {
			
			String rechercheMotArt = request.getParameter("rechercheMotArt");
			String categorie = request.getParameter("categorie");
			
			articles = am.filtreDeconnecte(rechercheMotArt,categorie);
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);

			
			
		} else if(request.getSession().getAttribute("utilisateur")!=null) {
			String rechercheMotArt = request.getParameter("rechercheMotArt");
			String categorie = request.getParameter("categorie");
			String choixRadio = request.getParameter("choixRadio");
			String checkbox1 = request.getParameter("checkBoxFiltre1");
			String checkbox2 = request.getParameter("checkBoxFiltre2");
			String checkbox3 = request.getParameter("checkBoxFiltre3");
			String checkbox4 = request.getParameter("checkBoxFiltre4");
			String checkbox5 = request.getParameter("checkBoxFiltre5");
			String checkbox6 = request.getParameter("checkBoxFiltre6");
			
			
		// ======= > pour afficher la liste en mode connecté avec les filtres 		
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");		
		
		listeArticles = am.filtreConnecte(user, rechercheMotArt, categorie, choixRadio, checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6);
		
		request.setAttribute("articles", listeArticles);
		request.getRequestDispatcher("/WEB-INF/jsp/accueilLoged.jsp").forward(request, response);
		}
			

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
