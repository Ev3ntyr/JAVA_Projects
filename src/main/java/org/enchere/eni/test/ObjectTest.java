package org.enchere.eni.test;

public class ObjectTest {
		
	private int id;
	
	private String nom;
	
	private String currentBook = "Le Parfum";
	


	public ObjectTest(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	


	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}

}
