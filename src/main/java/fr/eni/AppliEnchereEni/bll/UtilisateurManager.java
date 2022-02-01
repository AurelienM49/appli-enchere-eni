package fr.eni.AppliEnchereEni.bll;

import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;

public class UtilisateurManager {

	//Utilisateur Manager Singleton
	
	private static UtilisateurManager instance;
	
	//Constructor
	private UtilisateurManager () {
	}
	
	//Methods
	
	public static UtilisateurManager getInstance() {
		
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	//fin Singleton
	
	public void ajouterUtilisateur (Utilisateur utilisateur) {
	
	UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
	user.insertUtilisateur(utilisateur);
	}
	
	
	public boolean loginUtilisateur(String pseudo, String email, String mot_de_passe) {
		
		Utilisateur user1 = new Utilisateur();
		user1.setPseudo(pseudo);
		user1.setEmail(email);
		user1.setMot_de_passe(mot_de_passe);
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		return user.selectByLogin(user1);
		
	}
	
	
}
