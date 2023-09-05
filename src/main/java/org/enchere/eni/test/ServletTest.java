package org.enchere.eni.test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;

import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.jdbc.ConnectionProvider;

/**
 * Servlet implementation class ServletTest
 */
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try (Connection cnx = ConnectionProvider.getConnection()){
			System.out.println("Connexion ok ");
		} catch (SQLException e) {
			System.out.println("echec");		
		}
		
		
		User u = new User(0, "alias", "surname", "firstname", "email", "0232", "street", "zip", "city", "admin", 0, false);
		System.out.println("u avt create : " + u.getPasswordUser());
		UserManager.getInstance().createUser(u);
		String pwd = u.getPasswordUser();
		

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPTest.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
