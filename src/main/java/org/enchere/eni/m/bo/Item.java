package org.enchere.eni.m.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idItem;
	private String nameItem;
	private String descriptionItem;
	private LocalDateTime bidStartDate;
	private LocalDateTime bidEndDate;
	private int initialPrice;
	private int sellingPrice;
	private int stateItem;
	private User user;
	private Withdraw withdraw;
	private Category category;
	private List<Bid> bids = new ArrayList<Bid>();

	// GETTERS AND SETTERS
	public int getIdItem() {
		return idItem;
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

	public LocalDateTime getBidStartDate() {
		return bidStartDate;
	}

	public void setBidStartDate(LocalDateTime bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	public LocalDateTime getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(LocalDateTime bidEndDate) {
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

	public int getStateItem() {
		LocalDateTime today = LocalDateTime.now();
		if (bidStartDate.isBefore(today) && (bidEndDate.isAfter(today))) {
			stateItem = 1;
		} else if (bidEndDate.isBefore(today)) {
			stateItem = 2;
		} else {
			stateItem = 0;
		}
		return stateItem;
	}

	public void setStateItem(int stateItem) {
		this.stateItem = stateItem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Withdraw getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Withdraw withdraw) {
		this.withdraw = withdraw;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	// SPECIFIC BO METHODS
	public void addBid(Bid bid) {
		bids.add(bid);
	}

	// CONSTRUCTORS
	public Item() {
	}

	public Item(String nameItem, String descriptionItem, LocalDateTime bidStartDate, LocalDateTime bidEndDate,
			int initialPrice, int sellingPrice, int stateItem, User user, Category category) {
		this();
		this.nameItem = nameItem;
		this.descriptionItem = descriptionItem;
		this.bidStartDate = bidStartDate;
		this.bidEndDate = bidEndDate;
		this.initialPrice = initialPrice;
		this.sellingPrice = sellingPrice;
		this.stateItem = stateItem;
		this.user = user;
		this.category = category;
	}

	public Item(int idItem, String nameItem, String descriptionItem, LocalDateTime bidStartDate,
			LocalDateTime bidEndDate, int initialPrice, int sellingPrice, int stateItem, User user, Withdraw withdraw,
			Category category) {
		this(nameItem, descriptionItem, bidStartDate, bidEndDate, initialPrice, sellingPrice, stateItem, user,
				category);
		this.idItem = idItem;
		this.withdraw = withdraw;
	}

	// OVERRIDEN METHODS
	@Override
	public String toString() {
		return "ItemSold [idItem=" + idItem + ", nameItem=" + nameItem + ", descriptionItem=" + descriptionItem
				+ ", bidStartDate=" + bidStartDate + ", bidEndDate=" + bidEndDate + ", initialPrice=" + initialPrice
				+ ", sellingPrice=" + sellingPrice + ", stateItem=" + stateItem + ", user=" + user + ", withdraw="
				+ withdraw + ", category=" + category + ", bids=" + bids + "]";
	}

}
