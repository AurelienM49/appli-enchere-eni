package fr.eni.AppliEnchereEni.dal.UtilisateurDAO;



import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface UtilisateurDAO {

	void insertUtilisateur(Utilisateur utilisateur);
	boolean selectByLogin (Utilisateur utilisateur);
	
}
