package org.enchere.eni.m.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	public int idCategory;
	public String wording;
	private List<Item> itemsSold = new ArrayList<Item>();

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

	public List<Item> getItemsSold() {
		return itemsSold;
	}

	public void setItemsSold(List<Item> itemsSold) {
		this.itemsSold = itemsSold;
	}

	// SPECIFIC BO METHODS
	public void addItemSold(Item itemSold) {
		itemsSold.add(itemSold);
	}

	// CONSTRUCTORS
	public Category() {
	}

	public Category(String wording) {
		this();
		this.wording = wording;
	}

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
