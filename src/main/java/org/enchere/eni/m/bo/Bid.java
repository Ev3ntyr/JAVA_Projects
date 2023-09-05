package org.enchere.eni.m.bo;

import java.time.LocalDate;

public class Bid {

	private int idBid;
	private LocalDate bidDate;
	private int bidAmount;
	private User user;
	private ItemSold itemSold;

	public Bid() {
	}

	public int getIdBid() {
		return idBid;
	}

	public void setIdBid(int idBid) {
		this.idBid = idBid;
	}

	public LocalDate getBidDate() {
		return bidDate;
	}

	public void setBidDate(LocalDate bidDate) {
		this.bidDate = bidDate;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
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

	@Override
	public String toString() {
		return "Bid [idBid=" + idBid + ", bidDate=" + bidDate + ", bidAmount=" + bidAmount + ", user=" + user
				+ ", itemSold=" + itemSold + "]";
	}



}
