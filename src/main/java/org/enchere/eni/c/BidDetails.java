package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;

public class BidDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idItem = 0;
		if (request.getParameter("idItem") != null) {
			try {
				idItem = Integer.valueOf(request.getParameter("idItem"));
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR - Cannot get the requested item ID");
				nfe.printStackTrace();
			}
		} 
		
		Item item = ItemManager.getInstance().selectById(idItem);

		System.out.println(item);
		System.out.println(item.getWithdraw());

		request.setAttribute("item", item);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/bidDetails.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
