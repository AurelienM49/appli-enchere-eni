package fr.eni.AppliEnchereEni.dal.retraitDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.AppliEnchereEni.bo.Retrait;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class RetraitDAOJdbcImpl implements RetraitDAO {
 
	private static final String INSERT_Retrait = "INSERT INTO RETRAITS(no_article,rue,code_postal,ville)VALUES (?,?,?,?);";
	private static final String SELECT_BY_NO_ARTICLE="SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article=?;";
	
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

	@Override
	public Retrait selectByNoArticle(int noArticle) {

		Connection cnx = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
			pstmt.setInt(1, noArticle);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Retrait retrait = new Retrait();
				
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				
				
				return retrait;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
		return null;
	}
	
}
