package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateur")==null) {
			//delegue à la page login
		}
		
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		UtilisateurManager um = UtilisateurManager.getInstance();
		String ancienPseudo = user.getPseudo();
		String ancienEmail = user.getEmail();
		HashMap<String, String> listeErreurs = new HashMap<String, String>();

		if (request.getParameter("pseudo") != null && !request.getParameter("pseudo").isEmpty()) {
			if (!um.alphaNumVerif(request.getParameter("pseudo"))) {
				listeErreurs.put("pseudoCarSpeciaux", "Le pseudo ne doit pas comporter de caratères spéciaux");
			}
			System.out.println(ancienPseudo);
			if (!ancienPseudo.equals(request.getParameter("pseudo"))) {
				if (um.verifPseudo(user)) {
					listeErreurs.put("existPseudo", "Le pseudo existe déjà ");
				}
			} else {
				user.setPseudo(request.getParameter("pseudo"));

			}
		}

		if (request.getParameter("nom") != null && !request.getParameter("nom").isEmpty()) {
			user.setNom(request.getParameter("nom"));
		}

		if (request.getParameter("prenom") != null && !request.getParameter("prenom").isEmpty()) {
			user.setPrenom(request.getParameter("prenom"));
		}

		if (request.getParameter("email") != null && !request.getParameter("email").isEmpty()) {

			if (!ancienEmail.equals(request.getParameter("email"))) {
				if (um.verifEmail(user)) {
					listeErreurs.put("existEmail", "L'email existe déjà ");
				} else {
					user.setEmail(request.getParameter("email"));
				}
			}

		}

		if (request.getParameter("tel") != null && !request.getParameter("tel").isEmpty()) {
			user.setTelephone(request.getParameter("tel"));
		}

		if (request.getParameter("rue") != null && !request.getParameter("rue").isEmpty()) {
			user.setRue(request.getParameter("rue"));
		}

		if (request.getParameter("cpo") != null && !request.getParameter("cpo").isEmpty()) {
			if (request.getParameter("cpo").length() > 5) {
				listeErreurs.put("cpoIconnu", "Le code postal n'est pas reconnu");
			}
			if (!um.verifCpo(request.getParameter("cpo"))) {
				listeErreurs.put("cpoIconnu", "Le code postal n'est pas reconnu");
			} else {
				user.setCode_postal(request.getParameter("cpo"));
			}
		}

		if (request.getParameter("ville") != null && !request.getParameter("ville").isEmpty()) {
			user.setVille(request.getParameter("ville"));
		}

		Utilisateur user1 = (Utilisateur) um.identifiantUtilisateur(user);

		if (request.getParameter("pseudo") == null) {
			user.setPseudo(ancienPseudo);
		}
		if (HashPassword.hashpassword(request.getParameter("mdpActuel")) != null
				|| HashPassword.hashpassword(request.getParameter("mdpActuel"))
						.equals(HashPassword.hashpassword(user1.getMot_de_passe()))) {
			if (HashPassword.hashpassword(request.getParameter("nouveauMdp")) != null) {
				if (HashPassword.hashpassword(request.getParameter("mdpActuel")) != HashPassword
						.hashpassword(request.getParameter("nouveauMdp"))) {
					if (HashPassword.hashpassword(request.getParameter("confirmation")) != null) {
						if (!request.getParameter("nouveauMdp").equals(request.getParameter("confirmation"))) {
							listeErreurs.put("mdpDifferents", "Les mots de passes sont différents");
						} else {
							user.setMot_de_passe(HashPassword.hashpassword(request.getParameter("nouveauMdp")));
						}
					}
				}
			}
		} 

		if (listeErreurs.isEmpty()) {
			um.majUtilisateur(user);
			request.getRequestDispatcher("/monCompte").forward(request, response);
		} else {
			request.setAttribute("listeErreurs", listeErreurs);
			request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);
		}
	}

}
