package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.User;

/**
 * Servlet implementation class CreateAccount
 */
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accountCreation.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. on récupère les valeurs du formulaire (paramètres)
		String enteredEmail = request.getParameter("email");
		String enteredAlias = request.getParameter("alias");
		String enteredPassword = request.getParameter("passwordUser");
		String enteredPasswordCheck = request.getParameter("passwordUserCheck");
		String enteredSurname = request.getParameter("surname");
		String enteredFirstName = request.getParameter("firstName");
		String enteredZipCode = request.getParameter("zipCode");
		String enteredCity = request.getParameter("city");
		String enteredStreet = request.getParameter("street");
		String enteredPhone = request.getParameter("phone");

		// TODO Verif des données = pas de doublon sur email et pseudo + 2 password identiques
		
		User newUser = new User(enteredAlias,enteredSurname, enteredFirstName, enteredEmail,enteredPhone, enteredStreet, enteredZipCode, enteredCity, enteredPassword);
		System.out.println(newUser);
		try {
			UserManager.getInstance().createUser(newUser);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("home");
		rd.forward(request, response);
	}

}
