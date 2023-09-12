package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		
		HttpSession session = request.getSession();
		
		// COLLECTING VALUES FROM THE JSP FORM
		
		String enteredEmail = request.getParameter("email");
		String enteredAlias = request.getParameter("alias");
		String enteredPassword = request.getParameter("passwordUser");
		String enteredSurname = request.getParameter("surname");
		String enteredFirstName = request.getParameter("firstName");
		String enteredZipCode = request.getParameter("zipCode");
		String enteredCity = request.getParameter("city");
		String enteredStreet = request.getParameter("street");
		String enteredPhone = request.getParameter("phone");

		// CREATING USER
		
		User newUser = new User(enteredAlias,enteredSurname, enteredFirstName, enteredEmail,enteredPhone, 
								enteredStreet, enteredZipCode, enteredCity, enteredPassword);
		System.out.println(newUser);
		try {
			UserManager.getInstance().createUser(newUser);
			session.setAttribute("idUser", newUser.getIdUser());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorCodesList", e.getErrorCodeList());
			doGet(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("home");
		rd.forward(request, response);
	}

}
