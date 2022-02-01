package fr.eni.AppliEnchereEni.bo;

import java.util.List;

public class Categorie {

	//Attributes
	private int no_categorie;
	private String libelle;
	private List<ArticleVendu> listeArticles;
	
	//Constructor
	public Categorie() {
	}
	

	public Categorie(String libelle) {
		setLibelle(libelle);
	}


	//Getter && Setters
	public int getNo_categorie() {
		return no_categorie;
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<ArticleVendu> getListeArticles() {
		return listeArticles;
	}
	public void setListeArticles(List<ArticleVendu> listeArticles) {
		this.listeArticles = listeArticles;
	}
	
	
	
	
}
