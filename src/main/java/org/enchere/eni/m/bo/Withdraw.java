package org.enchere.eni.m.bo;

import java.io.Serializable;

public class Withdraw implements Serializable {

	private static final long serialVersionUID = 1L;
	private Item itemSold;
	private String street;
	private String zipCode;
	private String city;
	
	
	// GETTERS AND SETTERS
	public Item getItemSold() {
		return itemSold;
	}
	public void setItemSold(Item itemSold) {
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
		this();
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	public Withdraw(Item item, String street, String zipCode, String city) {
		this(street, zipCode, city);
		this.itemSold = item;
	}
	
	
	// OVERRRIDEN METHODS
	@Override
	public String toString() {
		return "Withdraw [ street=" + street + ", zipCode=" + zipCode + ", city=" + city
				+ "]";
	}
	
	


}
