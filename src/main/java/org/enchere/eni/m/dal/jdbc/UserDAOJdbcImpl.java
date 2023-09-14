package org.enchere.eni.m.dal.jdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.UserDAO;
import org.enchere.eni.m.security.BCrypt;

public class UserDAOJdbcImpl implements UserDAO {

	private static final int ADMIN = 1;
	private static final int STD_USER = 0;
	private static final int INITIAL_CREDIT = 100;
	
	private static final String CREATE_USER = """
			INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1);
			""";

	@Override
	public void createUser(User newUser) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, newUser.getAlias());
			pStmt.setString(2, newUser.getSurname());
			pStmt.setString(3, newUser.getFirstName());
			pStmt.setString(4, newUser.getEmail());
			pStmt.setString(5, newUser.getPhone());
			pStmt.setString(6, newUser.getStreet());
			pStmt.setString(7, newUser.getZipCode());
			pStmt.setString(8, newUser.getCity());

			String password = newUser.getPasswordUser();
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			pStmt.setString(9, hashedPassword);
			
			if (newUser.getCredit() == 0) {
				pStmt.setInt(10, INITIAL_CREDIT);
			} else {
				pStmt.setInt(10, newUser.getCredit());
			}
			
			pStmt.setInt(11, STD_USER);

			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				newUser.setIdUser(rs.getInt(1));
			}

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN INSERT User INTO DATABASE");
			sqle.printStackTrace();
		}

	}

	private static final String SELECT_BY_ID = """
			SELECT alias, surname, firstName, email, phone, street, zipCode,
			city, passwordUser, credit, isAdmin, isActive
			FROM USERS WHERE idUser = ?;
			""";
	public User selectById(int idUser) {

		User user = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, idUser);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				String alias = rs.getString("alias");
				String surname = rs.getString("surname");
				String firstName = rs.getString("firstName");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String street = rs.getString("street");
				String zipCode = rs.getString("zipCode");
				String city = rs.getString("city");
				String passwordUser = rs.getString("passwordUser");
				int credit = rs.getInt("credit");
				boolean isAdmin = rs.getBoolean("isAdmin");
				boolean isActive = rs.getBoolean("isActive");
				user = new User(idUser, alias, surname, firstName, email, phone, street, zipCode, city, passwordUser,
						credit, isAdmin, isActive);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean checkPassword(String password, String encryptedPassword) {
		return BCrypt.checkpw(password, encryptedPassword);
	}

	private static final String SELECT_BY_EMAIL = """
			SELECT email FROM USERS WHERE email = ?;
			""";
	@Override
	public boolean checkEmail(String email) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			pStmt.setString(1, email);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkAlias(String alias) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ALIAS);
			pStmt.setString(1, alias);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	
	private static final String SELECT_BY_ALIAS = """
			SELECT idUser, alias, email, passwordUser, isActive FROM USERS WHERE alias = ?;
			""";
	@Override
	public User selectByAlias(String alias) {
		User u = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ALIAS);
			pStmt.setString(1, alias);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				u = new User();
				u.setIdUser(rs.getInt("idUser"));
				u.setAlias(alias);
				u.setPasswordUser(rs.getString("passwordUser"));
				u.setEmail(rs.getString("email"));
				u.setIsActive(rs.getBoolean("isActive"));
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING USER WITH ALIAS=" + alias);
			sqle.printStackTrace();
		}
		
		return u;
	}
	
	private static final String UPDATE = """
			UPDATE USERS
			SET alias = ?,
			surname = ?,
			firstName = ?,
			email = ?,
			phone = ?,
			street = ?,
			zipCode = ?,
			city = ?,
			passwordUser = ?,
			credit = ?, 
			isActive = ?
			WHERE idUser = ?;
			""";
	@Override
	public void update(User user) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, user.getAlias());
			pStmt.setString(2,  user.getSurname());
			pStmt.setString(3, user.getFirstName());
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getPhone());
			pStmt.setString(6,  user.getStreet());
			pStmt.setString(7,  user.getZipCode());
			pStmt.setString(8,  user.getCity());
			String userPassword = user.getPasswordUser();
			if (userPassword.length() <= 20) {
				userPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
			}
			pStmt.setString(9,  userPassword);
			pStmt.setInt(10, user.getCredit());
			pStmt.setInt(11, user.getIsActive() == true ? 1 : 0);
			pStmt.setInt(12,  user.getIdUser());
			
			pStmt.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN UPDATING USER");
			sqle.printStackTrace();
		}
		
	}
	
	private static final String DELETE = """
			DELETE FROM USERS WHERE idUser = ?;
			""";
	
	@Override
	public void delete(int idUser) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, idUser);
			
			pStmt.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN DELETING USER id=" + idUser);
			sqle.printStackTrace();
		}
		
	}
	
	private static final String DEACTIVATE = """
			UPDATE USERS 
			SET isActive = 0
			WHERE idUser = ?;
			""";
	@Override
	public void deactivate(int idUser) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(DEACTIVATE);
			pStmt.setInt(1, idUser);
			
			pStmt.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN DEACTIVATING USER id=" + idUser);
			sqle.printStackTrace();
		}
	}
	
	private static final String SELECT_ALL = """
			SELECT idUser FROM USERS;
			""";
	@Override
	public List<User> selectAll() {
		
		List<User> users = new ArrayList<User>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				User user = selectById(rs.getInt("idUser"));
				users.add(user);
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING ALL USERS");
			sqle.printStackTrace();
		}
		
		
		return users;
	}
  
}
