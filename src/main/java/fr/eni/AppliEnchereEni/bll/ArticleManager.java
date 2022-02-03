package fr.eni.AppliEnchereEni.bll;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.ArticleDAO.ArticleDAO;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;

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
	
	public void ajouterArticle(ArticleVendu  articleVendu) {

		ArticleDAO article = DAOFactory.createArticleDAOJbbcImpl();

		article.insertArticle(articleVendu);
	}
}
