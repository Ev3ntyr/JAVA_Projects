package org.enchere.eni.m.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bid implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idBid;
	private LocalDate bidDate;
	private int bidAmount;
	private User user;
	private Item itemSold;
	
	// GETTERS AND SETTERS
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
	public Item getItemSold() {
		return itemSold;
	}
	public void setItemSold(Item itemSold) {
		this.itemSold = itemSold;
	}

	// CONSTRUCTORS
	public Bid() {}
	
	public Bid(LocalDate bidDate, int bidAmount, Item itemSold, User user) {
		this();
		this.bidDate = bidDate;
		this.bidAmount = bidAmount;
		this.itemSold = itemSold;
		this.user = user;
	}
	

	public Bid(int idBid, LocalDate bidDate, int bidAmount,Item itemSold,  User user) {
		this(bidDate, bidAmount, itemSold, user);
		this.idBid = idBid;
	
	}
	// OVERRIDEN METHODS
	@Override
	public String toString() {
		return "Bid [idBid=" + idBid + ", bidDate=" + bidDate + ", bidAmount=" + bidAmount
				+ "]";
	}

}
