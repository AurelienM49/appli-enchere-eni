 package fr.eni.AppliEnchereEni.dal.UtilisateurDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.dal.bddTools.ConnectionProvider;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	//Déclaration des constantes
	
	private static final String INSERT_UTILISATEUR = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit)"
		   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_BY_PSEUDO ="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit "
			+ "FROM Utilisateurs WHERE pseudo = ?";
	private static final String SELECT_BY_EMAIL ="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit "
			+ "FROM Utilisateurs WHERE email = ?";
	private static final String SELECT_BY_LOGIN = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit FROM Utilisateurs WHERE email = ? or pseudo = ? and mot_de_passe = ?;";
	private static final String UPDATE_UTILISATEUR ="UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?;";
	private static final String SELECT_BY_IDENTIFIANT = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit FROM Utilisateurs WHERE email = ? or pseudo = ?;"; 
	private static final String SELECT_BY_ID="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit FROM Utilisateurs WHERE no_utilisateur=?;";
	private static final String DELETE_UTILISATEUR = "{call deleteUtilisateur (?)}";
	private static final String UPDATE_MONTANT ="UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?;";
	
	/**Méthode permettant d'inserer un nouvel utilisateur dans la BDD
	 * @param utilisateur
	 * @return void
	 * @throws
	 * @catch
	 * @finally Ferme les connexions ouvertes
	 */
	
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		
		try {
			 // ouverture de la connexion
			 cnx = ConnectionProvider.getConnection();
			
			//creation d'un prepareStatement (requete avec arguments)
			 pstmt = cnx.prepareStatement(INSERT_UTILISATEUR);
			 
			//recupère les paramètres envoyés par la servlet
			 pstmt.setString(1, utilisateur.getPseudo());
			 pstmt.setString(2, utilisateur.getNom());
			 pstmt.setString(3, utilisateur.getPrenom());
			 pstmt.setString(4, utilisateur.getEmail());
			 pstmt.setString(5, utilisateur.getTelephone());
			 pstmt.setString(6, utilisateur.getRue());
			 pstmt.setString(7, utilisateur.getCode_postal());
			 pstmt.setString(8, utilisateur.getVille());
			 pstmt.setString(9, utilisateur.getMot_de_passe());
			 pstmt.setInt(10, 100);
			 
			//exectute la requete coté BDD
			 pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
	}
	
	
	/**Méthode qui permet d'inserer un nouvel utilisateur dans la BDD
	 * @return void
	 * @param utilisateur
	 * @throws
	 * @catch
	 * @finally Ferme les connexions ouvertes
	 */
	
	@Override
	public Utilisateur selectByIdentifiant(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		Utilisateur user = null;
		try {
			// ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			
			//creation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_IDENTIFIANT);
			
			//recupère les paramètres envoyés par la servlet
			pstmt.setString(1,utilisateur.getEmail() );
			pstmt.setString(2, utilisateur.getPseudo());
			
			//exectute la requete coté BDD
			rs = pstmt.executeQuery();
			
			//si la requète est executée et nous renvoi les infos de cet utilisateur
			if (rs.next()) {
				//creation d'un objet de type Utilisateur que à qui l'on attribu ces informations
				user = new Utilisateur();
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCode_postal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMot_de_passe(rs.getString("mot_de_passe"));
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//fermeture de la connexion 
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return user;
	}

	/**
	 * Methode permettant de selectionner un Utilisateur par son Login
	 * c'est à dire : avec son pseudo ou email et son mot de passe
	 * @return Utilisateur user
	 * @throws
	 * @finally Ferme les connexions ouvertes
	 */
	@Override
	public Utilisateur selectByLogin(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		Utilisateur user = null;
		try {
			// ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			//creation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_LOGIN);
			
			//recupère les paramètres envoyés par la servlet
			pstmt.setString(1,utilisateur.getEmail() );
			pstmt.setString(2, utilisateur.getPseudo());
			pstmt.setString(3, utilisateur.getMot_de_passe());
			//exectute la requete coté BDD
			rs = pstmt.executeQuery();
			
			//si la requète est executée, les données sont insérées en BDD et attribuées au user
			if (rs.next()) {
				user = new Utilisateur();
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCode_postal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setCredit(rs.getInt("credit"));
			}
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//fermeture de la connection
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return user;
	}
	

	/**
	 * Methode permettant de selectionner un Utilisateur par son pseudo
	 * @param pseudo
	 * @return Boolean
	 * @throws
	 * @catch
	 * @finally Ferme les connexions ouvertes
	 */
	
	@Override
	public boolean selectByPseudo(String pseudo, int idUser) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		try {
			// ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			
			//creation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			
			//recupère les paramètres envoyés par la servlet
			pstmt.setString(1, pseudo);
			
			//exectute la requete coté BDD
			rs = pstmt.executeQuery();
			
			//si la requète est executée, la methode retourne true car ce pseudo est bien existant en BDD
			if (rs.next() && rs.getInt("no_utilisateur")!=idUser) {
				return true;
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return false;
	}
	
	
	@Override
	public Utilisateur selectByPseudo2(String pseudo) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		Utilisateur user = null;
		
		try {
			// ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			
			//creation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			
			//recupère les paramètres envoyés par la servlet
			pstmt.setString(1, pseudo);
			
			//exectute la requete coté BDD
			rs = pstmt.executeQuery();
			
			//si la requète est executée, la methode retourne true car ce pseudo est bien existant en BDD
			if (rs.next()){
				user = new Utilisateur();
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(pseudo);
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCode_postal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setCredit(rs.getInt("credit"));
				
				return user;
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return null;
	}

	/**Methode permettant de selectionner un Utilisateur par son email
	 * @param email
	 * @return boolean
	 * @throws
	 * @catch
	 * @finally Ferme les connexions ouvertes
	 */
	@Override
	public boolean selectByEmail(String email, int idUser) {
		Connection cnx = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		try {
			// ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			//creation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			
			//recupère les paramètres envoyés par la servlet
			pstmt.setString(1, email);
			
			//exectute la requete coté BDD
			rs = pstmt.executeQuery();
			
			//si la requète est executée, la methode retourne true car cet email est bien existant en BDD
			if (rs.next() && rs.getInt("no_utilisateur")!=idUser) {
				return true;
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return false;
	}


	/**Methode permettant de mettre à jour les données d'un utilisateur
	 * @param utilisateur
	 * @return void
	 * @throws
	 * @catch
	 * @finally Ferme les connexions ouvertes
	 */
	
	@Override
	public void updateUtilisateur(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			// ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			//creation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			//recupère les paramètres envoyés par la servlet
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCode_postal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMot_de_passe());
			pstmt.setInt(10, utilisateur.getNo_utilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}
	
	/** Methode permettant de selectionner un utlisateur par son id (numero utilisateur)
	 * @param utilisateur
	 * @return Utilisateur user
	 * @throws
	 * @catch
	 * @finally Ferme les connexions ouvertes
	 */
	@Override
	public Utilisateur selectByID(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt= null;
		ResultSet rs =null;
		Utilisateur user = null;
				
		try {
			//ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			// création d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			
			//recuperer les paramètres envoyés par la serlvet
			pstmt.setInt(1, utilisateur.getNo_utilisateur());
			
			//executer la requête coté BDD
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				//creation d'un objet de type Utilisateur que à qui l'on attribu ces informations
				user = new Utilisateur();
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCode_postal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMot_de_passe(rs.getString("mot_de_passe"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}	
		
		return user;
	}


	
	


	/** Methode permettant de selectionner un utlisateur par son id (numero utilisateur)
	 * @param utilisateur
	 * @return Utilisateur user
	 * @throws
	 * @catch
	 * @finally Ferme les connexions ouvertes
	 */
	@Override
	public Utilisateur selectByID(int id) {
		Connection cnx = null;
		PreparedStatement pstmt= null;
		ResultSet rs =null;
		
				
		try {
			//ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			// création d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			
			//recuperer les paramètres envoyés par la serlvet
			pstmt.setInt(1, id);
			
			//executer la requête coté BDD
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				//creation d'un objet de type Utilisateur que à qui l'on attribu ces informations
				Utilisateur user = new Utilisateur();
				user.setNo_utilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCode_postal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMot_de_passe(rs.getString("mot_de_passe"));
				
				return user;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}	
		
		return null;
	}

	/**
	 * Méthode pour supprimer un utilisateur 
	 * @param Utilisateur
	 */
	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) {
		Connection cnx = null;
		CallableStatement callStmt= null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			callStmt = cnx.prepareCall(DELETE_UTILISATEUR);
			callStmt.setInt(1, utilisateur.getNo_utilisateur());
			callStmt.execute();
			cnx.commit();
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}


	@Override
	public void updateCredit(int idUser, int proposition) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			// ouverture de la connexion
			cnx = ConnectionProvider.getConnection();
			//creation d'un prepareStatement (requete avec arguments)
			pstmt = cnx.prepareStatement(UPDATE_MONTANT);
			//recupère les paramètres envoyés par la servlet
			pstmt.setInt(1, proposition);
			pstmt.setInt(2, idUser);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//fermeture de la connexion
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		
	}


	}

	


