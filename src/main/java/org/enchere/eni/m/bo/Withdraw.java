package org.enchere.eni.m.bo;

import java.io.Serializable;

public class Withdraw implements Serializable {

	private static final long serialVersionUID = 1L;
	private ItemSold itemSold;
	private String street;
	private String zipCode;
	private String city;
	
	
	// GETTERS AND SETTERS
	public ItemSold getItemSold() {
		return itemSold;
	}
	public void setItemSold(ItemSold itemSold) {
		this.itemSold = itemSold;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	// CONSTRUCTORS
	public Withdraw() {}
	
	
	public Withdraw(String street, String zipCode, String city) {
		super();
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}
	// OVERRRIDEN METHODS
	@Override
	public String toString() {
		return "Withdraw [itemSold=" + itemSold + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city
				+ "]";
	}
	
	


}
