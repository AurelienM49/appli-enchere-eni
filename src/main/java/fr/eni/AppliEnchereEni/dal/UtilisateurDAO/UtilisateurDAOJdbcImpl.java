package fr.eni.AppliEnchereEni.dal.UtilisateurDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit)"
		   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_BY_PSEUDO ="SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit "
			+ "FROM Utilisateurs WHERE pseudo = ?";
	private static final String SELECT_BY_EMAIL ="SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit "
			+ "FROM Utilisateurs WHERE email = ?";
	private static final String SELECT_BY_LOGIN = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit FROM Utilisateurs WHERE email = ? or pseudo = ? and mot_de_passe = ?;";
	private static final String UPDATE_UTILISATEUR ="UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE pseudo=?;";
	
	

	
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		try {
			 cnx = ConnectionProvider.getConnection();
			 pstmt = cnx.prepareStatement(INSERT_UTILISATEUR);
			 pstmt.setString(1, utilisateur.getPseudo());
			 pstmt.setString(2, utilisateur.getNom());
			 pstmt.setString(3, utilisateur.getPrenom());
			 pstmt.setString(4, utilisateur.getEmail());
			 pstmt.setString(5, utilisateur.getTelephone());
			 pstmt.setString(6, utilisateur.getRue());
			 pstmt.setString(7, utilisateur.getCode_postal());
			 pstmt.setString(8, utilisateur.getVille());
			 pstmt.setString(9, utilisateur.getMot_de_passe());
			 pstmt.setInt(10, 100);
			 pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
	}

	@Override
	public Utilisateur selectByLogin(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		Utilisateur user = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_LOGIN);
			
			pstmt.setString(1,utilisateur.getEmail() );
			pstmt.setString(2, utilisateur.getPseudo());
			pstmt.setString(3, utilisateur.getMot_de_passe());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new Utilisateur();
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCode_postal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
			}
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return user;
	}
	

	@Override
	public boolean selectByPseudo(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			
			pstmt.setString(1, utilisateur.getPseudo());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return false;
	}

	@Override
	public boolean selectByEmail(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			
			pstmt.setString(1, utilisateur.getEmail());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return false;
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCode_postal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMot_de_passe());
			pstmt.setInt(10, utilisateur.getNo_utilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}
	

	

}
