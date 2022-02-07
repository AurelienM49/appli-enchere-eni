package fr.eni.AppliEnchereEni.dal.EnchereDAO;

import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;

public interface EnchereDAO {

	public List<ArticleVendu> selectMesArticles();
}
