package org.enchere.eni.m.bo;

public class Withdraw {

	private ItemSold itemSold;
	private String street;
	private String zipCode;
	private String city;
	


	public Withdraw() {
	}



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
	
	@Override
	public String toString() {
		return "Withdraw [itemSold=" + itemSold + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city
				+ "]";
	}


}
