package fr.eni.AppliEnchereEni.bll;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
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
	
	public List<ArticleVendu> filtreConnecte(Utilisateur utilisateur, String rechercheMotArt, String categorie, String choixRadio,
			String checkBoxFiltre1, String checkBoxFiltre2, String checkBoxFiltre3, String checkBoxFiltre4,
			String checkBoxFiltre5, String checkBoxFiltre6){
		
		ArticleDAO article = DAOFactory.createArticleDAOJbbcImpl();
		return article.filtreConnected(utilisateur, rechercheMotArt, categorie, choixRadio, checkBoxFiltre1, checkBoxFiltre2, checkBoxFiltre3, checkBoxFiltre4, checkBoxFiltre5, checkBoxFiltre6);	
	}
	
	
	public List<ArticleVendu> filtreDeconnecte(String rechercheMotArt, String categorie){
		
		ArticleDAO article = DAOFactory.createArticleDAOJbbcImpl();
		return article.filtreDeconnected(rechercheMotArt, categorie);	
	}
	
	public List<ArticleVendu> afficher10Articles(){
		ArticleDAO article = DAOFactory.createArticleDAOJbbcImpl();
		return article.selectTop10();
		
	}
	
}
