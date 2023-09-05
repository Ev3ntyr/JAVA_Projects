package org.enchere.eni.m.dal.jdbc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.UserDAO;
import org.enchere.eni.m.security.BCrypt;

public class UserDAOJdbcImpl implements UserDAO {
	
	private static final int ADMIN = 1;
	private static final int STD_USER = 0;
	private static final int INITIAL_CREDIT = 100;
	
	private static final String CREATE_USER = """
			INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
			""";
	private static final String SELECT_BY_ID = """
			SELECT alias, surname, firstName, email, phone, street, zipCode,
			city, passwordUser, credit, isAdmin
			FROM USERS WHERE idUser = ?;
			""";

	@Override
	public void createUser(User newUser) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, newUser.getAlias());
			pStmt.setString(2, newUser.getSurname());
			pStmt.setString(3,  newUser.getFirstName());
			pStmt.setString(4, newUser.getEmail());
			pStmt.setString(5,  newUser.getPhone());
			pStmt.setString(6,  newUser.getStreet());
			pStmt.setString(7,  newUser.getZipCode());
			pStmt.setString(8, newUser.getCity());
			
			String password = newUser.getPasswordUser();
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
			pStmt.setString(9, hashedPassword);
		
			pStmt.setInt(10, INITIAL_CREDIT);
			pStmt.setInt(11, STD_USER);
			
			pStmt.executeUpdate();
			
			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				newUser.setIdUser(rs.getInt(1));
			}
			
		} catch (SQLException sqle) {
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
				user = new User(idUser, alias, surname, firstName, email, phone, street, zipCode, city, passwordUser, credit, isAdmin);
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

}
