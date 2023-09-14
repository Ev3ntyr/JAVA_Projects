package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.enchere.eni.m.bll.BidManager;
import org.enchere.eni.m.bll.ErrorCodesBLL;
import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;

public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUser = Integer.valueOf(request.getParameter("idUser"));
		User user = UserManager.getInstance().selectById(idUser);
		
		// CHECK IF USER HAS PENDING SELLS
		List<Item> pendingSells = ItemManager.getInstance().selectAllOpenByUser(user);
		List<Bid> pendingBids = BidManager.getInstance().selectUserWinningBids(user);
		BusinessException be = new BusinessException();
		
		if (pendingSells.size() > 0) {
			be.addErrorCode(ErrorCodesBLL.ACCOUNT_DELETION_PENDING_SELLS);
			request.setAttribute("errorCodesList", be.getErrorCodeList());
			
			if (pendingBids.size() > 0) {
				be.addErrorCode(ErrorCodesBLL.ACCOUNT_DELETION_WINNING_BIDS);
				request.setAttribute("pendingBids", pendingBids);
			}
			
			request.setAttribute("pendingSells", pendingSells);
			
			request.setAttribute("user", user);
			request.setAttribute("errorCodesList", be.getErrorCodeList());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/userProfile.jsp");
			rd.forward(request, response);
			
		} else {
			
			if (pendingBids.size() > 0) {
				
				be.addErrorCode(ErrorCodesBLL.ACCOUNT_DELETION_WINNING_BIDS);
				request.setAttribute("errorCodesList", be.getErrorCodeList());
				request.setAttribute("pendingBids", pendingBids);
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/userProfile.jsp");
				rd.forward(request, response);
				
			} else {
				
				UserManager.getInstance().delete(idUser);
				
				RequestDispatcher rd = request.getRequestDispatcher("logout");
				rd.forward(request, response);
				
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Admin menu to completely delete User from DB
		int idUser = Integer.valueOf(request.getParameter("idUser"));
		User user = UserManager.getInstance().selectById(idUser);
		BidManager.getInstance().deleteUserBids(user);
		UserManager.getInstance().delete(idUser);
		
		doGet(request, response);
	}

}
