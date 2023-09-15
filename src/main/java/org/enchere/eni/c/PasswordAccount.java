package org.enchere.eni.c;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.enchere.eni.m.bll.ErrorCodesBLL;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.User;

public class PasswordAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/forgottenPassword.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String alias = request.getParameter("alias");
		String email = request.getParameter("email");
		User user = UserManager.getInstance().selectByAlias(alias);
		BusinessException be = new BusinessException();

		if (user == null) {
			be.addErrorCode(ErrorCodesBLL.USER_DOESNT_EXIST);
			request.setAttribute("errorCodesList", be.getErrorCodeList());
			doGet(request, response);
		} else {
			if (!user.getEmail().equals(email)) {

				be.addErrorCode(ErrorCodesBLL.ALIAS_EMAIL_NOT_CORRESPONDING);
				request.setAttribute("errorCodesList", be.getErrorCodeList());
				doGet(request, response);

			} else {

				String newPassword = request.getParameter("passwordUser");
				user = UserManager.getInstance().selectById(user.getIdUser());
				user.setPasswordUser(newPassword);
				UserManager.getInstance().update(user);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accountConnection.jsp");
				rd.forward(request, response);

			}
		}

	}

}
