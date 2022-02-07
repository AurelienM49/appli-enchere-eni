package fr.eni.AppliEnchereEni.dal.CategorieDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class CategorieDAOJdcImpl implements CategorieDAO{
	
	
	private static final String SELECT_BY_LIBELLE ="SELECT no_categorie, libelle FROM Categories WHERE libelle = ?";
	private static final String SELECT_BY_ID = "SELECT no_categorie, libelle FROM Categories WHERE libelle = ?";
	private static final String INSERT_CAT ="INSERT INTO Categories (libelle) VALUES (?);";
	
	/**
	 * Methode permettant de rechercher une categorie en fonction de son libelle
	 * @param categorie
	 * @return categorie
	 */
	@Override
	public Categorie selectByLibelle(Categorie categorie) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Categorie cat = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			pstmt.setString(1, categorie.getLibelle());

			rs = pstmt.executeQuery();
			if(rs.next()) {
				cat = new Categorie (rs.getInt("no_categorie"), rs.getString("libelle"));			
			}
			
			return cat;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		
		
		return cat;
	}
	
	/**
	 * Methode permettant de rechercher une categorie en fonction de son ID
	 * @param categorie
	 * @return categorie
	 */
	@Override
	public Categorie selectById(Categorie categorie) {
		Connection cnx = null;
		PreparedStatement pstmt= null;
		ResultSet rs =null;
				
		try {
			//ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			// cr�ation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			
			//recuperer les param�tres envoy�s par la serlvet
			pstmt.setInt(1, categorie.getNo_categorie());
			
			//executer la requ�te cot� BDD
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				categorie.setLibelle(rs.getString("libelle"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}	
		
		return categorie;
	}
	
	
	/**
	 * Methode permettant d'is�rer une categorie en BDD
	 * @param categorie
	 * @return categorie
	 */
	@Override
	public void insertCategorie(Categorie categorie) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		
		try {
			 // ouverture de la connexion
			 cnx = ConnectionProvider.getConnection();
			
			//creation d'un prepareStatement (requete avec arguments)
			 pstmt = cnx.prepareStatement(INSERT_CAT);
			 
			//recup�re les param�tres envoy�s par la servlet
			 pstmt.setString(1, categorie.getLibelle());
			 
			//exectute la requete cot� BDD
			 pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
	}
		
	}

	
	
	

