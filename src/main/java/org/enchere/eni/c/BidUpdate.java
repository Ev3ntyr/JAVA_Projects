package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import org.enchere.eni.m.bll.CategoryManager;
import org.enchere.eni.m.bll.ErrorCodesBLL;
import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;

public class BidUpdate extends HttpServlet {
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

		Item item = ItemManager.getInstance().selectById(idItem);
		request.setAttribute("item", item);

		// GETTING CATEGORY LIST FROM DATABASE
		List<Category> listCategory = CategoryManager.getInstance().select();
		request.setAttribute("listCategory", listCategory);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/bidUpdate.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("idUser") == null) {

			RequestDispatcher rd = request.getRequestDispatcher("home");
			rd.forward(request, response);

		} else {

			int idCurrentUser = (int) session.getAttribute("idUser");
			User currentUser = UserManager.getInstance().selectById(idCurrentUser);

			int idItem = 0;
			try {
				idItem = Integer.valueOf(request.getParameter("itemID"));
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}

			Item item = ItemManager.getInstance().selectById(idItem);

			// GETTING FORM PARAMETERS FROM JSP
			String enteredName = request.getParameter("nameItem");
			String enteredDescript = request.getParameter("descriptionItem");
			int enteredCategory = Integer.valueOf(request.getParameter("category"));
			Category currentCategory = CategoryManager.getInstance().selectById(enteredCategory);
			// TODO Récupérer la photo et la stocker
			int enteredInitialPrice = Integer.valueOf(request.getParameter("initialPrice"));
			String enteredBidStartDate = request.getParameter("bidStartDate");
			String enteredBidEndDate = request.getParameter("bidEndDate");

			// CHECKING FORMAT VALUE TO THROW BUSINESS EXCEPTION

			LocalDateTime bidStartDate = null;
			try {
				bidStartDate = LocalDateTime.parse(enteredBidStartDate);
			} catch (DateTimeException e) {
				e.printStackTrace();
				BusinessException be = new BusinessException(ErrorCodesBLL.FORMAT_BID_START_DATE_ERROR);
			}

			LocalDateTime bidEndDate = null;
			try {
				bidEndDate = LocalDateTime.parse(enteredBidEndDate);
			} catch (DateTimeException e) {
				e.printStackTrace();
				BusinessException be = new BusinessException(ErrorCodesBLL.FORMAT_BID_END_DATE_ERROR);
			} finally {
				if (bidStartDate.isAfter(bidEndDate) || bidEndDate.isEqual(bidStartDate)) {
					BusinessException be = new BusinessException(ErrorCodesBLL.BID_END_DATE_ERROR);
				}
			}

			// CHECKING BID START DATE FOR ITEM STATUT
			int statut = 0;
			if (bidStartDate.isBefore(LocalDateTime.now())) {
				statut = 1;
			}

			// CREATING UPDATED ITEM
			Item updatedItem = new Item(enteredName, enteredDescript, bidStartDate, bidEndDate, enteredInitialPrice, 0,
					statut, currentUser, currentCategory);
			updatedItem.setIdItem(idItem);

			ItemManager.getInstance().update(updatedItem);

			// COMPARING USER ADRESS VS FORM WITHDRAW ADRESSE TO CREATE A NEW WITHDRAW OR
			// NOT

			String enteredStreet = request.getParameter("street");
			String enteredZipCode = request.getParameter("zipCode");
			String enteredCity = request.getParameter("city");

			if (ItemManager.getInstance().hasWithdraw(item)) {
				if (!(enteredStreet.equalsIgnoreCase(item.getWithdraw().getStreet()))
						|| !(enteredCity.equalsIgnoreCase(item.getWithdraw().getCity()))
						|| !(enteredZipCode.equalsIgnoreCase(item.getWithdraw().getZipCode()))) {
					Withdraw updatedWithdraw = new Withdraw(updatedItem, enteredStreet, enteredZipCode, enteredCity);
					ItemManager.getInstance().updateWithdraw(updatedWithdraw);
				}
			} else {
				if (!(enteredStreet.equalsIgnoreCase(currentUser.getStreet()))
						|| !(enteredCity.equalsIgnoreCase(currentUser.getCity()))
						|| !(enteredZipCode.equalsIgnoreCase(currentUser.getZipCode()))) {
					Withdraw newWithdraw = new Withdraw(updatedItem, enteredStreet, enteredZipCode, enteredCity);
					ItemManager.getInstance().insertWithdraw(newWithdraw);
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher("home");
			rd.forward(request, response);
		}

	}

}
