package fr.eni.AppliEnchereEni.dal.EnchereDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private final static String SELECT_MESARTICLES = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";

	@Override
	public List<ArticleVendu> selectMesArticles(Utilisateur utilisateur) {
		
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		ArticleVendu article = null;
		Utilisateur user = null;
		Categorie categorie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_MESARTICLES);
			
			pstmt.setInt(1, utilisateur.getNo_utilisateur());
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				article = new ArticleVendu();
				user = new Utilisateur();
				categorie = new Categorie();
				
				article.setNo_article(rs.getInt("no_article"));
			
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				article.setUtilisateur(user);
				
				categorie.setNo_categorie(rs.getInt("no_categorie"));
				article.setCategorie(categorie);
				
				articles.add(article);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		return articles;
	}
	
}
