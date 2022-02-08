package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.AppliEnchereEni.bll.EnchereManager;
import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class MesEncheres
 */
@WebServlet("/mesAnnoncesPostees")
public class MesAnnoncesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesAnnoncesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//Recuperation des informations de notre utillisateur à partir de la session
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		EnchereManager enchereManager = EnchereManager.getInstance();
		List <ArticleVendu> mesAnnonces = enchereManager.listerMesAnnonces(utilisateur);
		
		request.setAttribute("listeDeMesAnnonces",mesAnnonces);
		request.getRequestDispatcher("WEB-INF/jsp/mesAnnoncesPostees.jsp").forward(request, response);
		
		System.out.println(enchereManager.listerMesAnnonces(utilisateur));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
