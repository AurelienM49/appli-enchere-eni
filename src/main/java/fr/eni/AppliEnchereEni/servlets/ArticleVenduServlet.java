package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import fr.eni.AppliEnchereEni.bll.ArticleManager;
import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class ArticleVenduServlet
 */
@WebServlet("/ArticleVendu")
public class ArticleVenduServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleVenduServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/ArticleVendu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pour creer un article, nous avons besoin d'un objet utilisateur, d'un objet
		//Categorie et d'un objet retrait
		
		//Recuperation des elements de la session pour les inserer dans articles.
		

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
				

		
		//Recuperer les attributs du formulaire pour poster une annonce
		String nomArticle = request.getParameter("nomArticle");
		String descriptionArticle = request.getParameter("descriptionArticle");
		String categorie = request.getParameter("categorie");
		int prix = Integer.valueOf(request.getParameter("prix"));
		
		//Definition d'un format pour les dates de début et de fin de l'enchère
		//de l'article mis en vente
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		//Recuperer les dates de debut et fin d'enchere en fonction du date format
		LocalDate dateDebutEnchere =  LocalDate.parse(request.getParameter("dateDebutEnchere"), dateFormat);
		LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("dateFinEnchere"), dateFormat);
		
		//Creation de l'objet Categorie à partir des attributs du formualaire
		
		Categorie categorieArticleVendu = new Categorie();
		categorieArticleVendu.setLibelle(categorie);
		
		//Creation de l'objet Articlevendu à partir des attributs du formulaire
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNom_article(nomArticle);
		articleVendu.setDescription(descriptionArticle);
//		articleVendu.setCategorie(categorieArticleVendu);
		articleVendu.setPrix_initial(prix);
		articleVendu.setDate_debut_encheres(dateDebutEnchere);
		articleVendu.setDate_fin_encheres(dateFinEnchere);
		articleVendu.setUtilisateur(utilisateur);
		articleVendu.setNo_categorie(1);
		
		ArticleManager articleManager = ArticleManager.getInstance();
		articleManager.ajouterArticle(articleVendu);
	}

}
