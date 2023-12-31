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
import org.enchere.eni.m.bll.ErrorCodesBLL;
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
		
		HttpSession session = request.getSession();
		
		int idUser = (int) session.getAttribute("idUser");
		User userConnected = UserManager.getInstance().selectById(idUser);
		request.setAttribute("userConnected", userConnected);
		
		// COLLECTING CURRENT ITEM INFO
		
		int idItem = 0;
		if (request.getParameter("idItem") != null) {
			try {
				idItem = Integer.valueOf(request.getParameter("idItem"));
			} catch (NumberFormatException nfe) {
				System.out.println("ERROR - Cannot get the requested item ID");
				nfe.printStackTrace();
			}
		} else {
			idItem = (int) request.getAttribute("idItem");
		}
		
		Item item = ItemManager.getInstance().selectById(idItem);

		Bid bid = BidManager.getInstance().selectMaxBid(item);
		request.setAttribute("bid", bid);
		
		
		
		// COLLECTING WITHDRAW AND CHANGING ITEM STATUS		
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
		
		int idCurrentBidder = 0;
		
		try {
			idCurrentBidder = Integer.valueOf(request.getParameter("currentBidder"));
		} catch (NumberFormatException nfe) {
			System.out.println("ERROR WHEN PARSING CURRENT BIDDER ID");
			nfe.printStackTrace();
		}
		
		BusinessException be = null;
		if (user.getCredit() < bidAmount) {
			be = new BusinessException(ErrorCodesBLL.UNSUFFICIENT_CREDIT_AMOUNT);
		}
		
		if (be != null) {
			request.setAttribute("errorCodesList", be.getErrorCodeList());
			
		} else {
			Bid bid = new Bid(bidDate, bidAmount, item, user);
			BidManager.getInstance().insert(bid);
			
			user.setCredit(user.getCredit() - bidAmount);
			UserManager.getInstance().update(user);
			
			if (idCurrentBidder != 0) {
				User currentBidder = UserManager.getInstance().selectById(idCurrentBidder);
				int currentBidAmount = 0;
				try {
					currentBidAmount = Integer.valueOf(request.getParameter("currentBidAmount"));
				} catch (NumberFormatException nfe) {
					System.out.println("ERROR WHEN PARSING CURRENT BID AMOUNT");
					nfe.printStackTrace();
				}
				currentBidder.setCredit(user.getCredit() + currentBidAmount);
				UserManager.getInstance().update(currentBidder);
			}
			
		}
		
		doGet(request, response);
	}

}
