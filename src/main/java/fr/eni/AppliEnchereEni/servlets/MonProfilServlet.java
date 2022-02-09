package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.AppliEnchereEni.bll.UtilisateurManager;
import fr.eni.AppliEnchereEni.bo.Utilisateur;
import fr.eni.AppliEnchereEni.helpers.HashPassword;

/**
 * Servlet implementation class MonProfilServlet
 */
@WebServlet("/monProfil")
public class MonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//v�rifier si l'utilisateur 
		if (request.getSession().getAttribute("utilisateur") == null) {
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}

		//on r�cup�re l'utlisateur qui est en session
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		//on stocke le pseudo et l'email pour v�rifier sur la BDD
		//puisqu'on risque des les �craser par la suite
		String currentPseudo = user.getPseudo();
		String currentEmail = user.getEmail();
		
		//on cr�er une HashMap pour rentrer les erreurs
		HashMap<String, String> listeErreurs = new HashMap<String, String>();

		
		//on v�rifie si le pseudo est vide
		if (request.getParameter("pseudo") == null || request.getParameter("pseudo").isEmpty()) {
			listeErreurs.put("emptyPseudo", "Le pseudo est vide");
		}else {
			// on v�rifie si le pseudo est diff�rent du pseudo actuel
			if (!request.getParameter("pseudo").equals(currentPseudo)) {
				
				System.out.println(currentPseudo);
				System.out.println(request.getParameter("pseudo"));
				//si il est diff�rent on v�rifie si le nouveau pseudo ne contient pas caract sp�ciaux
				if (!um.alphaNumVerif(request.getParameter("pseudo"))) {				
					listeErreurs.put("pseudoCarSpeciaux", "Le pseudo ne doit pas comporter de carat�res sp�ciaux");
				}

				//on v�rifie si le nouveau est d�j� existant
				if (um.verifPseudo(request.getParameter("pseudo"))) {
					listeErreurs.put("existPseudo", "Le pseudo existe d�j� ");
				} else {
					user.setPseudo(request.getParameter("pseudo"));
				}
				
				//si le champ pseudo n'a pas �t� modifi�, alors il prendre la value par d�faut
			} 
		}
		
		if (request.getParameter("email") == null || request.getParameter("email").isEmpty()) {
			listeErreurs.put("emptyemail", "Le email est vide");
		}else {
			// on v�rifie si le mail est diff�rent du pseudo actuel
			if (!request.getParameter("email").equals(currentEmail)) {
				
				
				//on v�rifie si le nouveau est d�j� existant
				if (um.verifEmail(request.getParameter("email"))) {
					listeErreurs.put("existEmail", "Le email existe d�j� ");
					
					//sinon on l'ajoute dans 
				} else {
					user.setEmail(request.getParameter("email"));
				}
				
				//si le champ mail n'a pas �t� modifi�, alors il prendre la value par d�faut
			} 
		}

		
		
		//on v�rifie les attributs ont chang�s --> si non on change pas / si oui alors on le remplace
		if (request.getParameter("nom")==null || request.getParameter("nom").isEmpty()) {
			listeErreurs.put("emptyNom", "Le nom est vide");
		
		} else {
			user.setNom(request.getParameter("nom"));
		}

		if (request.getParameter("prenom")==null || request.getParameter("prenom").isEmpty()) {
			listeErreurs.put("emptyPrenom", "Le pr�nom est vide");
		
		} else {
			user.setPrenom(request.getParameter("prenom"));
		}
		
		if (request.getParameter("tel")==null || request.getParameter("tel").isEmpty()) {
			listeErreurs.put("emptyTel", "Le t�l�phone est vide");
		
		}else {
			user.setTelephone(request.getParameter("tel"));
		}
		
		if (request.getParameter("rue")==null || request.getParameter("rue").isEmpty()) {
			listeErreurs.put("emptyRue", "Le rue est vide");
		
		} else {
			user.setRue(request.getParameter("rue"));
		}
		
		if (request.getParameter("cpo")==null || request.getParameter("cpo").isEmpty()) {
			listeErreurs.put("cpoInconnu", "Le cpo est vide");
		
		} else {
			if (request.getParameter("cpo").length() > 5) {
				listeErreurs.put("cpoInconnu", "Le code postal n'est pas reconnu");
			}else {
				user.setRue(request.getParameter("cpo"));
			}		
		}

		if (request.getParameter("ville")==null || request.getParameter("ville").isEmpty()) {
			listeErreurs.put("emptyVille", "La ville est vide");
		
		} else {
			user.setRue(request.getParameter("ville"));
		}
		

		//on vient r�cup�rer le user dans la BDD pour r�cup�rer le mot de passe afin de v�rifier 
		//avec le mot de passe rentrer dans l'input : "mot de passe actuel"
		Utilisateur user1 = (Utilisateur) um.identifiantUtilisateur(user);

		//condition (un peu longue :D) pour v�rifier les mots de passe
		if (HashPassword.hashpassword(request.getParameter("mdpActuel")) != null
				|| HashPassword.hashpassword(request.getParameter("mdpActuel"))
						.equals(HashPassword.hashpassword(user1.getMot_de_passe()))) {
			if (HashPassword.hashpassword(request.getParameter("nouveauMdp")) != null) {
				if (HashPassword.hashpassword(request.getParameter("mdpActuel")) != HashPassword
						.hashpassword(request.getParameter("nouveauMdp"))) {
					if (HashPassword.hashpassword(request.getParameter("confirmation")) != null) {
						if (!request.getParameter("nouveauMdp").equals(request.getParameter("confirmation"))) {
							listeErreurs.put("mdpDifferents", "Les mots de passes sont diff�rents");
						} else {
							user.setMot_de_passe(HashPassword.hashpassword(request.getParameter("nouveauMdp")));
						}
					}
				}
			}
		} else {
			listeErreurs.put("mdpActuelFalse", "Le mot de passe actuel n'est pas le bon");
		}

		
		//si la liste d'erreurs est nul, on ajoute les modifications en abse de donn�es
		//sinon on revient sur la page de modification avec les erreurs
		if (listeErreurs.isEmpty()) {
			um.majUtilisateur(user);
			request.getRequestDispatcher("/monCompte").forward(request, response);
		} else {
			request.setAttribute("user", user);
			request.setAttribute("listeErreurs", listeErreurs);
			request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);
		}
	}

}
