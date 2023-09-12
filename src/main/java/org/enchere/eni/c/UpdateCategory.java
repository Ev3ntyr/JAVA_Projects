package org.enchere.eni.c;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.enchere.eni.m.bll.CategoryManager;
import org.enchere.eni.m.bo.Category;

/**
 * Servlet implementation class UpdateCategory
 */
public class UpdateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String wording = request.getParameter("oldCategoryLabel");
		Category category = CategoryManager.getInstance().selectByWording(wording);
		
		CategoryManager.getInstance().update(category);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin/category");
	}

}
