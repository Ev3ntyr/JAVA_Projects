package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.User;

public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = UserManager.getInstance().selectAll();
		request.setAttribute("users", users);
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adminUser.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idRequest = request.getParameter("userID");
		int idUser = 0;
		try {
			idUser = Integer.valueOf(idRequest);
		} catch (NumberFormatException nfe) {
			System.out.println("ERROR WHEN PARSING USER ID");
			nfe.printStackTrace();
		}
		
		
		if (request.getParameter("deactivate") != null) {
			
			User user = UserManager.getInstance().selectById(idUser);
			user.setIsActive(false);
			UserManager.getInstance().update(user);
			
		} else if (request.getParameter("activate") != null) {

			User user = UserManager.getInstance().selectById(idUser);
			user.setIsActive(true);
			UserManager.getInstance().update(user);
			
		} else {
			
			System.out.println("delete : ");
			
		}
		
		doGet(request, response);
	}

}
