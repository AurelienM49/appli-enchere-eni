package fr.eni.AppliEnchereEni.dal;

import fr.eni.AppliEnchereEni.dal.ArticleDAO.ArticleDAO;
import fr.eni.AppliEnchereEni.dal.ArticleDAO.ArticleDAOJdbcImpl;
import fr.eni.AppliEnchereEni.dal.CategorieDAO.CategorieDAO;
import fr.eni.AppliEnchereEni.dal.CategorieDAO.CategorieDAOJdcImpl;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAOJdbcImpl;
import fr.eni.AppliEnchereEni.dal.retraitDAO.RetraitDAO;
import fr.eni.AppliEnchereEni.dal.retraitDAO.RetraitDAOJdbcImpl;

public class DAOFactory {
	
	
	public static UtilisateurDAO createUtilisateurDAOJdbcImpl() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		
		return utilisateurDAO;
	}
	
	public static ArticleDAO createArticleDAOJbbcImpl() {
		ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
		
		return articleDAO;
	}
	
	public static RetraitDAO createRetraitDaoJdbcImpl() {
		RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
		
		return retraitDAO;
	}

	public static CategorieDAO createCategorietDaoJdbcImpl() {
		CategorieDAO categorieDAO = new CategorieDAOJdcImpl();
		
		return categorieDAO;
	}
	
}
