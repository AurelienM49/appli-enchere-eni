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
	private static final String DELETE_ARTICLE="DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";
	
	
	
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
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
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


	@Override
	public void delete(ArticleVendu articleVendu) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			
			pstmt.setInt(1, articleVendu.getNo_article());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		
	}


	@Override
	public List<ArticleVendu> filtre(Utilisateur utilisateur, String rechercheMotArt, String categorie, String choixRadio,
			String checkBoxFiltre1, String checkBoxFiltre2, String checkBoxFiltre3, String checkBoxFiltre4,
			String checkBoxFiltre5, String checkBoxFiltre6) {
	
		//variable que l'on remplace par "TOP 1" si l'utilisateur coche "Mes enchères remportées".
		String top1 =" ";
		
		
		//requête principale 
		StringBuilder requêteSQL = new StringBuilder();		
		requêteSQL.append("SELECT"+top1+"av.nom_article, prix_initial, date_debut_encheres, date_fin_encheres, pseudo\r\n"
				+ "FROM ARTICLES_VENDUS AS av WHERE 1 = 1");

		//choix radio 
		String radioMesVentes = "INNER JOIN UTILISATEURS AS u ON u.no_utilisateur = ?";
		String radioAchats = "INNER JOIN UTILISATEURS as u ON u.no_utilisateur = av.no_utilisateur\r\n"
				+ "LEFT JOIN ENCHERES e ON av.no_article = e.no_article ";
		
		
		//filtre mot clé et catégorie
		String rechercheMotArtSQL = " AND av.nom_article LIKE ?";
		String categorieSQL = " INNER JOIN CATEGORIES AS c ON c.libelle = ?";
		
		
		//choix checkbox
		String venteEnCoursSQL = " OR GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres";
		String ventesNonDebuteesSQL = " OR date_debut_encheres > GETDATE()";
		String venteTermineesSQL = " OR GETDATE() > date_fin_encheres";
		
		String enchereOuverte = " OR GETDATE() > date_debut_encheres";
		String mesEncheresEnCours= " OR e.no_utilisateur = ?";
		String mesEncheresRemportees =" OR date_fin_encheres < GETDATE()\r\n"
				+ "	ORDER BY montant_enchere DESC";
		
		//on vérifie si l'utilisateur à choisi une categorie pour filtrer
		if (categorie!=null) {
			requêteSQL.append(categorieSQL);
		}

		//on vérifie si l'utilisateur à choisit "Mes ventes"
		if(choixRadio.equals("Mes ventes")) {
			requêteSQL.append(radioMesVentes);
			
//TODO Finir les conditions de checkbox sur ArticleDAOJdcImpl
			
			
			
			
			//condition si l'utilisateur à coché le checkbox1 on vérifie les deux autres
			if(checkBoxFiltre1!=null) {	
				requêteSQL.append(venteEnCoursSQL);	
				if(checkBoxFiltre2!=null) {
					requêteSQL.append(ventesNonDebuteesSQL);	
				}
				if(checkBoxFiltre3!=null) {
					requêteSQL.append(venteTermineesSQL);	
				}	
			}
			

			
			
		}else {
			requêteSQL.append(radioAchats);
		}
		

		if(!rechercheMotArt.isEmpty()) {
			requêteSQL.append(rechercheMotArtSQL);
		}
		
		
		
		
		
		
		
		
		return null;
	}
	
	
}
