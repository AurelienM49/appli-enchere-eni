package fr.eni.AppliEnchereEni.bll;

import java.util.HashMap;
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
	
	
	public Utilisateur loginUtilisateur(Utilisateur utilisateur) {		
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();	
		
		Utilisateur user2 = user.selectByLogin(utilisateur);
		System.out.println("VERIF MANAGER : "+ user2.getNom());
		return user.selectByLogin(utilisateur);
		
	}
	
	public HashMap<String, String> validationUser(Utilisateur utilisateur) {
		HashMap<String, String> listeErreurs = new HashMap<String, String>();
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		if(utilisateur.getPseudo().isEmpty()) {
			listeErreurs.put("emptyPseudo", "Le pseudo obligatoire");
		} if (utilisateur.getNom().isEmpty()){
			listeErreurs.put("emptyNom", "Le nom obligatoire");
		} if (utilisateur.getPrenom().isEmpty()) {
			listeErreurs.put("emptyPrenom", "Le prenom obligatoire");
		} if (utilisateur.getEmail().isEmpty()){
			listeErreurs.put("emptyEmail", "l'email obligatoire");
		} if (utilisateur.getTelephone().isEmpty()) {
			listeErreurs.put("emptyTel", "le télephone obligatoire");
		} if (utilisateur.getRue().isEmpty()){
			listeErreurs.put("emptyRue", "Le nom de rue est obligatoire");
		} if (utilisateur.getCode_postal().isEmpty()){
			listeErreurs.put("emptyCpo", "Le code postal est obligatoire");
		} if (utilisateur.getRue().isEmpty()){
			listeErreurs.put("emptyRue", "Le nom de rue est obligatoire");
		} if (utilisateur.getVille().isEmpty()){
			listeErreurs.put("emptyVille", "La ville est obligatoire");
		} if (utilisateur.getMot_de_passe().isEmpty()){
			listeErreurs.put("emptyMdp", "Le mot de passe est obligatoire");
		} if (utilisateur.getMot_de_passe_cofirm().isEmpty()){
			listeErreurs.put("emptyMdpConfirm", "Le mot de passe de confirmation est obligatoire");
		} if (!alphaNumVerif(utilisateur.getPseudo())) {
			listeErreurs.put("pseudoCarSpeciaux", "Le pseudo ne doit pas comporter de caratères spéciaux");
		} if(user.selectByPseudo(utilisateur)) {
			listeErreurs.put("existPseudo", "Le pseudo existe déjà ");
		} if (user.selectByEmail(utilisateur)) {
			listeErreurs.put("existEmail", "L'email existe déjà ");
		} if (!utilisateur.getMot_de_passe().equals(utilisateur.getMot_de_passe_cofirm())) {
			listeErreurs.put("mdpDifferents", "Les mots de passes sont différents");
		} if(utilisateur.getCode_postal().length()>5) {
			listeErreurs.put("cpoIconnu", "La code postal n'est pas reconnu");
		} if(!verifCpo(utilisateur.getCode_postal())) {
			listeErreurs.put("cpoIconnu", "La code postal n'est pas reconnu");
		}
	
			return listeErreurs;
	}

	public boolean alphaNumVerif(String pseudo) {
      	String regExpression = "[a-zA-Z_0-9]*";
        String sample = "abcde";

        Pattern p = Pattern.compile (regExpression);
        Matcher m = p.matcher (sample);
        boolean b = m.matches ();
        return b;
    }
	
	public boolean verifCpo(String cpo) {
        boolean b = true;
        try {
            Float f = Float.parseFloat(cpo);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
      
	}
	
	public void majUtilisateur(Utilisateur utilisateur) {
		
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		user.updateUtilisateur(utilisateur);
		
	}
}
