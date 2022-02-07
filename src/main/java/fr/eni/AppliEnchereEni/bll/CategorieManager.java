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
		 * M�thode pour ajouter une cat�gorie qui s'inscrit sur le site
		 * @param cat�gorie
		 */
		public void ajouterCategorie(Categorie categorie) {
			CategorieDAO cat = DAOFactory.createCategorietDaoJdbcImpl();
			cat.insertCategorie(categorie);
		}

		
		/**
		 * M�thode pour retrouver une categorie en bdd en fonction de son ID
		 * @param Categorie
		 * @return Categorie 
		 */	
		public Categorie catById (Categorie categorie) {
			CategorieDAO cat = DAOFactory.createCategorietDaoJdbcImpl();
			return cat.selectById(categorie);
		}
		
		
		
		/**
		 * M�thode pour retrouver une categorie en bdd en fonction de son libelle (unique)
		 * @param Categorie
		 * @return Categorie 
		 */	
		public Categorie catByLibelle (Categorie categorie) {
			CategorieDAO cat = DAOFactory.createCategorietDaoJdbcImpl();
			return cat.selectByLibelle(categorie);
		}
}
