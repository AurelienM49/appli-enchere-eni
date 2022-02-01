package fr.eni.AppliEnchereEni.dal.UtilisateurDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO [dbo].[UTILISATEURS] ([pseudo],[nom],[prenom],[email],[telephone],[rue],[code_postal],[ville],[mot_de_passe],[credit])"
		   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_BY_LOGIN = "SELECT [dbo].[UTILISATEURS] ([pseudo],[nom],[prenom],[email],[telephone],[rue],[code_postal],[ville],[mot_de_passe],[credit] "
			+ "FROM [dbo].[UTILISATEURS] WHERE email = ? or pseudo = ? and mot_de_passe = ?;";

	
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
	public boolean selectByLogin(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_LOGIN);
			
			pstmt.setString(1,utilisateur.getEmail() );
			pstmt.setString(2, utilisateur.getPseudo());
			pstmt.setString(3, utilisateur.getMot_de_passe());
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

}
