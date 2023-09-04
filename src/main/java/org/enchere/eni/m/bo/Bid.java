package org.enchere.eni.m.bo;

import java.time.LocalDate;

public class Bid {

	private LocalDate bidDate;
	private int bidAmount;
	private User user;
	private ItemSold itemSold;

	public Bid() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ItemSold getItemSold() {
		return itemSold;
	}

	public void setItemSold(ItemSold itemSold) {
		this.itemSold = itemSold;
	}

	public LocalDate getbidDate() {
		return bidDate;
	}

	public void setbidDate(LocalDate bidDate) {
		this.bidDate = bidDate;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	@Override
	public String toString() {
		return "Bid [bidDate=" + bidDate + ", bidAmount=" + bidAmount + "]";
	}

}
