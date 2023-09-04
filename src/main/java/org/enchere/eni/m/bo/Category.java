package org.enchere.eni.m.bo;

import java.util.ArrayList;
import java.util.List;

public class Category {

	public int noCategory;
	public String libelle;
	private List <ItemSold> itemSold = new ArrayList <ItemSold>();

	public Category() {
		super();
	}

	public int getNoCategory() {
		return noCategory;
	}

	public void setNoCategory(int noCategory) {
		this.noCategory = noCategory;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Category [noCategory=" + noCategory + ", libelle=" + libelle + "]";
	}
	
	
	
}
