package org.enchere.eni.m.bll;

import java.util.List;

import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.dal.DAOFactory;

public class CategoryManager {

	private static CategoryManager instance;

	private CategoryManager() {
	}

	public static CategoryManager getInstance() {
		if (instance == null) {
			instance = new CategoryManager();
		}
		return instance;
	}

	public void insertCategory(Category newCategory) {
		DAOFactory.getCategoryDAO().insert(newCategory);
	}

	public List<Category> select() {
		return DAOFactory.getCategoryDAO().select();
	}

	public Category selectById(int idCategory) {
		return DAOFactory.getCategoryDAO().selectById(idCategory);
	}

	public Category selectByWording(String wording) {
		return DAOFactory.getCategoryDAO().selectByWording(wording);
	}

	public void update(Category category) {
		DAOFactory.getCategoryDAO().update(category);
	}

	public void deleteById(int idCategory) {
		DAOFactory.getCategoryDAO().deleteById(idCategory);
	}

}
