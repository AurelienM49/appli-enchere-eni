package fr.eni.AppliEnchereEni.bo;

import java.time.LocalDate;

public class ArticleVendu {

	//Attributes
	private int no_article;
	private String nom_article, description;
	private LocalDate date_debut_encheres, date_fin_encheres;
	private int prix_initial, prix_vente, no_categorie;
	private Categorie categorie;
	private Utilisateur utilisateur;
	private Retrait retrait;
	private Enchere enchere;
	
	


	//Constructors
	public ArticleVendu() {
		
	}
	
	public ArticleVendu(String nom_article, String description, LocalDate date_debut_encheres,
			LocalDate date_fin_encheres, Categorie categorie) {
		setNom_article(nom_article);
		setDescription(description);
		setDate_debut_encheres(date_debut_encheres);
		setDate_fin_encheres(date_fin_encheres);
		setCategorie(categorie);
	}
	
	
	
	public ArticleVendu(String nom_article, String description, LocalDate date_debut_encheres,
			LocalDate date_fin_encheres, int prix_initial, int prix_vente, int no_categorie, Categorie categorie,
			Utilisateur utilisateur) {
		this(nom_article, description, date_debut_encheres, date_fin_encheres, categorie);
		setPrix_initial(prix_initial);
		setPrix_vente(prix_vente);
		setNo_categorie(no_categorie);
		setUtilisateur(utilisateur);
	} 
	
	//Getters && Getters
	
	public int getNo_article() {
		return no_article;
	}
	
	public void setNo_article(int No_article ) {
		this.no_article = No_article;
	}

	public String getNom_article() {
		return nom_article;
	}
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate_debut_encheres() {
		return date_debut_encheres;
	}
	public void setDate_debut_encheres(LocalDate date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}
	public LocalDate getDate_fin_encheres() {
		return date_fin_encheres;
	}
	public void setDate_fin_encheres(LocalDate date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}
	public int getPrix_initial() {
		return prix_initial;
	}
	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}
	public int getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}

	public int getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	
	public Enchere getEnchere() {
		return enchere;
	}



	@Override
	public String toString() {
		return "ArticleVendu [nom_article=" + nom_article + ", date_fin_encheres=" + date_fin_encheres
				+ ", prix_initial=" + prix_initial + ", utilisateur=" + utilisateur.getPseudo() + "]";
	}
	
	
	
	
}
