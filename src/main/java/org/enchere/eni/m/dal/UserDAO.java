package org.enchere.eni.m.dal;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.enchere.eni.m.bo.User;

public interface UserDAO {
	
	void initDataset();
	void createUser(User newUser);
	User selectById(int idUser);
	boolean checkPassword (String password, String encryptedPassword) throws NoSuchAlgorithmException, NoSuchProviderException;
	boolean checkEmail (String email);
	boolean checkAlias (String alias);
}
