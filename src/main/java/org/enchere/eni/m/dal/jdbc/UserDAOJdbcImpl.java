package org.enchere.eni.m.dal.jdbc;


import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.UserDAO;
import org.enchere.eni.m.security.BCrypt;

public class UserDAOJdbcImpl implements UserDAO {

	private static final int ADMIN = 1;
	private static final int STD_USER = 0;
	private static final int INITIAL_CREDIT = 100;

	
	private static final String INIT_TABLE = """
			DROP TABLE IF EXISTS Users;
			
			CREATE TABLE Users (
			    idUser INTEGER IDENTITY(1,1) NOT NULL,
			    alias VARCHAR(30) NOT NULL,
			    surname VARCHAR(30) NOT NULL,
			    firstName VARCHAR(30) NOT NULL,
			    email VARCHAR(50) NOT NULL,
			    phone VARCHAR(15),
			    street VARCHAR(30) NOT NULL,
			    zipCode VARCHAR(10) NOT NULL,
			    city VARCHAR(50) NOT NULL,
			    passwordUser VARCHAR(128) NOT NULL,
			    credit INTEGER NOT NULL,
			    isAdmin bit NOT NULL
			);
			ALTER TABLE Users ADD constraint user_pk PRIMARY KEY (idUser);
			""";
	
	private static final String CREATE_USER = """
			INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
			""";
	private static final String SELECT_BY_ID = """
			SELECT alias, surname, firstName, email, phone, street, zipCode,
			city, passwordUser, credit, isAdmin
			FROM USERS WHERE idUser = ?;
			""";
	private static final String SELECT_BY_EMAIL = """
			SELECT email FROM USERS WHERE email = ?;
			""";
	private static final String SELECT_BY_ALIAS = """
			SELECT alias FROM USERS WHERE alias = ?;
			""";

	@Override
	public void initDataset() {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			stmt.executeUpdate(INIT_TABLE);
			
		} catch(SQLException sqle) {
			System.out.println("ERROR WHEN CREATING TABLE Users");
			sqle.printStackTrace();
		}
		
		User u1 = new User("RogerTheRoro", "Smith", "Roger", "roger.smith@email.com", "01234567890", "123 Main Street", "12345", "New York", "IL0vNY123", 312, false);
		User u2 = new User("AliceInBorderlands", "Johnson", "Alice", "alice.johnson@email.com", "02345678901", "456 Elm Street", "23456", "Los Angeles", "lAV!bes", 153, false);
		User u3 = new User("DoudouDavid", "Fortin", "David", "david.fortin@email.com", "0761115598", "78 Avenue Montaigne", "75007", "Paris", "monMotdePasseEstTop", 1200, false);
		User u4 = new User("Soso", "Wilson", "Sophia", "sophia.wilson@email.com", "4567890123", "1010 Pine Road", "45678", "Houston", "123456", 0, false);
		User u5 = new User("admin", "admin", "admin", "admin@email.com", "0761144598", "admin street", "75000", "Paris", "admin", 600, true);
		
		createUser(u1);
		createUser(u2);
		createUser(u3);
		createUser(u4);
		createUser(u5);

	}
	
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
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
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
				user = new User(idUser, alias, surname, firstName, email, phone, street, zipCode, city, passwordUser,
						credit, isAdmin);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return user;
	}

	@Override
	public boolean checkPassword(String password, String encryptedPassword)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		// TODO Auto-generated method stub
		return false;
	}

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

}
