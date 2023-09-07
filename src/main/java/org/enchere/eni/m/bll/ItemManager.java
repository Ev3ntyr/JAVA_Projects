package org.enchere.eni.m.bll;

import java.util.List;

import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.dal.DAOFactory;

public class ItemManager {

	private static ItemManager instance;
	
	public static ItemManager getInstance() {
		if(instance == null) {
			instance = new ItemManager();
			
		}
		return instance;
	}
	private ItemManager() {}
	
	public List<Item> selectAll(){
		return DAOFactory.getItemSoldDAO().selectAll();
	}
	
	public void insert(Item item) {
		DAOFactory.getItemSoldDAO().insert(item);
	}
}
