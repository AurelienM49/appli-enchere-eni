package fr.eni.AppliEnchereEni.bll;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.ArticleDAO.ArticleDAO;

public class ArticleManager {

	// Utilisateur Manager Singleton
	
	private static ArticleManager instance;
	
	//Constructor
	private ArticleManager() {
	}
	
	//Methods
	
	public static ArticleManager getInstance() {

		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	//Fin du pattern Singleton
	
	public ArticleVendu ajouterArticle(ArticleVendu  articleVendu) {

		ArticleDAO article = DAOFactory.createArticleDAOJbbcImpl();

		return article.insertArticle(articleVendu);
	}
}
