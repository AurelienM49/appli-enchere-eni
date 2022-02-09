package fr.eni.AppliEnchereEni.dal.EnchereDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.bo.Enchere;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private final static String SELECT_MESANNONCES = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";
	private final static String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)\r\n"
			+ "VALUES (?, ?, ?, ?);";
	private final static String UPDATE_ENCHERE = "UPDATE ENCHERES\r\n"
			+ "SET date_enchere = ?, montant_enchere = ?\r\n"
			+ "WHERE no_utilisateur = ? and no_article= ?"; 
	
	

	@Override
	public List<ArticleVendu> selectMesAnnonces(Utilisateur utilisateur) {
		
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		ArticleVendu article = null;
		Utilisateur user = null;
		Categorie categorie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_MESANNONCES);
			
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

	@Override
	public Enchere insertEnchere(Enchere enchere) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT_ENCHERE);
			pstmt.setInt(1, enchere.getUtilisateur().getNo_utilisateur());
			pstmt.setInt(2, enchere.getArticle().getNo_article());
			pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDate_enchere()));
			pstmt.setInt(4, enchere.getMontant_enchere());
			
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		return enchere;
	}

	@Override
	public Enchere UpdateEnchere(Enchere enchere) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
			pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDate_enchere()));
			pstmt.setInt(2, enchere.getMontant_enchere());
			pstmt.setInt(3, enchere.getUtilisateur().getNo_utilisateur());
			pstmt.setInt(4, enchere.getArticle().getNo_article());

			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		return enchere;
	}

	@Override
	public void updateIdUser(int idUser) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
	}
	
}
