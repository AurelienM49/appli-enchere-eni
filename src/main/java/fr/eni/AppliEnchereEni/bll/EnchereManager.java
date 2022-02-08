package fr.eni.AppliEnchereEni.bll;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.EnchereDAO.EnchereDAO;

public class EnchereManager {

	//Enchere Manager Singleton
	
	private static EnchereManager instance;
	
	//Constructor
	public EnchereManager () {
	}
	
	//Methods 
	
	public static EnchereManager getInstance () {
		
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	//Fin du Pattern Singleton
	
	public List<ArticleVendu> listerMesEncheres (Utilisateur utilisateur) {
		
		EnchereDAO enchereDAO = DAOFactory.createEnchereDaoJdbcImpl();
		
		return enchereDAO.selectMesArticles(utilisateur);		
	}
	
}
