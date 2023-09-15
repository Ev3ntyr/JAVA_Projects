package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.enchere.eni.m.bll.ItemManager;

public class BidDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idItem = 0;

		try {
			idItem = Integer.valueOf(request.getParameter("idItem"));
		} catch (NumberFormatException nfe) {
			System.out.println("ERROR WHEN PARSING idItem FROM REQUEST");
			nfe.printStackTrace();
		}

		ItemManager.getInstance().delete(idItem);

		RequestDispatcher rd = request.getRequestDispatcher("home");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
