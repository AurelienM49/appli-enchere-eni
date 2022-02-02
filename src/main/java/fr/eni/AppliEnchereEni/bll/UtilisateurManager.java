package fr.eni.AppliEnchereEni.bll;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	
	public boolean loginUtilisateur(Utilisateur utilisateur) {
		
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		return user.selectByLogin(utilisateur);
		
	}
	
	public HashMap<String, String> validationUser(Utilisateur utilisateur) {
		HashMap<String, String> listeErreurs = null;
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		if(utilisateur.getPseudo().isEmpty()) {
			listeErreurs.put("emptyPseudo", "Pseudo obligatoire");
		} else if (utilisateur.getNom().isEmpty()){
			listeErreurs.put("emptyNom", "Nom obligatoire");
		} else if (utilisateur.getPrenom().isEmpty()) {
			listeErreurs.put("emptyPrenom", "Prenom obligatoire");
		} else if (utilisateur.getEmail().isEmpty()){
			listeErreurs.put("emptyEmail", "Email obligatoire");
		} else if (utilisateur.getTelephone().isEmpty()) {
			listeErreurs.put("emptyTel", "Télephone obligatoire");
		} else if (utilisateur.getRue().isEmpty()){
			listeErreurs.put("emptyRue", "Le nom de rue est obligatoire");
		} else if (utilisateur.getCode_postal().isEmpty()){
			listeErreurs.put("emptyCpo", "Le code postal est obligatoire");
		} else if (utilisateur.getRue().isEmpty()){
			listeErreurs.put("emptyRue", "Le nom de rue est obligatoire");
		} else if (utilisateur.getVille().isEmpty()){
			listeErreurs.put("emptyVille", "La ville est obligatoire");
		} else if (utilisateur.getMot_de_passe().isEmpty()){
			listeErreurs.put("emptyMdp", "Le mot de passe est obligatoire");
		} else if (utilisateur.getMot_de_passe_cofirm().isEmpty()){
			listeErreurs.put("emptyMdpConfirm", "Le mot de passe de confirmation est obligatoire");
		} else if (!alphaNumVerif(utilisateur.getPseudo())) {
			listeErreurs.put("pseudoCarSpeciaux", "Le pseudo ne doit pas comporter de caratères spéciaux");
		} else if(user.selectByPseudo(utilisateur)) {
			listeErreurs.put("existPseudo", "Le pseudo existe déjà ");
		} else if (user.selectByEmail(utilisateur)) {
			listeErreurs.put("existEmail", "L'email existe déjà ");
		} else if (utilisateur.getMot_de_passe()!= utilisateur.getMot_de_passe_cofirm()) {
			listeErreurs.put("mdpDifferents", "Les mots de passes sont différents");

		}


		
		
		
		
		return null;
		
	}

	public boolean alphaNumVerif(String pseudo) {
      	String regExpression = "[a-zA-Z_0-9]*";
        String sample = "abcde";

        Pattern p = Pattern.compile (regExpression);
        Matcher m = p.matcher (sample);
        boolean b = m.matches ();
        return b;
    }
}
