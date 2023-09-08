package org.enchere.eni.test;

import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.User;

public class AppliTestBO {
	
	public static void main(String[] args) {
		
		// TESTING USER CREATION
		User u1 = new User(1, "plop", "SMITH", "John", "john.smith@mail.com", "0725345899", "rue de l'ENI", "35752", "Rennes", "azerty", 100, false, true);
		User u2 = new User(2, "admin", "ADMIN", "ADMIN", "admin@admin.fr", "0000000000", "admin street", "75000", "Paris", "admin", 999, true, true);
		
		System.out.println("/// DISPLAY OF CREATED USERS");
		System.out.println(u1);
		System.out.println(u2);
		
		// TESTING USER MODIFICATION
		
		u1.setAlias("Mr.Plop");
		System.out.println("\n/// DISPLAY OF USER AFTER MODIFICATION");
		System.out.println(u1);
		
		// TESTING CATEGORY CREATION
		Category c1 = new Category();
		c1.setIdCategory(1);
		c1.setWording("TestCategory");
		System.out.println("\n/// DISPLAY OF CREATED CATEGORY");
		
		// 
		
		
	}

}
