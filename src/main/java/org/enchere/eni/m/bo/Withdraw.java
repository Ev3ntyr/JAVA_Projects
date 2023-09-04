package org.enchere.eni.m.bo;

public class Withdraw {

	private String street;
	private int postalCode;
	private String city;
	


	public Withdraw() {
		super();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Withdraw [street=" + street + ", postalCode=" + postalCode + ", city=" + city + "]";
	}
	
	
}
