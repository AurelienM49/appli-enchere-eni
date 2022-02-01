package fr.eni.AppliEnchereEni.bo;

public class Retrait {

	//attributes
	private String rue, code_postal, ville;
	private ArticleVendu articleVendu;
	//constructor
	public Retrait() {
		
	}
	
	public Retrait(String rue, String code_postal, String ville, ArticleVendu articleVendu) {
		setRue(rue);
		setCode_postal(code_postal);
		setVille(ville);
		setArticleVendu(articleVendu);
	}




	//getters and setters
	public String getRue() {
		return rue;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Retrait(String rue, String code_postal, String ville) {
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
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
}
