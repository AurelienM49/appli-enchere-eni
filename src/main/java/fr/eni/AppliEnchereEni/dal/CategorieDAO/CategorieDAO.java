package fr.eni.AppliEnchereEni.dal.CategorieDAO;

import fr.eni.AppliEnchereEni.bo.Categorie;

public interface CategorieDAO {

	public Categorie selectByLibelle(Categorie categorie);
	public Categorie selectById (Categorie categorie);
	public Categorie selectByID(int id);
	public void insertCategorie (Categorie categorie);
}
