package fr.eni.AppliEnchereEni.dal.UtilisateurDAO;



import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface UtilisateurDAO {

	void insertUtilisateur(Utilisateur utilisateur);
	Utilisateur selectByLogin (Utilisateur utilisateur);
	boolean selectByEmail(Utilisateur utilisateur);
	boolean selectByPseudo(Utilisateur utilisateur);
	void updateUtilisateur(Utilisateur utilisateur);
}
