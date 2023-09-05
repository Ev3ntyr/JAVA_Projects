package org.enchere.eni.m.bo;

import java.time.LocalDate;

public class ItemSold {

	private int idItem;
	private String nameItem;
	private String description;
	private LocalDate bidStartDate;
	private LocalDate bidEndDate;
	private int initialPrice;
	private int sellingPrice;
	private String statePrice; // est ce que c'est pour etatVente du diagramme ? Est ce que je l'ajoute sur SQL ?


	public ItemSold() {
	}

	public int getidItem() {
		return idItem;
	}

	public void setidItem(int idItem) {
		this.idItem = idItem;
	}

	public String getnameItem() {
		return nameItem;
	}

	public void setnameItem(String nameItem) {
		this.nameItem = nameItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBidStartDate() {
		return bidStartDate;
	}

	public void setBidStartDate(LocalDate bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	public LocalDate getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(LocalDate bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public int getinitialPrice() {
		return initialPrice;
	}

	public void setinitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}

	public int getsellingPrice() {
		return sellingPrice;
	}

	public void setsellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getStatePrice() {
		return statePrice;
	}

	public void setStatePrice(String statePrice) {
		this.statePrice = statePrice;
	}

	@Override
	public String toString() {
		return "ItemSold [idItem=" + idItem + ", nameItem=" + nameItem + ", description=" + description
				+ ", bidStartDate=" + bidStartDate + ", bidEndDate=" + bidEndDate + ", initialPrice=" + initialPrice
				+ ", sellingPrice=" + sellingPrice + ", statePrice=" + statePrice + "]";
	}
	
	
}
