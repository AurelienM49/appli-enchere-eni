package fr.eni.AppliEnchereEni.dal.UtilisateurDAO;



import fr.eni.AppliEnchereEni.bo.Utilisateur;

public interface UtilisateurDAO {

	//Inserer un nouvel utilisateur dans la BDD
	void insertUtilisateur(Utilisateur utilisateur);
	
	//Selectionner un utilisateur avec son pseudo ou email et son mot de passe
	Utilisateur selectByLogin (Utilisateur utilisateur);
	
	//Selectionner un utilisateur par son Email
	boolean selectByEmail(String email);
	
	//Selectionner un utlisateur par son pseudo
	boolean selectByPseudo(String pseudo);
	
	//Mettre � jour les donn�es d'un utlisateur
	void updateUtilisateur(Utilisateur utilisateur);
	
	//Selectionner un Utilisateur par son identifiant (pseudo ou email)
	public Utilisateur selectByIdentifiant(Utilisateur utilisateur);
	
	//Selectionner un Utilisateur par son ID
	public Utilisateur selectByID(Utilisateur utilisateur);
	
	
	//Supprimer un compte utilisateur
	void deleteUtilisateur (Utilisateur utilisateur);
	
}
