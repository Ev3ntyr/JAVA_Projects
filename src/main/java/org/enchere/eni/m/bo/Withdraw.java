package org.enchere.eni.m.bo;

public class Withdraw {

	private String street;
	private String zipCode;
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

	public String getzipCode() {
		return zipCode;
	}

	public void setzipCode(String zipCode) {
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
		return "Withdraw [street=" + street + ", zipCode=" + zipCode + ", city=" + city + "]";
	}
	
	
}
