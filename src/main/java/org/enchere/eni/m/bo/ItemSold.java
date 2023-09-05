package org.enchere.eni.m.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemSold {

	private int idItem;
	private String nameItem;
	private String descriptionItem;
	private LocalDate bidStartDate;
	private LocalDate bidEndDate;
	private int initialPrice;
	private int sellingPrice;
	private int stateItem;
	private User user;
	private Withdraw withdraw;
	private Category category;
	private List <Bid> bids = new ArrayList <Bid>();

	
	public Withdraw getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Withdraw withdraw) {
		this.withdraw = withdraw;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ItemSold() {
	}

	public int getIdItem() {
		return idItem;
	}

	public int getStateItem() {
		return stateItem;
	}

	public void setStateItem(int stateItem) {
		this.stateItem = stateItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}

	public String getDescriptionItem() {
		return descriptionItem;
	}

	public void setDescriptionItem(String descriptionItem) {
		this.descriptionItem = descriptionItem;
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

	public int getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ItemSold [idItem=" + idItem + ", nameItem=" + nameItem + ", descriptionItem=" + descriptionItem
				+ ", bidStartDate=" + bidStartDate + ", bidEndDate=" + bidEndDate + ", initialPrice=" + initialPrice
				+ ", sellingPrice=" + sellingPrice + ", stateItem=" + stateItem + ", user=" + user + ", withdraw="
				+ withdraw + ", category=" + category + ", bids=" + bids + "]";
	}

	

}
