<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

        <div>
        	<div>
            	<p>Filtres :</p>
            </div>

            <div>
                <form action="" method="get">
                    <div>
                        <input type="text" placeholder="Le nom de l'article contient">
                    </div>
                    
                    <div>
                        <label for="categorie">Categorie : </label>
                        <select name="categorie" id="categorie">
                    	    <option value="informatique">Informatique</option>
                    	    <option value="ameublement">Ameublement</option>
                    	    <option value="vêtement">Vêtement</option>
                    	    <option value="sport&Loisir">Sport&Loisirs</option>
                         </select>
                    </div>
                    
                    <div>
                        <button type="submit">Rechercher</button>
                    </div>
                </form>
            </div>

            <div>
                <ul>
                    <li>élément liste enchères en cours</li>
                    <li>élément liste enchères en cours</li>
                </ul>
            </div>

            
        </div>