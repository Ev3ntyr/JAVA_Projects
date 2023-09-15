package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.enchere.eni.m.bll.CategoryManager;

public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idCategory = 0;
		try {
			idCategory = Integer.valueOf(request.getParameter("idCategory"));
		} catch (NumberFormatException nfe) {
			System.out.println("ERROR WHEN GETTING ID PARAMETER");
			nfe.printStackTrace();
		}

		CategoryManager.getInstance().deleteById(idCategory);

		RequestDispatcher rd = request.getRequestDispatcher("/admin/categories");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
