package fr.eni.AppliEnchereEni.dal.retraitDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.AppliEnchereEni.bo.Retrait;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class RetraitDAOJdbcImpl implements RetraitDAO {
 
	private static final String INSERT_Retrait = "INSERT INTO RETRAITS(no_article,rue,code_postal,ville)VALUES (?,?,?,?);";

	@Override
	public Retrait insertRetrait(Retrait retrait) {
		
		Connection cnx = null;
		PreparedStatement pstmt=null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT_Retrait);
			pstmt.setInt(1, retrait.getArticleVendu().getNo_article());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCode_postal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		return retrait;
	}
	
}
