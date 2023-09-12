package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.Category;

public interface CategoryDAO {
	
	void insert(Category newCategory);

	List<Category> select();
	Category selectById(int idCategory);
	Category selectByWording(String wording);
	void update(Category category);
	void deleteById(int idCategory);
}
