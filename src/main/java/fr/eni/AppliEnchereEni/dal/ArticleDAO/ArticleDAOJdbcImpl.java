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
import fr.eni.AppliEnchereEni.dal.CategorieDAO.CategorieDAO;
import fr.eni.AppliEnchereEni.dal.CategorieDAO.CategorieDAOJdcImpl;
import fr.eni.AppliEnchereEni.dal.EnchereDAO.EnchereDAO;
import fr.eni.AppliEnchereEni.dal.EnchereDAO.EnchereDAOJdbcImpl;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAOJdbcImpl;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;
import fr.eni.AppliEnchereEni.dal.retraitDAO.RetraitDAO;
import fr.eni.AppliEnchereEni.dal.retraitDAO.RetraitDAOJdbcImpl;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT_Article = "INSERT INTO ARTICLES_VENDUS (nom_article ,description ,date_debut_encheres ,date_fin_encheres ,prix_initial ,no_utilisateur ,no_categorie)"
			+ "VALUES (?,?,?,?,?,?,?);";
	private static final String SELECT_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = ?;";
	private static final String SELECT_ALL = "select nom_article, u.pseudo, av.prix_initial, av.date_fin_encheres from ARTICLES_VENDUS AS av inner join UTILISATEURS AS u ON av.no_utilisateur = u.no_utilisateur;";
	private static final String SELECT_TOP10 = "select TOP 10 no_article, nom_article, u.pseudo, av.prix_initial, av.date_fin_encheres from ARTICLES_VENDUS AS av inner join UTILISATEURS AS u ON av.no_utilisateur = u.no_utilisateur ORDER BY av.date_fin_encheres ASC;";
	private static final String SELECT_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_vendus WHERE no_categorie = ?;";
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";
//	private static final String SELECT_BY_ID_TOP1 = "SELECT top 1 av.nom_article, description, av.no_categorie, e.montant_enchere, av.prix_initial, av.date_fin_encheres,av.no_utilisateur\r\n"
//			+ "FROM ARTICLES_VENDUS AS av\r\n" + "LEFT JOIN ENCHERES AS e ON e.no_article = av.no_article\r\n"
//			+ "WHERE av.no_article = ?;";

	public ArticleVendu selectArticleTop1(ArticleVendu articleVendu, boolean withEncheres) {

		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ID);

			pstmt.setInt(1, articleVendu.getNo_article());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ArticleVendu article = new ArticleVendu();

				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				// article.getEnchere().setMontant_enchere(rs.getInt("montant_enchere"));
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				// article.getRetrait().setVille(rs.getString("ville"));

				UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
				article.setUtilisateur(utilisateurDAO.selectByID(rs.getInt("no_utilisateur")));

				CategorieDAO categorieDAO = new CategorieDAOJdcImpl();
				article.setCategorie(categorieDAO.selectByID(rs.getInt("no_categorie")));
				
				RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
				article.setRetrait(retraitDAO.selectByNoArticle(rs.getInt("no_article")));
				
				if (withEncheres) {
					EnchereDAO enchereDAO = new EnchereDAOJdbcImpl();
					article.setEnchere(enchereDAO.selectById(rs.getInt("no_article"), false));
				}
	

				return article;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return null;

	}

	@Override
	public ArticleVendu insertArticle(ArticleVendu articleVendu) {

		Connection cnx = null;
		PreparedStatement pstmt = null;

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

			// Recuperer la generated key
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVendu article = null;
		Utilisateur user = null;
		Categorie categorie = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, articleVendu.getNo_article());

			rs = pstmt.executeQuery();

			if (rs.next()) {
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
			// fermeture de la connexion
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

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {
				article = new ArticleVendu();
				user = new Utilisateur();

				article.setNom_article(rs.getString("nom_article"));
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));

				user.setPseudo(rs.getString("pseudo"));
				article.setUtilisateur(user);

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
	public List<ArticleVendu> selectTop10() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		ArticleVendu article = null;
		Utilisateur user = null;

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_TOP10);

			while (rs.next()) {
				article = new ArticleVendu();
				user = new Utilisateur();

				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setPrix_initial(rs.getInt("prix_initial"));

				user.setPseudo(rs.getString("pseudo"));
				article.setUtilisateur(user);

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

			while (rs.next()) {
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
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}

	@Override
	public List<ArticleVendu> filtreConnected(Utilisateur utilisateur, String rechercheMotArt, String categorie,
			String choixRadio, String checkBoxFiltre1, String checkBoxFiltre2, String checkBoxFiltre3,
			String checkBoxFiltre4, String checkBoxFiltre5, String checkBoxFiltre6) {

		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleVendu> listeArticles = new ArrayList<>();
		ArticleVendu articleVendu = null;
		Utilisateur user = null;
		try {

			// requête principale
			StringBuilder requêteSQL = new StringBuilder();
			requêteSQL.append("SELECT "
					+ " av.no_article, av.nom_article, prix_initial, date_debut_encheres,  date_fin_encheres, date_enchere, montant_enchere, pseudo, c.libelle\r\n"
					+ "	FROM ARTICLES_VENDUS AS av\r\n"
					+ "	INNER JOIN UTILISATEURS as u ON u.no_utilisateur = av.no_utilisateur\r\n"
					+ "	INNER JOIN CATEGORIES AS c ON c.no_categorie = av.no_categorie 	\r\n"
					+ "	LEFT JOIN ENCHERES e ON av.no_article = e.no_article \r\n" + "	WHERE 1 = 1");

			// choix catégorie
			String choixCategorie = " AND c.libelle = :libele_categorie";

			// filtre mot clé et catégorie
			String rechercheMotArtSQL = " AND av.nom_article LIKE :rechercheMotArt";

			// choix checkbox vente
			String venteDebutSQL = " AND (1 = 1 ";
			String venteEnCoursSQL = " AND ((GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres) AND (u.no_utilisateur = :no_utilisateur)) ";
			String ventesNonDebuteesSQL = " OR date_debut_encheres > GETDATE() ";
			String venteTermineesSQL = " OR GETDATE() > date_fin_encheres ";
			String venteFinSQL = ")";

			// choix checkbox achats
			String achatsDebutSQL = " AND (1 = 1 ";
			String enchereOuverte = " OR GETDATE() > date_debut_encheres";
			String mesEncheresEnCours = " OR (u.no_utilisateur = :no_utilisateur AND (GETDATE() < date_fin_encheres) AND (date_enchere is not null))";
			String mesEncheresRemportees = " OR (e.montant_enchere = (SELECT MAX(e2.montant_enchere) FROM ENCHERES e2 WHERE e2.no_article = av.no_article AND e2.no_utilisateur = :no_utilisateur AND date_fin_encheres < GETDATE())))";
			String finAchatsSQL = ")";

			// on vérifie si l'utilisateur à choisi une categorie pour filtrer
			if (!categorie.equals("all")) {
				requêteSQL.append(choixCategorie);
			}

			// on vérifie si l'utilisateur à choisit "Mes ventes"
			if (choixRadio.equals("Mes ventes")) {
				requêteSQL.append(venteDebutSQL);

				// filtre de recherche par mot clé
				if (!rechercheMotArt.equals("all")) {
					requêteSQL.append(rechercheMotArtSQL);
				}

				// MES VENTES
				// si il n'y qu'un seul qui est coché

				if (checkBoxFiltre4 != null && checkBoxFiltre5 == null && checkBoxFiltre6 == null) {
					requêteSQL.append(venteEnCoursSQL);
				}

				if (checkBoxFiltre5 != null && checkBoxFiltre4 == null && checkBoxFiltre6 == null) {
					requêteSQL.append(ventesNonDebuteesSQL);
				}

				if (checkBoxFiltre6 != null && checkBoxFiltre4 == null && checkBoxFiltre5 == null) {
					requêteSQL.append(venteTermineesSQL);
				}

				// si y'en a deux qui sont cochés
				if (checkBoxFiltre4 != null && checkBoxFiltre5 != null && checkBoxFiltre6 == null
						|| checkBoxFiltre4 != null && checkBoxFiltre6 != null && checkBoxFiltre5 == null
						|| checkBoxFiltre5 != null && checkBoxFiltre6 != null && checkBoxFiltre4 == null) {
					if (checkBoxFiltre4 != null && checkBoxFiltre5 != null && checkBoxFiltre6 == null) {
						requêteSQL.append(venteEnCoursSQL);
						requêteSQL.append(ventesNonDebuteesSQL);
					} else if (checkBoxFiltre4 != null && checkBoxFiltre6 != null && checkBoxFiltre5 == null) {
						requêteSQL.append(venteEnCoursSQL);
						requêteSQL.append(venteTermineesSQL);
					} else if (checkBoxFiltre5 != null && checkBoxFiltre6 != null && checkBoxFiltre4 == null) {
						requêteSQL.append(ventesNonDebuteesSQL);
						requêteSQL.append(venteTermineesSQL);
					}
				}

				// si les trois sont cochés
				if (checkBoxFiltre4 != null && checkBoxFiltre5 != null && checkBoxFiltre6 != null) {
					requêteSQL.append(venteEnCoursSQL);
					requêteSQL.append(ventesNonDebuteesSQL);
					requêteSQL.append(venteTermineesSQL);
				}

				requêteSQL.append(venteFinSQL);

				// ACHATS
			} else {
				requêteSQL.append(achatsDebutSQL);

				// filtre de recherche par mot clé
				if (!rechercheMotArt.isEmpty()) {
					requêteSQL.append(rechercheMotArtSQL);
				}

				// si il n'y qu'un seul qui est coché
				if (checkBoxFiltre1 != null && checkBoxFiltre2 == null && checkBoxFiltre3 == null) {
					requêteSQL.append(enchereOuverte);
				}

				if (checkBoxFiltre2 != null && checkBoxFiltre1 == null && checkBoxFiltre3 == null) {
					requêteSQL.append(mesEncheresEnCours);
				}

				if (checkBoxFiltre3 != null && checkBoxFiltre1 == null && checkBoxFiltre2 == null) {
					requêteSQL.append(mesEncheresRemportees);
					requêteSQL.append(finAchatsSQL);
				}

				// si y'en a deux qui sont cochés
				if (checkBoxFiltre1 != null && checkBoxFiltre2 != null && checkBoxFiltre3 == null
						|| checkBoxFiltre1 != null && checkBoxFiltre3 != null && checkBoxFiltre2 == null
						|| checkBoxFiltre2 != null && checkBoxFiltre3 != null && checkBoxFiltre1 == null) {
					if (checkBoxFiltre1 != null && checkBoxFiltre2 != null && checkBoxFiltre3 == null) {
						requêteSQL.append(enchereOuverte);
						requêteSQL.append(mesEncheresEnCours);
					} else if (checkBoxFiltre1 != null && checkBoxFiltre3 != null && checkBoxFiltre2 == null) {
						requêteSQL.append(enchereOuverte);
						requêteSQL.append(mesEncheresRemportees);
					} else if (checkBoxFiltre2 != null && checkBoxFiltre3 != null && checkBoxFiltre1 == null) {
						requêteSQL.append(mesEncheresEnCours);
						requêteSQL.append(mesEncheresRemportees);
						requêteSQL.append(finAchatsSQL);
					}
				}

				// si les trois sont cochés
				if (checkBoxFiltre1 != null && checkBoxFiltre2 != null && checkBoxFiltre3 != null) {
					requêteSQL.append(enchereOuverte);
					requêteSQL.append(mesEncheresEnCours);
					requêteSQL.append(mesEncheresRemportees);
					requêteSQL.append(finAchatsSQL);
				}

				if (checkBoxFiltre3 == null) {
					requêteSQL.append(finAchatsSQL);
				}
			}

			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(requêteSQL.toString().replaceAll(":libele_categorie", "'" + categorie + "'")
					.replaceAll(":no_utilisateur", String.valueOf(utilisateur.getNo_utilisateur()))
					.replaceAll(":rechercheMotArt", "'%" + rechercheMotArt + "%'"));

			System.out.println(requêteSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVendu = new ArticleVendu();
				user = new Utilisateur();

				articleVendu.setNo_article(rs.getInt("no_article"));
				articleVendu.setNom_article(rs.getString("nom_article"));
				articleVendu.setPrix_initial(rs.getInt("prix_initial"));
				articleVendu.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());

				user.setPseudo(rs.getString("pseudo"));
				articleVendu.setUtilisateur(user);

				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeArticles;
	}

	@Override
	public List<ArticleVendu> filtreDeconnected(String rechercheMotArt, String categorie) {

		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleVendu> listeArticles = new ArrayList<>();
		ArticleVendu articleVendu = null;
		Utilisateur user = null;
		try {

			// requête principale
			StringBuilder requêteSQL = new StringBuilder();
			requêteSQL.append("SELECT "
					+ " av.nom_article, prix_initial, date_debut_encheres,  date_fin_encheres, date_enchere, montant_enchere, pseudo, c.libelle\r\n"
					+ "	FROM ARTICLES_VENDUS AS av\r\n"
					+ "	INNER JOIN UTILISATEURS as u ON u.no_utilisateur = av.no_utilisateur\r\n"
					+ "	INNER JOIN CATEGORIES AS c ON c.no_categorie = av.no_categorie 	\r\n"
					+ "	LEFT JOIN ENCHERES e ON av.no_article = e.no_article \r\n" + "	WHERE 1 = 1");

			// choix catégorie
			String choixCategorie = " AND c.libelle = :libele_categorie";

			// filtre mot clé et catégorie
			String rechercheMotArtSQL = " AND av.nom_article LIKE :rechercheMotArt";

			// on vérifie si l'utilisateur à choisi une categorie pour filtrer
			if (!categorie.equals("all")) {
				requêteSQL.append(choixCategorie);
			}

			if (rechercheMotArt != null) {
				requêteSQL.append(rechercheMotArtSQL);
			}

			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(requêteSQL.toString().replaceAll(":libele_categorie", "'" + categorie + "'")
					.replaceAll(":rechercheMotArt", "'%" + rechercheMotArt + "%'"));

			System.out.println(requêteSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVendu = new ArticleVendu();
				user = new Utilisateur();

				articleVendu.setNom_article(rs.getString("nom_article"));
				articleVendu.setPrix_initial(rs.getInt("prix_initial"));
				articleVendu.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());

				user.setPseudo(rs.getString("pseudo"));
				articleVendu.setUtilisateur(user);

				System.out.println(articleVendu.toString());
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeArticles;
	}

}
