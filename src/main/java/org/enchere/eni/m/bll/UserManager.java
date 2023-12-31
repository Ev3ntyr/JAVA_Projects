package org.enchere.eni.m.bll;

import java.util.List;

import org.enchere.eni.c.BusinessException;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.DAOFactory;

public class UserManager {

	private static UserManager instance;

	private UserManager() {
	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}


	public void createUser(User newUser) throws BusinessException {
		
		BusinessException be = new BusinessException();
		
		// EMAIL DUPLICITY CHECK
		emailCheck(newUser.getEmail(), be);				
		
		// ALIAS DUPLICITY CHECK	
		aliasCheck(newUser.getAlias(), be);
		if(be.hasErreur()) {
			throw be;
		}	
		
		DAOFactory.getUserDAO().createUser(newUser);
		
	}

	public User selectById(int idUser) {
		return DAOFactory.getUserDAO().selectById(idUser);
	}

	public boolean checkPassword(String password, String hashedPassword) {
		return DAOFactory.getUserDAO().checkPassword(password, hashedPassword);
	}

	private void emailCheck(String email, BusinessException be) {
		if (DAOFactory.getUserDAO().checkEmail(email)) {			
			be.addErrorCode(ErrorCodesBLL.EMAIL_NOT_UNIQUE_ERROR);
		}
	}
	
	private void aliasCheck(String alias, BusinessException be) {
		if (DAOFactory.getUserDAO().checkAlias(alias)) {
			be.addErrorCode(ErrorCodesBLL.ALIAS_NOT_UNIQUE_ERROR);
		}
	}
	
	public User selectByAlias(String alias) {
		return DAOFactory.getUserDAO().selectByAlias(alias);
	}
	
	public void update(User user) {
		DAOFactory.getUserDAO().update(user);
	}
	
	public void delete(int idUser) {
		DAOFactory.getUserDAO().delete(idUser);
	}
	
	public void adminDelete(int idUser) {
		DAOFactory.getUserDAO().adminDelete(idUser);
	}
	
	public void deactivate(int idUser) {
		DAOFactory.getUserDAO().deactivate(idUser);
	}
	
	public List<User> selectAll() {
		return DAOFactory.getUserDAO().selectAll();
	}

}
