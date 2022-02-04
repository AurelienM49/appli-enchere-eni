package fr.eni.AppliEnchereEni.dal.CategorieDAO;

import fr.eni.AppliEnchereEni.bo.Categorie;

public interface CategorieDAO {

	Categorie selectByLibelle(Categorie categorie);
	Categorie selectById (Categorie categorie);
	void insertCategorie (Categorie categorie);
}
