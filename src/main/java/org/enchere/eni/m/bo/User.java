package org.enchere.eni.m.bo;

import java.util.ArrayList;
import java.util.List;

public class User {

	private int idUser;
	private String alias;
	private String surname;
	private String firstName;
	private String email;
	private String phone;
	private String street;
	private String zipCode;
	private String city; 
	private String passwordUser;
	private int credit;
	private boolean isAdmin;
	private List <ItemSold> itemsSold = new ArrayList <ItemSold>();
	private List <Bid> bids = new ArrayList <Bid>();
	
	
	public List<Bid> getBids() {
		return bids;
	}


	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}


	public User() {
	}


	public int getIdUser() {
		return idUser;
	}



	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}



	public String getAlias() {
		return alias;
	}



	public void setAlias(String alias) {
		this.alias = alias;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
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



	public String getPasswordUser() {
		return passwordUser;
	}



	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}



	public int getCredit() {
		return credit;
	}



	public void setCredit(int credit) {
		this.credit = credit;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public List<ItemSold> getItemsSold() {
		return itemsSold;
	}



	public void setItemsSold(List<ItemSold> itemsSold) {
		this.itemsSold = itemsSold;
	}



	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", alias=" + alias + ", surname=" + surname + ", firstName=" + firstName
				+ ", email=" + email + ", phone=" + phone + ", street=" + street + ", zipCode=" + zipCode + ", city="
				+ city + ", passwordUser=" + passwordUser + ", credit=" + credit + ", isAdmin=" + isAdmin
				+ ", itemsSold=" + itemsSold + ", bids=" + bids + "]";
	}






	
	
}