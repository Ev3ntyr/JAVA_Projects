package org.enchere.eni.m.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	public int idCategory;
	public String wording;
	private List <ItemSold> itemsSold = new ArrayList <ItemSold>();
	
	// GETTERS AND SETTERS
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getWording() {
		return wording;
	}
	public void setWording(String wording) {
		this.wording = wording;
	}
	public List<ItemSold> getItemsSold() {
		return itemsSold;
	}
	public void setItemsSold(List<ItemSold> itemsSold) {
		this.itemsSold = itemsSold;
	}
	
	// SPECIFIC BO METHODS
	public void addItemSold(ItemSold itemSold) {
		itemsSold.add(itemSold);
	}
	
	
	// CONSTRUCTORS
	public Category() {}
	
	
	
	public Category(int idCategory, String wording) {
		super();
		this.idCategory = idCategory;
		this.wording = wording;
	}
	// OVERRIDEN METHODS
	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", wording=" + wording + ", itemsSold=" + itemsSold + "]";
	}

	

}
