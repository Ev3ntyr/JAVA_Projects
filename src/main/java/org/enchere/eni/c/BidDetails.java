package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

import org.enchere.eni.m.bll.BidManager;
import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;

public class BidDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		// COLLECTING CURRENT ITEM INFO
		
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
		
		// COLLECTING WITHDRAW AND CHANGING ITEM STATUS
		
		System.out.println(item.getWithdraw());
		
		item.getStateItem();

		request.setAttribute("item", item);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/bidDetails.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		LocalDateTime bidDate = LocalDateTime.now();		
		int bidAmount = Integer.valueOf(request.getParameter("bidAmount"));
		Item item = ItemManager.getInstance().selectById(Integer.valueOf(request.getParameter("idItem")));
		int idUser = (int)session.getAttribute("idUser");
		User user = UserManager.getInstance().selectById(idUser);
		
		Bid bid = new Bid(bidDate, bidAmount, item, user);
		System.out.println("created bid in servlet : " + bid);
		BidManager.getInstance().insert(bid);
		
		doGet(request, response);
	}

}
