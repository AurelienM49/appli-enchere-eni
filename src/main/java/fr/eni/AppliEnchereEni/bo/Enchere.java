package fr.eni.AppliEnchereEni.bo;

import java.time.LocalDate;

public class Enchere {

	//Attributes
	private LocalDate date_enchere;
	private int montant_enchere;
	//private int no_utilisateur;
	private Utilisateur utilisateur;
	//private int no_article;
	private ArticleVendu articleVendu;
	//Constructor
	public Enchere() {
	}
	
	public Enchere(LocalDate date_enchere, int montant_enchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
		setDate_enchere(date_enchere);
		setMontant_enchere(montant_enchere);
		setUtilisateur(utilisateur);
		setArticle(articleVendu);
	}
	
	
	//Getters && Setters
	public LocalDate getDate_enchere() {
		return date_enchere;
	}
	
	public void setDate_enchere(LocalDate date_enchere) {
		this.date_enchere = date_enchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public ArticleVendu getArticle() {
		return articleVendu;
	}
	public void setArticle(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	
}
