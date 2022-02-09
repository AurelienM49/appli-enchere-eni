package fr.eni.AppliEnchereEni.bll;


import fr.eni.AppliEnchereEni.bo.Retrait;
import fr.eni.AppliEnchereEni.dal.DAOFactory;
import fr.eni.AppliEnchereEni.dal.retraitDAO.RetraitDAO;

public class RetraitManager {

	//RetraitManager Pattern Singleton
	
	private static RetraitManager instance;
	
	//Constructor 
	private RetraitManager() {
		
	}
	
	//Methods
	
		public static RetraitManager getInstance() {

			if (instance == null) {
				instance = new RetraitManager();
			}
			return instance;
		}
		
		//Fin du pattern Singleton
		
		public Retrait ajouterRetrait(Retrait retrait) {

			RetraitDAO retraitDAO = DAOFactory.createRetraitDaoJdbcImpl();

			return retraitDAO.insertRetrait(retrait);
		}
		
		
		public Retrait selectParNumArticle(int noArticle) {
			RetraitDAO retraitDAO = DAOFactory.createRetraitDaoJdbcImpl();
			return retraitDAO.selectByNoArticle(noArticle);
		}
	
}
