package fr.eni.AppliEnchereEni.dal.EnchereDAO;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Enchere;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface EnchereDAO {

	public List<ArticleVendu> selectMesAnnonces(Utilisateur utilisateur);
	
	public List<ArticleVendu> selectEnchereEnCours(Utilisateur utilisateur);
	
	public Enchere insertEnchere(Enchere enchere);
	
	public Enchere UpdateEnchere(Enchere enchere);
	
	public Enchere selectById (int idUser, int idArticle);
	
}
