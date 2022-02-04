package fr.eni.AppliEnchereEni.dal.ArticleDAO;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface ArticleDAO {

	//inserer un nouvel article dans BDD
	void insertArticle(ArticleVendu articleVendu);
	
	//Selectionner un article par son id
	public ArticleVendu SelectArticleVenduByID(ArticleVendu articleVendu);
	
	//Lister tous les articles
	public List<ArticleVendu> selectAll(); 
	
	//selectionner une liste d'articles par categeorie
	public List<ArticleVendu> selectByCategorie();
	
}
