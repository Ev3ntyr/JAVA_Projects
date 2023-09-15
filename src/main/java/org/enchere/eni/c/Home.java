package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.enchere.eni.m.bll.CategoryManager;
import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.Item;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getAttribute("listItem") != null) {
			@SuppressWarnings("unchecked")
			List<Item> listItem = (List<Item>) request.getAttribute("listItem");
			if (listItem.size() <= 0) {
				listItem = ItemManager.getInstance().selectAll();
				request.setAttribute("listItem", listItem);
			}
		} else {
			List<Item> listItem = ItemManager.getInstance().selectAll();
			request.setAttribute("listItem", listItem);
		}

		List<Category> listCategory = CategoryManager.getInstance().select();
		request.setAttribute("listCategory", listCategory);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String itemName = request.getParameter("itemName");

		List<Item> listItem = ItemManager.getInstance().selectAllByName(itemName);
		request.setAttribute("listItem", listItem);
		doGet(request, response);

	}

}
