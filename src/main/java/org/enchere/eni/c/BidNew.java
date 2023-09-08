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

/**
 * Servlet implementation class AuctionNew
 */
public class BidNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("idUser");
		
		User user = UserManager.getInstance().selectById(idUser);
		
		request.setAttribute("user", user);
		
		List<Category> listCategory = CategoryManager.getInstance().select();
		request.setAttribute("listCategory", listCategory);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/bidNew.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int idCurrentUser = (int) session.getAttribute("idUser");
		User currentUser = UserManager.getInstance().selectById(idCurrentUser);
		
		String enteredName = request.getParameter("nameItem");
		String enteredDescript = request.getParameter("descriptionItem");
		int enteredCategory = Integer.valueOf(request.getParameter("category"));
		System.out.println("catégorie :" + enteredCategory);
		//TODO Récupérer la photo et la stocker
		int enteredInitialPrice = Integer.valueOf(request.getParameter("initialPrice"));
		String enteredBidStartDate = request.getParameter("bidStartDate");
		String enteredBidEndDate = request.getParameter("bidEndDate");


		// On transforme les valeurs dans le bon format			
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
		System.out.println(bidStartDate);
		
		//Verification conformité des dates et attribution du statut 0 ou 1
		int statut = 0;
		if (bidStartDate.isEqual(LocalDateTime.now()) ) {
			statut = 1;
		}		

		// On crée l'item
		
		Item newItem = new Item(enteredName, enteredDescript, bidStartDate, bidEndDate, enteredInitialPrice, 0, statut, currentUser, enteredCategory);
		System.out.println(newItem);
		
		try {
			ItemManager.getInstance().insert(newItem);		
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorCodesList", e.getErrorCodeList());
			doGet(request, response);
		}
		
		// Vérification de la concordance entre l'adresse saisie en formulaire et celle du user
		String enteredStreet = request.getParameter("street");
		String enteredZipCode = request.getParameter("zipCode");
		String enteredCity = request.getParameter("city");
		
		
		if (!(enteredStreet.equalsIgnoreCase(currentUser.getStreet())) 
			|| !(enteredCity.equalsIgnoreCase(currentUser.getCity())) 
			|| !(enteredZipCode.equalsIgnoreCase(currentUser.getZipCode()))) {
			Withdraw newWithdraw = new Withdraw(newItem,enteredStreet, enteredZipCode, enteredCity);
			System.out.println(newWithdraw);
			ItemManager.getInstance().insertWithdraw(newWithdraw);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("home");
		rd.forward(request, response);
	}

}
