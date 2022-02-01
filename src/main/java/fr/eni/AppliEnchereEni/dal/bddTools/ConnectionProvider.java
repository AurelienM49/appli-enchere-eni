package fr.eni.AppliEnchereEni.dal.bddTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	
	private static DataSource dataSource;

	static {// init		
		// chargement de context
		Context context;
		try {
			context = new InitialContext();
			// creation de pool
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/cnx_pool");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	
	public static Connection getConnection() throws SQLException {
		// obtenir la cnx		
		return dataSource.getConnection();
	} 
	
	public static void closeConnection (Connection cnx, PreparedStatement pstmt) {
		try {
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection (Connection cnx, Statement stmt) {
		try {
			stmt.close();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
