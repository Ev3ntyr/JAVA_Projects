package org.enchere.eni.m.bll;

import java.util.List;

import org.enchere.eni.m.bo.ItemSold;
import org.enchere.eni.m.dal.DAOFactory;

public class ItemSoldManager {

	private static ItemSoldManager instance;
	
	public static ItemSoldManager getInstance() {
		if(instance == null) {
			instance = new ItemSoldManager();
			
		}
		return instance;
	}
	private ItemSoldManager() {}
	
	public List<ItemSold> selectAll(){
		return DAOFactory.getItemSoldDAO().selectAll();
	}
	
	public void insert(ItemSold item) {
		DAOFactory.getItemSoldDAO().insert(item);
	}
}
