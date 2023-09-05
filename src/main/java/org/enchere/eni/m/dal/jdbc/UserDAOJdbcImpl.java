package org.enchere.eni.m.dal.jdbc;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.UserDAO;

public class UserDAOJdbcImpl implements UserDAO {
	
	private static final int ADMIN = 1;
	private static final int STD_USER = 0;
	private static final String createUser = "INSERT INTO Users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String selectById = """
			SELECT * FROM Users WHERE idUser = ?
			JOIN Bids ON idUser = user
			JOIN ItemsSold ON idUser = user;
			""";

	@Override
	public void createUser(User newUser) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(createUser, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, newUser.getAlias());
			pStmt.setString(2, newUser.getSurname());
			pStmt.setString(3,  newUser.getFirstName());
			pStmt.setString(4, newUser.getEmail());
			pStmt.setString(5,  newUser.getPhone());
			pStmt.setString(6,  newUser.getStreet());
			pStmt.setString(7,  newUser.getZipCode());
			pStmt.setString(8, newUser.getCity());
			String encryptedPassword = "";
			try {
				encryptedPassword = encryptPassword(newUser.getPasswordUser(), "");
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Algorithm error when encrypting password");
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				System.out.println("KeySpec error when encrypting password");
				e.printStackTrace();
			}
			
			pStmt.setString(9, encryptedPassword);
	
			pStmt.setInt(10, newUser.getCredit());
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
	
	private String encryptPassword(String password, String wantedSalt) throws NoSuchAlgorithmException, InvalidKeySpecException {		
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		if (wantedSalt.equals("")) {
			random.nextBytes(salt);
		} else {
			for (int i = 0; i < wantedSalt.length(); i++) {
				salt[i] = Byte.valueOf(String.valueOf(wantedSalt.charAt(i)));
			}
		}
		
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 112);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		
		StringBuilder encryptedPassword = new StringBuilder();
		encryptedPassword.append(hash.toString());
		encryptedPassword.append(salt.toString());
		
		return encryptedPassword.toString();
	}
	
	public boolean checkPassword(String password, String encryptedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String subPassword = password.substring(0, password.length() - 14);
		String salt = password.substring(password.length() - 14, password.length());
		return encryptedPassword.equals(encryptPassword(subPassword, salt));
	}
	
	public User selectById(int idUser) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(selectById);
			pStmt.setInt(1, idUser);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		
		User user = null;
		
		
		
		return user;
	}

}
