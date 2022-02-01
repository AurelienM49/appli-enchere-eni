package fr.eni.AppliEnchereEni.dal;

import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static UtilisateurDAO createUtilisateurDAOJdbcImpl() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		
		return utilisateurDAO;
	}
	
}
