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
	private List <ItemSold> itemSold = new ArrayList <ItemSold>();
	
	
	
	public User() {
	}


	public int getidUser() {
		return idUser;
	}

	public void setidUser(int idUser) {
		this.idUser = idUser;
	}

	public String getalias() {
		return alias;
	}

	public void setalias(String alias) {
		this.alias = alias;
	}

	public String getsurname() {
		return surname;
	}

	public void setsurname(String surname) {
		this.surname = surname;
	}

	public String getFirstsurname() {
		return firstName;
	}

	public void setFirstsurname(String firstsurname) {
		this.firstName = firstsurname;
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

	public String getpasswordUser() {
		return passwordUser;
	}

	public void setpasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isisAdmin() {
		return isAdmin;
	}

	public void setisAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", alias=" + alias + ", surname=" + surname + ", firstsurname=" + firstName
				+ ", email=" + email + ", phone=" + phone + ", street=" + street + ", zipCode=" + zipCode
				+ ", city=" + city + ", passwordUser=" + passwordUser + ", credit=" + credit + ", isAdmin=" + isAdmin + "]";
	}
	
	
}
