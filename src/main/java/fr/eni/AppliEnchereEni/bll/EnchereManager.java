package fr.eni.AppliEnchereEni.bll;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Enchere;
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
	
	public List<ArticleVendu> listerMesAnnonces (Utilisateur utilisateur) {
		
		EnchereDAO enchereDAO = DAOFactory.createEnchereDaoJdbcImpl();
		
		return enchereDAO.selectMesAnnonces(utilisateur);
	}
	
	public List<ArticleVendu> listerEnchereEnCours (Utilisateur utilisateur){
		
		EnchereDAO enchereDAO = DAOFactory.createEnchereDaoJdbcImpl();
		
		return enchereDAO.selectEnchereEnCours(utilisateur);
	}
	
	public Enchere InsererEnchere (Enchere enchere) {
		
		EnchereDAO enchereDAO = DAOFactory.createEnchereDaoJdbcImpl();
		
		return enchereDAO.insertEnchere(enchere);
	}
	
	public Enchere UpdateEnchere (Enchere enchere) {
		
		EnchereDAO enchereDAO = DAOFactory.createEnchereDaoJdbcImpl();
		
		return enchereDAO.UpdateEnchere(enchere);
	}
	
	public Enchere selectByID(int idArticle) {
		EnchereDAO enchereDAO = DAOFactory.createEnchereDaoJdbcImpl();
		return enchereDAO.selectById(idArticle, false);
	}
}
