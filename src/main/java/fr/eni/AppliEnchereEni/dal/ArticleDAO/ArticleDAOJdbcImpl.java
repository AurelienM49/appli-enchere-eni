package fr.eni.AppliEnchereEni.dal.ArticleDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT_Article ="INSERT INTO [dbo].[ARTICLES_VENDUS] (nom_article ,description ,date_debut_encheres ,date_fin_encheres ,prix_initial ,prix_vente ,no_utilisateur ,no_categorie) ."
			+ "VALUES (?,?,?,?,?,?,? ,?);";

	@Override
	public void insertArticle(ArticleVendu articleVendu) {
		
		Connection cnx = null;
		PreparedStatement pstmt=null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT_Article);
			pstmt.setString(1, articleVendu.getNom_article());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, Date.valueOf(articleVendu.getDate_debut_encheres()));
			pstmt.setDate(4, Date.valueOf(articleVendu.getDate_fin_encheres()));
			pstmt.setInt(5, articleVendu.getPrix_initial());
			pstmt.setInt(6, articleVendu.getPrix_vente());
			pstmt.setInt(7, articleVendu.getUtilisateur().getNo_utilisateur());
			pstmt.setInt(8, articleVendu.getCategorie().getNo_categorie());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
	}
	
	
}