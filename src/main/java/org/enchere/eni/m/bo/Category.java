package org.enchere.eni.m.bo;

import java.util.ArrayList;
import java.util.List;

public class Category {

	public int idCategory;
	public String wording;
	private List <ItemSold> itemsSold = new ArrayList <ItemSold>();

	public Category() {
	}

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

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", wording=" + wording + ", itemsSold=" + itemsSold + "]";
	}


}
