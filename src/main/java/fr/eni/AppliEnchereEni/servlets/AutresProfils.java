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
 * Servlet implementation class AutresProfils
 */
@WebServlet("/profil")
public class AutresProfils extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutresProfils() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//faire passer un attribut de type boolean à la JSP profil 
		//pour savoir si le profil est connecté ou pas
		boolean verifCnx = false;
		request.setAttribute("verifCnx",verifCnx);
		
		
		UtilisateurManager um = UtilisateurManager.getInstance();		

		
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/monCompte.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
