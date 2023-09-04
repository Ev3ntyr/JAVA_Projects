package org.enchere.eni.m.bo;

import java.time.LocalDate;

public class ItemSold {

	private int noArticle;
	private String nameArticle;
	private String description;
	private LocalDate bidStartDate;
	private LocalDate bidEndDate;
	private int pricing;
	private int soldPrice;
	private String statePrice;


	public ItemSold() {
		super();
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNameArticle() {
		return nameArticle;
	}

	public void setNameArticle(String nameArticle) {
		this.nameArticle = nameArticle;
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

	public int getPricing() {
		return pricing;
	}

	public void setPricing(int pricing) {
		this.pricing = pricing;
	}

	public int getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(int soldPrice) {
		this.soldPrice = soldPrice;
	}

	public String getStatePrice() {
		return statePrice;
	}

	public void setStatePrice(String statePrice) {
		this.statePrice = statePrice;
	}

	@Override
	public String toString() {
		return "ItemSold [noArticle=" + noArticle + ", nameArticle=" + nameArticle + ", description=" + description
				+ ", bidStartDate=" + bidStartDate + ", bidEndDate=" + bidEndDate + ", pricing=" + pricing
				+ ", soldPrice=" + soldPrice + ", statePrice=" + statePrice + "]";
	}
	
	
}
