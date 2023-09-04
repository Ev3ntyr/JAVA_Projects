package org.enchere.eni.test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
System.out.println("echec");		}
		
		
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
