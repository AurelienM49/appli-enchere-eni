package fr.eni.AppliEnchereEni.dal.CategorieDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class CategorieDAOJdcImpl implements CategorieDAO{
	
	
	private static final String SELECT_BY_LIBELLE ="SELECT no_categorie, libelle FROM Categories WHERE libelle = ?";
	private static final String SELECT_BY_ID = "SELECT no_categorie, libelle FROM Categories WHERE libelle = ?";
	private static final String INSERT_CAT ="INSERT INTO Categories (libelle) VALUES (?);";
	
	
	
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
	@Override
	public Categorie selectById(Categorie categorie) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insertCategorie(Categorie categorie) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
