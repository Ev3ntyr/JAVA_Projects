package org.enchere.eni.m.bll;

import org.enchere.eni.m.dal.DAOFactory;

public class ApplicationManager {

	private static ApplicationManager instance;

	public static ApplicationManager getInstance() {
		if (instance == null) {
			instance = new ApplicationManager();
		}
		return instance;
	}

	private ApplicationManager() {}

	public void initDB() {
		DAOFactory.getApplicationDAO().initDB();
	}

	public void initDataset() {
		DAOFactory.getApplicationDAO().initDataset();
	}
}
