package fr.eni.AppliEnchereEni.bll;

import fr.eni.AppliEnchereEni.bo.Categorie;
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.CategorieDAO.CategorieDAO;

public class CategorieManager {

	// Utilisateur Manager Singleton

		private static CategorieManager instance;

		// Constructor
		private CategorieManager() {
		}

		// Methods

		public static CategorieManager getInstance() {

			if (instance == null) {
				instance = new CategorieManager();
			}
			return instance;
		}
	
	//fin singleton
	
		
		
		/**
		 * Méthode pour ajouter une catégorie qui s'inscrit sur le site
		 * @param catégorie
		 */
		public void ajouterCategorie(Categorie categorie) {
			CategorieDAO cat = DAOFactory.createCategorietDaoJdbcImpl();
			cat.insertCategorie(categorie);
		}

		
		/**
		 * Méthode pour retrouver une categorie en bdd en fonction de son ID
		 * @param Categorie
		 * @return Categorie 
		 */	
		public Categorie catById (Categorie categorie) {
			CategorieDAO cat = DAOFactory.createCategorietDaoJdbcImpl();
			return cat.selectById(categorie);
		}
		
		
		
		/**
		 * Méthode pour retrouver une categorie en bdd en fonction de son libelle (unique)
		 * @param Categorie
		 * @return Categorie 
		 */	
		public Categorie catByLibelle (Categorie categorie) {
			CategorieDAO cat = DAOFactory.createCategorietDaoJdbcImpl();
			return cat.selectByLibelle(categorie);
		}
}
