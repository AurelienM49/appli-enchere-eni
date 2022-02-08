package fr.eni.AppliEnchereEni.dal.ArticleDAO;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface ArticleDAO {

	//inserer un nouvel article dans BDD
	public ArticleVendu insertArticle(ArticleVendu articleVendu);
	
	//Selectionner un article par son id
	public ArticleVendu SelectArticleVenduByID(ArticleVendu articleVendu);
	
	//Lister tous les articles
	public List<ArticleVendu> selectAll(); 
	
	//selectionner une liste d'articles par categeorie
	public List<ArticleVendu> selectByCategorie(Categorie categorie);
	
	//supprimer un article
	public void delete(ArticleVendu articlevendu);
	
//	//selectionne une liste d'articles par nom
//	public List<ArticleVendu> selectByNom();
	
	public List<ArticleVendu> filtreConnected (Utilisateur utilisateur, String rechercheMotArt, String categorie, String choixRadio,
			String checkBoxFiltre1, String checkBoxFiltre2, String checkBoxFiltre3, String checkBoxFiltre4,
			String checkBoxFiltre5, String checkBoxFiltre6);
	
	public List<ArticleVendu> filtreDeconnected (String rechercheMotArt, String categorie);
	
	public List<ArticleVendu> selectTop10();
}
