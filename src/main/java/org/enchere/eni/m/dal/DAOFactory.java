package org.enchere.eni.m.dal;

import org.enchere.eni.m.dal.jdbc.BidDAOJdbcImpl;
import org.enchere.eni.m.dal.jdbc.UserDAOJdbcImpl;

public class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOJdbcImpl();
	}
	
	public static BidDAO getBidDAO() {
		return new BidDAOJdbcImpl();
	}
}
