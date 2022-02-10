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
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.ArticleDAO.ArticleDAO;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private final static String SELECT_MES_ANNONCES = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";
	private final static String SELECT_ENCHERE_EN_COURS = "SELECT * FROM ARTICLES_VENDUS \r\n "
			+ "WHERE no_utilisateur <> ? AND GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres;";
	private final static String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)\r\n"
			+ "VALUES (?, ?, ?, ?);";
	private final static String UPDATE_ENCHERE = "UPDATE ENCHERES\r\n" + "SET date_enchere = ?, montant_enchere = ?\r\n"
			+ "WHERE no_utilisateur = ? and no_article= ?;";
	private final static String SELECT_BY_IDARTICLE = "SELECT TOP 1 no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES "
			+ "WHERE no_article = ? ORDER BY montant_enchere DESC;";
	private final static String SELECT_BY_IDARTICLE_ID_UTILISATEUR = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES "
			+ "WHERE no_article = ? AND no_utilisateur = ?;";
	
	
	
	
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
			pstmt = cnx.prepareStatement(SELECT_MES_ANNONCES);

			pstmt.setInt(1, utilisateur.getNo_utilisateur());
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	public List<ArticleVendu> selectEnchereEnCours(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		ArticleVendu article = null;
		Utilisateur user = null;
		Categorie categorie = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ENCHERE_EN_COURS);

			pstmt.setInt(1, utilisateur.getNo_utilisateur());
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

			pstmt.executeUpdate();

		} catch (SQLException e) {
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
		ResultSet rs= null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
			pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDate_enchere()));
			pstmt.setInt(2, enchere.getMontant_enchere());
			pstmt.setInt(3, enchere.getUtilisateur().getNo_utilisateur());
			pstmt.setInt(4, enchere.getArticle().getNo_article());
			pstmt.executeUpdate();
			
					
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return enchere;
	}

	public Enchere selectById(int idArticle, boolean withArticle) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_IDARTICLE);

			pstmt.setInt(1, idArticle);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				Enchere enchere = new Enchere();
				Utilisateur user = new Utilisateur();
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				enchere.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				enchere.setUtilisateur(user);

//				UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
//				enchere.setUtilisateur(user.selectByID(rs.getInt("no_utilisateur")));

				if (withArticle) {
					ArticleDAO article = DAOFactory.createArticleDAOJbbcImpl();
					ArticleVendu av = new ArticleVendu();
					av.setNo_article(rs.getInt("no_article"));
					enchere.setArticle(article.selectArticleTop1(av, false));
				}

				return enchere;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Enchere selectByidArticleIdUtilisateur(int idArticle, int idUtilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_IDARTICLE_ID_UTILISATEUR);

			pstmt.setInt(1, idArticle);
			pstmt.setInt(2, idUtilisateur);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				Enchere enchere = new Enchere();

				enchere.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				
				return enchere;
			}
		} catch (Exception e) {
			
		}finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return null;
	}
}
