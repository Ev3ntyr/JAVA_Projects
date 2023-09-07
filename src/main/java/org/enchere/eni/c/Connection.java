package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.DAOFactory;


public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accountConnection.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String alias = request.getParameter("alias");
		String password = request.getParameter("passwordUser");
		
		User user = UserManager.getInstance().selectByAlias(alias);
		
		if (UserManager.getInstance().checkPassword(password, user.getPasswordUser())) {
			System.out.println("Password OK");
		} else {
			System.out.println("Wrong pwd");
		}
		
		doGet(request, response);
	}

}
