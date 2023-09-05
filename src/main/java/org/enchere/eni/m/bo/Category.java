package org.enchere.eni.m.bo;

import java.util.ArrayList;
import java.util.List;

public class Category {

	public int idCategory;
	public String wording;
	private List <ItemSold> itemSold = new ArrayList <ItemSold>();

	public Category() {
		super();
	}

	public int getidCategory() {
		return idCategory;
	}

	public void setidCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getLibelle() {
		return wording;
	}

	public void setLibelle(String libelle) {
		this.wording = libelle;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", libelle=" + wording + "]";
	}
	
}
