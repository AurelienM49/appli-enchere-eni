package fr.eni.AppliEnchereEni.dal.UtilisateurDAO;



import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface UtilisateurDAO {

	void insertUtilisateur(Utilisateur utilisateur);
	Utilisateur selectByLogin (Utilisateur utilisateur);
	boolean selectByEmail(String email);
	boolean selectByPseudo(String pseudo);
	void updateUtilisateur(Utilisateur utilisateur);
	public Utilisateur selectByIdentifiant(Utilisateur utilisateur);
}
