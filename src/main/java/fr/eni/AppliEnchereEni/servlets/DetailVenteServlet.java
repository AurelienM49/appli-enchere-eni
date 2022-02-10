package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.AppliEnchereEni.bll.ArticleManager;
import fr.eni.AppliEnchereEni.bll.EnchereManager;
import fr.eni.AppliEnchereEni.bll.UtilisateurManager;
import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.bo.Enchere;
import fr.eni.AppliEnchereEni.bo.Retrait;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAOJdbcImpl;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Retrait retrait = new Retrait();
		
		Enchere enchere = new Enchere();
		
		
		
		ArticleVendu article = new ArticleVendu();
		article.setNo_article(Integer.valueOf(request.getParameter("no_article")));

		//article.setCategorie(request.getParameter(""));
		ArticleManager am = ArticleManager.getInstance();
		article = am.selectByIDTop1(article);
		
		
		
		//article.getCategorie().setLibelle(request.getParameter("libelle"));
		request.setAttribute("article", article);
		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//Recupération des informations utilisateur à partir de la Session
		Utilisateur  utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		//Recuperation du numero de l'article dans les parametre de la requete
		 ArticleVendu articleVendu = new ArticleVendu();
		 articleVendu.setNo_article(Integer.valueOf(request.getParameter("no_article")));  
		 
		//Recuperation de prix propose dans la methode post
		int maProposition =Integer.valueOf(request.getParameter("proposition"));
		
		EnchereManager enchereManager = EnchereManager.getInstance();
		Enchere enchere = new Enchere();
		enchere.setUtilisateur(utilisateur);
		enchere.setArticle(articleVendu);
		enchere.setDate_enchere(LocalDate.now());
		enchere.setMontant_enchere(maProposition);
		
		ArticleManager am = ArticleManager.getInstance();
		articleVendu = am.selectByIDTop1(articleVendu);
		
		if (articleVendu.getEnchere().getMontant_enchere() > maProposition) {
			request.setAttribute("propositionInf", "La proposition est inférieur à la plus grosse offre");		
			request.setAttribute("article", articleVendu);
			request.getRequestDispatcher("WEB-INF/jsp/detailVente.jsp").forward(request, response);
		}else {

			
			
			
			if (enchereManager.selectByIdArticleIdUtilisateur(articleVendu.getNo_article(),utilisateur.getNo_utilisateur()) == null) {
				enchereManager.InsererEnchere(enchere);
				//article.getCategorie().setLibelle(request.getParameter("libelle"));
				articleVendu = am.selectByIDTop1(articleVendu);
				request.setAttribute("article", articleVendu);
				request.getRequestDispatcher("WEB-INF/jsp/detailVente.jsp").forward(request, response);
			
			} else {
				enchereManager.UpdateEnchere(enchere);			
				//article.getCategorie().setLibelle(request.getParameter("libelle"));
				articleVendu = am.selectByIDTop1(articleVendu);
				request.setAttribute("article", articleVendu);
				request.getRequestDispatcher("WEB-INF/jsp/detailVente.jsp").forward(request, response);
			}
		}

		
		

		
		
	}

}
