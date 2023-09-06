package org.enchere.eni.m.bll;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.DAOFactory;

public class UserManager {
	
	private static UserManager instance;
	
	private UserManager() {}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public void initDataset() {
		DAOFactory.getUserDAO().initDataset();
	}
	
	public void createUser(User newUser) {
		DAOFactory.getUserDAO().createUser(newUser);
	}
	
	public User selectById(int idUser) {
		return DAOFactory.getUserDAO().selectById(idUser);
	}
	
	public boolean checkPassword(String password, String hashedPassword) throws NoSuchAlgorithmException, NoSuchProviderException {
		return DAOFactory.getUserDAO().checkPassword(password, hashedPassword);
	}
}
