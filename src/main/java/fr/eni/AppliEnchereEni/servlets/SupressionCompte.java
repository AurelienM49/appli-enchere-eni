package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.AppliEnchereEni.bll.UtilisateurManager;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class SupressionCompte
 */
@WebServlet("/supression")
public class SupressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int no_user = Integer.valueOf(request.getParameter("no_utilisateur")) ;
		Utilisateur user = new Utilisateur();
		user.setNo_utilisateur(no_user);
		UtilisateurManager um = UtilisateurManager.getInstance();	
		System.out.println(user.getNo_utilisateur());
		um.supprimerUser(user);			
		response.sendRedirect("./");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
