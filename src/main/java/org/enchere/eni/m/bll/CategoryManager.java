package org.enchere.eni.m.bll;

import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.dal.DAOFactory;

public class CategoryManager {
	
	private static CategoryManager instance;
	
	private CategoryManager() {}
	
	public static CategoryManager getInstance() {
		if (instance == null) {
			instance = new CategoryManager();
		}
		return instance;
	}

	public void insertCategory(Category newCategory) {
		DAOFactory.getCategoryDAO().insert(newCategory);
	}

}
