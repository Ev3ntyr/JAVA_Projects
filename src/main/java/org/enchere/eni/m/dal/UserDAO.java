package org.enchere.eni.m.dal;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.enchere.eni.m.bo.User;

public interface UserDAO {
	
	void createUser(User newUser);
	User selectById(int idUser);
	boolean checkPassword (String password, String encryptedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
