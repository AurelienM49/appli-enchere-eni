package fr.eni.AppliEnchereEni.dal.ArticleDAO;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT_Article ="INSERT INTO ARTICLES_VENDUS (nom_article ,description ,date_debut_encheres ,date_fin_encheres ,prix_initial ,no_utilisateur ,no_categorie)"
			+ "VALUES (?,?,?,?,?,?,?);";
	private static final String SELECT_BY_ID="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_vendus WHERE no_article = ?;";
	private static final String SELECT_ALL="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_vendus;";
	private static final String SELECT_BY_CATEGORIE="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_vendus WHERE no_categorie = ?;";
	
	@Override
	public ArticleVendu insertArticle(ArticleVendu articleVendu) {
		
		Connection cnx = null;
		PreparedStatement pstmt=null;
		
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT_Article, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, articleVendu.getNom_article());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, Date.valueOf(articleVendu.getDate_debut_encheres()));
			pstmt.setDate(4, Date.valueOf(articleVendu.getDate_fin_encheres()));
			pstmt.setInt(5, articleVendu.getPrix_initial());
			pstmt.setInt(6, articleVendu.getUtilisateur().getNo_utilisateur());
			pstmt.setInt(7, articleVendu.getNo_categorie());
			int rowsNumber = pstmt.executeUpdate();
			
			
			//Recuperer la generated key
			if (rowsNumber == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if (rs.next()) {
			
					articleVendu.setNo_article(rs.getInt(1));
					
				}
	
		}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return articleVendu;
		
	}


	@Override
	public ArticleVendu SelectArticleVenduByID(ArticleVendu articleVendu) {
		Connection cnx = null;
		PreparedStatement pstmt= null;
		ResultSet rs =null;
		ArticleVendu article = null;
		Utilisateur user = null;
		Categorie categorie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, articleVendu.getNo_article());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleVendu();
				user = new Utilisateur();
				categorie = new Categorie();
				
			
				article.setNo_article(rs.getInt("no_article"));
			
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_debut_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
			
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				article.setUtilisateur(user);
				
				categorie.setNo_categorie(rs.getInt("no_categorie"));
				article.setCategorie(categorie);
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		
		return article;
	}


	@Override
	public List<ArticleVendu> selectAll() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		ArticleVendu article = null;
		Utilisateur user = null;
		Categorie categorie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()) {
				article = new ArticleVendu();
				user = new Utilisateur();
				categorie = new Categorie();
				
				article.setNo_article(rs.getInt("no_article"));
			
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_debut_encheres(rs.getDate("date_fin_encheres").toLocalDate());
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
			ConnectionProvider.closeConnection(cnx, stmt);
		}	
		return articles;
	}
	
	
	
	@Override
	public List<ArticleVendu> selectByCategorie(Categorie categorie) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		ArticleVendu article = null;
		Utilisateur user = null;
		Categorie c = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			
			pstmt.setInt(1, categorie.getNo_categorie());

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new ArticleVendu();
				user = new Utilisateur();
				c = new Categorie();
				
				article.setNo_article(rs.getInt("no_article"));
				
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDate_debut_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));
				
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				article.setUtilisateur(user);
				
				c.setNo_categorie(rs.getInt("no_categorie"));
				article.setCategorie(c);
				
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
