package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.Item;

public interface ItemDAO {

	List<Item> selectAll();
	void insert(Item item);
}
