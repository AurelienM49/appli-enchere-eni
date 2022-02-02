package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		if(request.getParameter("pseudo")!=null) {
			user.setPseudo(request.getParameter("pseudo"));
		} 
		
		if(request.getParameter("nom")!=null){
			user.setNom(request.getParameter("nom"));
		}
		
		if(request.getParameter("prenom")!=null){
			user.setPrenom(request.getParameter("prenom"));
		}
		
		if(request.getParameter("email")!=null){
			user.setEmail(request.getParameter("email"));
		}
		
		if(request.getParameter("tel")!=null){
			user.setTelephone(request.getParameter("tel"));
		}
		
		if(request.getParameter("rue")!=null){
			user.setRue(request.getParameter("rue"));
		}
		
		if(request.getParameter("cpo")!=null){
			user.setCode_postal(request.getParameter("cpo"));
		}
		
		if(request.getParameter("ville")!=null){
			user.setVille(request.getParameter("ville"));
		}
		
		Utilisateur user1 = (Utilisateur)um.identifiantUtilisateur(user);

		System.out.println(user1.getMot_de_passe());
		if(HashPassword.hashpassword(request.getParameter("mdpActuel"))!=null 
				&& HashPassword.hashpassword(request.getParameter("mdpActuel")).equals(HashPassword.hashpassword(user1.getMot_de_passe()))){
			if(HashPassword.hashpassword(request.getParameter("nouveauMdp"))!=null) {
				if(HashPassword.hashpassword(request.getParameter("mdpActuel"))!= HashPassword.hashpassword(request.getParameter("nouveauMdp"))) {
					if(HashPassword.hashpassword(request.getParameter("confirmation"))!=null) {
						if(HashPassword.hashpassword(request.getParameter("nouveauMdp")).equals(HashPassword.hashpassword(request.getParameter("confirmation")))) {
							user.setMot_de_passe(HashPassword.hashpassword(request.getParameter("nouveauMdp")));
						}
						
					}
				}
			}
		}
		
		um.majUtilisateur(user);
	}

}
