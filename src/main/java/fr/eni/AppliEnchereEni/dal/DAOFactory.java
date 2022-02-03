package fr.eni.AppliEnchereEni.dal;

import fr.eni.AppliEnchereEni.dal.ArticleDAO.ArticleDAO;
import fr.eni.AppliEnchereEni.dal.ArticleDAO.ArticleDAOJdbcImpl;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	
	public static UtilisateurDAO createUtilisateurDAOJdbcImpl() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		
		return utilisateurDAO;
	}
	
	public static ArticleDAO createArticleDAOJbbcImpl() {
		ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
		
		return articleDAO;
	}
	
}
