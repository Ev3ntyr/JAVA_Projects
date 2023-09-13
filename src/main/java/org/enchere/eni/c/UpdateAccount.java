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

public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("idUser");
		
		User user = UserManager.getInstance().selectById(idUser);
		
		request.setAttribute("user", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accountUpdate.jsp");
		rd.forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("idUser");
		
		User user = UserManager.getInstance().selectById(idUser);
		
		String alias = request.getParameter("alias");
		String surname = request.getParameter("surname");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String street = request.getParameter("street");
		String zipCode = request.getParameter("zipCode");
		String city = request.getParameter("city");
		String newPasswordUser = request.getParameter("newPasswordUser");
		
		if (!alias.isBlank()) {
			user.setAlias(alias);
		}
		if (!surname.isBlank()) {
			user.setSurname(surname);
		}
		if (!firstName.isBlank()) {
			user.setFirstName(firstName);
		}
		if (!email.isBlank()) {
			user.setEmail(email);
		}
		if (!phone.isBlank()) {
			user.setPhone(phone);
		}
		if (!street.isBlank()) {
			user.setStreet(street);
		}
		if (!zipCode.isBlank()) {
			user.setZipCode(zipCode);
		}
		if (!city.isBlank()) {
			user.setCity(city);
		}
		if (!newPasswordUser.isBlank()) {
			user.setPasswordUser(newPasswordUser);
		}
		
		UserManager.getInstance().update(user);
		
		RequestDispatcher rd = request.getRequestDispatcher("userProfile");
		rd.forward(request, response);
	
	}

}
