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
import org.enchere.eni.m.dal.DAOFactory;


public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accountConnection.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String alias = request.getParameter("alias");
		String password = request.getParameter("passwordUser");
		boolean rememberMe = request.getParameter("rememberMe") != null;
		
		User user = UserManager.getInstance().selectByAlias(alias);
		System.out.println("remember check ? " + rememberMe);
		
		if (UserManager.getInstance().checkPassword(password, user.getPasswordUser())) {
			System.out.println("Password OK");
			HttpSession session = request.getSession();
			session.setAttribute("idUser", user.getIdUser());
			
			if (rememberMe) {
				session.setMaxInactiveInterval(Integer.MAX_VALUE);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("home");
			rd.forward(request, response);
			
			
		} else {
			System.out.println("Wrong pwd");
			doGet(request, response);
		}
		
		
	}

}
