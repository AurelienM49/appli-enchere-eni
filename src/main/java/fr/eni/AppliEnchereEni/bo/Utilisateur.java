package fr.eni.AppliEnchereEni.bo;

import java.util.List;

public class Utilisateur {

	//Attributes
	private int no_utilisateur;
	private String pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, mot_de_passe_cofirm;
	private int credit;
	private boolean administrateur;
	private List<ArticleVendu> listeArticles;
	private List<Enchere> listeEncheres;
	



	//Constructor
	public Utilisateur() {
	}
	
	

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe) {
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setTelephone(telephone);
		setRue(rue);
		setCode_postal(code_postal);
		setVille(ville);
		setMot_de_passe(mot_de_passe);
	}	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe, int credit, boolean administrateur) {
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setTelephone(telephone);
		setRue(rue);
		setCode_postal(code_postal);
		setVille(ville);
		setMot_de_passe(mot_de_passe);
		setCredit(credit);
		setAdministrateur(administrateur);
	}
	
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mot_de_passe, int credit, boolean administrateur,
			List<ArticleVendu> listeArticles, List<Enchere> listeEncheres) {		
		this(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur);
		this.listeArticles = listeArticles;
		this.listeEncheres = listeEncheres;
	}
	
	//Getters && Setters
	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	public List<ArticleVendu> getListeArticles() {
		return listeArticles;
	}
	public void setListeArticles(List<ArticleVendu> listeArticles) {
		this.listeArticles = listeArticles;
	}
	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}
	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}
	public String getMot_de_passe_cofirm() {
		return mot_de_passe_cofirm;
	}
	public void setMot_de_passe_cofirm(String mot_de_passe_cofirm) {
		this.mot_de_passe_cofirm = mot_de_passe_cofirm;
	}

	
	
	
	
}
