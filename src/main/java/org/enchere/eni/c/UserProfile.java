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
 * Servlet implementation class UserProfile
 */
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idUser = 0;
		
		if (request.getParameter("idUser") != null) {
			try {
				idUser = Integer.valueOf(request.getParameter("idUser"));
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR - Cannot get the requested user ID");
				nfe.printStackTrace();
			}
		} else {
			HttpSession session = request.getSession();
			
			if (session.getAttribute("idUser") != null) {
				idUser = (int) session.getAttribute("idUser");
			}
		}

		User user = UserManager.getInstance().selectById(idUser);
		
		System.out.println(user);

		request.setAttribute("user", user);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/userProfile.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
