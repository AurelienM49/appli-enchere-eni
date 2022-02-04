package fr.eni.AppliEnchereEni.bll;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.UtilisateurDAO.UtilisateurDAO;

public class UtilisateurManager {

	// Utilisateur Manager Singleton

	private static UtilisateurManager instance;

	// Constructor
	private UtilisateurManager() {
	}

	// Methods

	public static UtilisateurManager getInstance() {

		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	// fin Singleton

	
	/**
	 * M�thode pour ajouter un utilisateur qui s'inscrit sur le site
	 * @param utilisateur
	 */
	public void ajouterUtilisateur(Utilisateur utilisateur) {

		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();

		user.insertUtilisateur(utilisateur);
	}

	/**
	 * M�thode pour v�rifier si l'utilisateur qui veut loger existe bien en BDD
	 * @param utilisateur
	 * @return utilisateur (cet utilisateur sera ensuite mont� en session)
	 */
	public Utilisateur loginUtilisateur(Utilisateur utilisateur) {
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();

		return user.selectByLogin(utilisateur);

	}

	/**
	 * M�thode pour v�rifier si les identifiants existe en BDD
	 * @param utilisateur
	 * @return Utilisateur 
	 */
	public Utilisateur identifiantUtilisateur(Utilisateur utilisateur) {
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();

		return user.selectByIdentifiant(utilisateur);

	}
	
	public boolean verifPseudo(String pseudo) {
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		return user.selectByPseudo(pseudo);
	}
	
	
	/**
	 * Methode pour v�irifier si l'adresse mail existe en BDD
	 * @param email
	 * @return boolean
	 */
	public boolean verifEmail(String email) {
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		return user.selectByEmail(email);
	}


	/**
	 * M�thode pour v�rifier les attributs rentrer par un utilisateur lorsqu'il s'enrengistre
	 * et renvoyer une liste d'erreurs
	 * @param utilisateur
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> validationUser(Utilisateur utilisateur) {
		HashMap<String, String> listeErreurs = new HashMap<String, String>();
		UtilisateurDAO user = DAOFactory.createUtilisateurDAOJdbcImpl();
		if (utilisateur.getPseudo().isEmpty()) {
			listeErreurs.put("emptyPseudo", "Le pseudo obligatoire");
		}
		if (utilisateur.getNom().isEmpty()) {
			listeErreurs.put("emptyNom", "Le nom obligatoire");
		}
		if (utilisateur.getPrenom().isEmpty()) {
			listeErreurs.put("emptyPrenom", "Le prenom obligatoire");
		}
		if (utilisateur.getEmail().isEmpty()) {
			listeErreurs.put("emptyEmail", "l'email obligatoire");
		}
		if (utilisateur.getTelephone().isEmpty()) {
			listeErreurs.put("emptyTel", "le t�lephone obligatoire");
		}
		if (utilisateur.getRue().isEmpty()) {
			listeErreurs.put("emptyRue", "Le nom de rue est obligatoire");
		}
		if (utilisateur.getCode_postal().isEmpty()) {
			listeErreurs.put("emptyCpo", "Le code postal est obligatoire");
		}
		if (utilisateur.getRue().isEmpty()) {
			listeErreurs.put("emptyRue", "Le nom de rue est obligatoire");
		}
		if (utilisateur.getVille().isEmpty()) {
			listeErreurs.put("emptyVille", "La ville est obligatoire");
		}
		if (utilisateur.getMot_de_passe().isEmpty()) {
			listeErreurs.put("emptyMdp", "Le mot de passe est obligatoire");
		}
		if (utilisateur.getMot_de_passe_cofirm().isEmpty()) {
			listeErreurs.put("emptyMdpConfirm", "Le mot de passe de confirmation est obligatoire");
		}
		if (!alphaNumVerif(utilisateur.getPseudo())) {
			listeErreurs.put("pseudoCarSpeciaux", "Le pseudo ne doit pas comporter de carat�res sp�ciaux");
		}
		if (user.selectByPseudo(utilisateur.getPseudo())) {
			listeErreurs.put("existPseudo", "Le pseudo existe d�j� ");
		}
		if (user.selectByEmail(utilisateur.getEmail())) {
			listeErreurs.put("existEmail", "L'email existe d�j� ");
		}
		if (!utilisateur.getMot_de_passe().equals(utilisateur.getMot_de_passe_cofirm())) {
			listeErreurs.put("mdpDifferents", "Les mots de passes sont diff�rents");
		}
		if (utilisateur.getCode_postal().length() > 5) {
			listeErreurs.put("cpoIconnu", "Le code postal n'est pas reconnu");
		}
		if (!verifCpo(utilisateur.getCode_postal())) {
			listeErreurs.put("cpoIconnu", "Le code postal n'est pas reconnu");
		}

		return listeErreurs;
	}

	/**
	 * M�thode pour v�rifier qu'une chaine ne comporte pas de caract�res sp�ciaux
	 * @param pseudo
	 * @return boolean
	 */
	public boolean alphaNumVerif(String pseudo) {
		String regExpression = "[a-zA-Z_0-9]*";

		Pattern p = Pattern.compile(regExpression);
		Matcher m = p.matcher(pseudo);
		boolean b = m.matches();
		return b;
	}

	
	
	/**
	 * Methode pour v�rifier qu'une chaine ne contient que des chiffres
	 * @param cpo
	 * @return boolean
	 */
	public boolean verifCpo(String cpo) {
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
