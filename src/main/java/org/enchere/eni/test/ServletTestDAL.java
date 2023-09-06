package org.enchere.eni.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.enchere.eni.c.BusinessException;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.User;


public class ServletTestDAL extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Crée un utilisateur de test
		User quentin = new User();
        quentin.setAlias("Quent2");
        quentin.setSurname("Quentin");
        quentin.setFirstName("Quentib");
        quentin.setEmail("test@example.com");
        quentin.setPhone("1234567890");
        quentin.setStreet("123 Rue de Test");
        quentin.setZipCode("12345");
        quentin.setCity("VilleTest");
        quentin.setPasswordUser("MotDePasseTest"); 

        // Appelle la méthode createUser pour ajouter l'utilisateur à la base de données
        try {
			UserManager.getInstance().createUser(quentin);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(quentin);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
