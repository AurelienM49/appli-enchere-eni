package fr.eni.AppliEnchereEni.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.AppliEnchereEni.bll.ArticleManager;
import fr.eni.AppliEnchereEni.bll.EnchereManager;
import fr.eni.AppliEnchereEni.bll.UtilisateurManager;
import fr.eni.AppliEnchereEni.bo.ArticleVendu;
import fr.eni.AppliEnchereEni.bo.Enchere;
import fr.eni.AppliEnchereEni.bo.Retrait;
import fr.eni.AppliEnchereEni.bo.Utilisateur;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retrait retrait = new Retrait();
		
		//Enchere enchere = new Enchere();
		
		
		
		ArticleVendu article = new ArticleVendu();
		ArticleManager am = ArticleManager.getInstance();

		//On recupère le no d'article passé en paramètre de la requète
		//on converti ce no en int, on va ensuite attribuer ce no à notre objet article
		article.setNo_article(Integer.valueOf(request.getParameter("no_article")));
		//On fait appel au manager qui appel la BDD
		article = am.selectByIDTop1(article);
		
		
		request.setAttribute("article", article);
		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//Recupération des informations utilisateur à partir de la Session
		Utilisateur  utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		//Recuperation du numero de l'article dans les parametre de la requete
		 ArticleVendu articleVendu = new ArticleVendu();
		 articleVendu.setNo_article(Integer.valueOf(request.getParameter("no_article")));  
		 
		//Recuperation de prix propose dans la methode post
		int maProposition =Integer.valueOf(request.getParameter("proposition"));
		
		//on récupère les infos de l'enchère qui est en train d'être effetuée
		EnchereManager enchereManager = EnchereManager.getInstance();
		Enchere enchere = new Enchere();
		enchere.setUtilisateur(utilisateur);
		enchere.setArticle(articleVendu);
		enchere.setDate_enchere(LocalDate.now());
		enchere.setMontant_enchere(maProposition); 
		
		//on récupère les infos de l'article en question avec la meilleure enchère
		ArticleManager am = ArticleManager.getInstance();
		articleVendu = am.selectByIDTop1(articleVendu);
		
		//on récupère la valeur de l'ancienne enchère, et l'id de l'utilisateur qui a enchérit
		int currentProposition = articleVendu.getEnchere().getMontant_enchere();
		int currentIdUserEnchere = Integer.valueOf(request.getParameter("no_utilisateur_before"));
		
		UtilisateurManager um = UtilisateurManager.getInstance(); 
		Utilisateur currentUser = um.rechercheParId(currentIdUserEnchere);

		
		
		if (utilisateur.getCredit() < maProposition) {
			request.setAttribute("creditInsuffisant", "Votre offre depasse votre solde de credits");
			request.setAttribute("article", articleVendu);
			request.getRequestDispatcher("WEB-INF/jsp/detailVente.jsp").forward(request, response);
		}else {
		
			if (maProposition < 0) {
				request.setAttribute("nombrePositif", "Votre propistion ne peut être inférieur à 0");
				request.setAttribute("article", articleVendu);
				request.getRequestDispatcher("WEB-INF/jsp/detailVente.jsp").forward(request, response);
			}else {
				if (currentProposition > maProposition) {
					request.setAttribute("propositionInf", "La proposition est inférieur à la plus grosse offre");		
					request.setAttribute("article", articleVendu);
					request.getRequestDispatcher("WEB-INF/jsp/detailVente.jsp").forward(request, response);
				}else {
					
					//on update maintenant le credit du current user avec son ancienne proposition + son credit actuel
					if (currentIdUserEnchere==utilisateur.getNo_utilisateur()) {
						utilisateur.setCredit((currentUser.getCredit()+currentProposition)-maProposition);
						um.updateCreditUser(currentIdUserEnchere, utilisateur.getCredit());			
					}else {
						currentUser.setCredit(currentProposition + currentUser.getCredit());
						um.updateCreditUser(currentIdUserEnchere, currentUser.getCredit());
						
						
						//on enlève le credit du user qui fait la nouvelle proposition
						utilisateur.setCredit(utilisateur.getCredit()-maProposition);			
						um.updateCreditUser(utilisateur.getNo_utilisateur(), utilisateur.getCredit());		
					}
					
					
					//on update l'enchère
					if (enchereManager.selectByIdArticleIdUtilisateur(articleVendu.getNo_article(),utilisateur.getNo_utilisateur()) == null) {
						enchereManager.InsererEnchere(enchere);			
						response.sendRedirect("./login");
					
					} else {
						enchereManager.UpdateEnchere(enchere);			
						articleVendu = am.selectByIDTop1(articleVendu);
						response.sendRedirect("./login");

					}
				
				}
			}
			
		}
		
		

		
		

		
		
	}

}
