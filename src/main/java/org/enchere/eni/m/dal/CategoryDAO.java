package org.enchere.eni.m.dal;

import org.enchere.eni.m.bo.Category;

public interface CategoryDAO {
	
	void initDataset();
	void insert(Category newCategory);

}
