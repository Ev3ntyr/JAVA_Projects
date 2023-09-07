package org.enchere.eni.m.bll;

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
		
		// Email duplicity check
		emailCheck(newUser.getEmail(), be);				
		//si une erreur lors de la validation -> Lève l'exception
		if(be.hasErreur()) {
			throw be;
		}
		
		// Alias duplicity check	
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
}
