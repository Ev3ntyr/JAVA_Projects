package org.enchere.eni.m.bll;

import java.util.List;

import org.enchere.eni.c.BusinessException;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.Withdraw;
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
	
	public List<Item> selectAllByName(String itemName){
		return DAOFactory.getItemSoldDAO().selectAllByName(itemName);
	}
	
	public List<Item> selectAll(){
		return DAOFactory.getItemSoldDAO().selectAll();
	}
	
	public Item selectById(int idItem) {
		return DAOFactory.getItemSoldDAO().selectById(idItem);
	}
	
	public void insert(Item item) throws BusinessException{
		DAOFactory.getItemSoldDAO().insert(item);
	}
	
	public void insertWithdraw(Withdraw withdraw) {
		DAOFactory.getItemSoldDAO().insertWithdraw(withdraw);
	}
	
	public Withdraw selectWithdraw(Item item) {
		return DAOFactory.getItemSoldDAO().selectWithdraw(item);
	}
	
	public boolean hasWithdraw(Item item) {
		return DAOFactory.getItemSoldDAO().hasWithdraw(item);
	}
}
