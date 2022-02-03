package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class monCompteServlet
 */
@WebServlet("/monCompte")
public class MonCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");				
		request.setAttribute("utilisateur", user);
		boolean verifCnx = true;
		request.setAttribute("verifCnx", verifCnx);

		
		request.getRequestDispatcher("/WEB-INF/jsp/monCompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");				
		request.setAttribute("utilisateur", user);
		boolean verifCnx = true;
		request.setAttribute("verifCnx", verifCnx);
		request.getRequestDispatcher("/WEB-INF/jsp/monCompte.jsp").forward(request, response);

	}

}
