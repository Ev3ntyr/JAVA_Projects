package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.enchere.eni.m.bll.UserManager;

public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUser = Integer.valueOf(request.getParameter("idUser"));
		UserManager.getInstance().deactivate(idUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("logout");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Admin menu to completely delete User from DB
		int idUser = Integer.valueOf(request.getParameter("idUser"));
		UserManager.getInstance().delete(idUser);
		
		doGet(request, response);
	}

}
