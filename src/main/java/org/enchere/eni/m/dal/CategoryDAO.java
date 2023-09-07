package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.Category;

public interface CategoryDAO {
	
	void insert(Category newCategory);

	List<Category> select();
}
