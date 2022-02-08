package fr.eni.AppliEnchereEni.dal.EnchereDAO;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface EnchereDAO {

	public List<ArticleVendu> selectMesArticles(Utilisateur utilisateur);
}
