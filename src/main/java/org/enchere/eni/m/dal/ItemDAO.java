package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.Withdraw;

public interface ItemDAO {

	List<Item> selectAllByName(String itemName);
	List<Item> selectAll();
	void insert(Item item);
	void insertWithdraw (Withdraw withdraw);
	boolean hasWithdraw(Item item);
	Withdraw selectWithdraw(Item item);
	Item selectById (int idItem);
	void updateSellingPrice (Item item);
	void delete(int idItem);
	void update(Item item);
	void updateWithdraw(Withdraw withdraw);
	
}
