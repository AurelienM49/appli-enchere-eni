package fr.eni.AppliEnchereEni.dal.EnchereDAO;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Enchere;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface EnchereDAO {

	public List<ArticleVendu> selectMesAnnonces(Utilisateur utilisateur);
	
	public Enchere insertEnchere();
	
	public Enchere UpdateEnchere();
}
