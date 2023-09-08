package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.Withdraw;

public interface ItemDAO {

	List<Item> selectAll();
	void insert(Item item);
	void insertWithdraw (Withdraw withdraw);
	boolean hasWithdraw(Item item);
	Withdraw selectWithdraw(Item item);
}
