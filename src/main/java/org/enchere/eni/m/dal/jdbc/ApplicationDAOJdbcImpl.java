package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.enchere.eni.c.BusinessException;
import org.enchere.eni.m.bll.CategoryManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.ApplicationDAO;

public class ApplicationDAOJdbcImpl implements ApplicationDAO {

	public static final String INIT_DB = """
			USE master;

			IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'AUCTION_DB')
				BEGIN
					CREATE DATABASE AUCTION_DB
				END;
			
			USE AUCTION_DB;
			
			IF (OBJECT_ID('dbo.withdraw_item_fk', 'F') IS NOT NULL)
			BEGIN
			    ALTER TABLE dbo.WITHDRAW DROP CONSTRAINT withdraw_item_fk
			END;
			IF (OBJECT_ID('dbo.bids_users_fk', 'F') IS NOT NULL)
			BEGIN
			    ALTER TABLE dbo.BIDS DROP CONSTRAINT bids_users_fk
			END;
			IF (OBJECT_ID('dbo.bids_item_no_fk', 'F') IS NOT NULL)
			BEGIN
			    ALTER TABLE dbo.BIDS DROP CONSTRAINT bids_item_no_fk
			END;
			IF (OBJECT_ID('dbo.sold_items_categories_fk', 'F') IS NOT NULL)
			BEGIN
			    ALTER TABLE dbo.SOLD_ITEMS DROP CONSTRAINT sold_items_categories_fk
			END;
			
			IF (OBJECT_ID('dbo.sold_user_fk', 'F') IS NOT NULL)
			BEGIN
			    ALTER TABLE dbo.SOLD_ITEMS DROP CONSTRAINT sold_user_fk
			END;
			
			-- Drop of all tables
			DROP TABLE IF EXISTS WITHDRAW;
			DROP TABLE IF EXISTS BIDS;
			DROP TABLE IF EXISTS SOLD_ITEMS;
			DROP TABLE IF EXISTS USERS;
			DROP TABLE IF EXISTS CATEGORIES;
			
			
			-- Creating Table Categories
			CREATE TABLE CATEGORIES (
			    idCategory   INTEGER IDENTITY(1,1) NOT NULL,
			    wording        VARCHAR(30) NOT NULL
			);
			
			ALTER TABLE CATEGORIES ADD constraint category_pk PRIMARY KEY (idCategory);
			
			  
			 -- Creating Table Users
			CREATE TABLE USERS (
			    idUser   INTEGER IDENTITY(1,1) NOT NULL,
			    alias           VARCHAR(30) NOT NULL,
			    surname              VARCHAR(30) NOT NULL,
			    firstName           VARCHAR(30) NOT NULL,
			    email            VARCHAR(50) NOT NULL,
			    phone        VARCHAR(15),
			    street              VARCHAR(30) NOT NULL,
			    zipCode      VARCHAR(10) NOT NULL,
			    city            VARCHAR(50) NOT NULL,
			    passwordUser     VARCHAR(128) NOT NULL,
			    credit           INTEGER NOT NULL,
			    isAdmin   bit NOT NULL,
				isActive bit NOT NULL
			);
			ALTER TABLE USERS ADD constraint user_pk PRIMARY KEY (idUser);
			
			
			-- Creating Table Sold_Items
			CREATE TABLE SOLD_ITEMS (
			    idItem                    INTEGER IDENTITY(1,1) NOT NULL,
			    nameItem                   VARCHAR(30) NOT NULL,
			    descriptionItem                   VARCHAR(300) NOT NULL,
				bidStartDate           DATETIME  NOT NULL,
			    bidEndDate             DATETIME NOT NULL,
			    initialPrice                  INTEGER,
			    sellingPrice                    INTEGER,
				stateItem					INT NOT NULL,
			    idUser                INTEGER NOT NULL,
			    idCategory                  INTEGER NOT NULL
			);
			
			ALTER TABLE SOLD_ITEMS ADD constraint sold_items_pk PRIMARY KEY (idItem);
			ALTER TABLE SOLD_ITEMS ADD constraint ck_state_item CHECK (stateItem BETWEEN 0 AND 2);
			ALTER TABLE SOLD_ITEMS
			    ADD CONSTRAINT sold_items_categories_fk FOREIGN KEY (idCategory)
			    REFERENCES categories (idCategory)
				ON DELETE NO ACTION 
			    ON UPDATE NO ACTION;
			
			ALTER TABLE SOLD_ITEMS
			    ADD CONSTRAINT sold_user_fk FOREIGN KEY (idUser)
			    REFERENCES USERS (idUser)
				ON DELETE NO ACTION
			    ON UPDATE NO ACTION;
			
			-- Creating Table Withdraw
			CREATE TABLE WITHDRAW (
			    idWithdraw INTEGER IDENTITY(1, 1) NOT NULL,
				idItem        INTEGER NOT NULL,
			    street              VARCHAR(30) NOT NULL,
			    zipCode    VARCHAR(15) NOT NULL,
			    city            VARCHAR(30) NOT NULL
			);
			ALTER TABLE WITHDRAW ADD constraint withdraw_pk PRIMARY KEY  (idWithdraw);
			ALTER TABLE WITHDRAW
			    ADD CONSTRAINT withdraw_item_fk FOREIGN KEY (idItem) REFERENCES SOLD_ITEMS (idItem)
			    ON DELETE NO ACTION 
			    ON UPDATE NO ACTION;
			
			-- Creating Table Bids
			CREATE TABLE BIDS (	
				idBid  INTEGER IDENTITY(1,1) NOT NULL,
				bidDate datetime NOT NULL,
				bidAmount INTEGER NOT NULL,
				idItem INTEGER NOT NULL,
				idUser INTEGER NOT NULL
			 );
			ALTER TABLE BIDS ADD constraint bids_pk PRIMARY KEY (idBid);
			 
			ALTER TABLE BIDS
			    ADD CONSTRAINT bids_users_fk FOREIGN KEY (idUser) REFERENCES USERS (idUser)
				ON DELETE NO ACTION 
			    ON UPDATE NO ACTION;
			
			ALTER TABLE BIDS
			    ADD CONSTRAINT bids_item_no_fk FOREIGN KEY (idItem) REFERENCES SOLD_ITEMS (idItem)
				ON DELETE NO ACTION 
			    ON UPDATE NO ACTION; 
			""";
	@Override
	public void initDB() {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			stmt.executeUpdate(INIT_DB);
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN CREATING DATABSE AND TABLES");
			sqle.printStackTrace();
		}
		
		
	}

	@Override
	public void initDataset() {
		
		// Initialization of categories
		Category c = new Category("Non affectée");
		Category c1 = new Category("Informatique");
		Category c2 = new Category("Ameublement");
		Category c3 = new Category("Sport Loisirs");
		Category c4 = new Category("Vêtements");
		
		CategoryManager.getInstance().insertCategory(c);
		CategoryManager.getInstance().insertCategory(c1);
		CategoryManager.getInstance().insertCategory(c2);
		CategoryManager.getInstance().insertCategory(c3);
		CategoryManager.getInstance().insertCategory(c4);
		
		// Initialization of users
		User u = new User("DeletedVendor", "", "", "", "", "", "", "", "DeletedUserDoNotUse", 0, false, false);
		User u1 = new User("RogerTheRoro", "Smith", "Roger", "roger.smith@email.com", "01234567890", "123 Main Street", "12345", "New York", "IL0vNY123", 312, false, true);
		User u2 = new User("AliceInBorderlands", "Johnson", "Alice", "alice.johnson@email.com", "02345678901", "456 Elm Street", "23456", "Los Angeles", "lAV!bes", 153, false, true);
		User u3 = new User("DoudouDavid", "Fortin", "David", "david.fortin@email.com", "0761115598", "78 Avenue Montaigne", "75007", "Paris", "monMotdePasseEstTop", 1200, false, true);
		User u4 = new User("Soso", "Wilson", "Sophia", "sophia.wilson@email.com", "4567890123", "1010 Pine Road", "45678", "Houston", "123456", 0, false, true);
		User u5 = new User("admin", "admin", "admin", "admin@email.com", "0761144598", "admin street", "75000", "Paris", "admin", 9999, true, true);
		
		try {
			UserManager.getInstance().createUser(u);
			UserManager.getInstance().createUser(u1);
			UserManager.getInstance().createUser(u2);
			UserManager.getInstance().createUser(u3);
			UserManager.getInstance().createUser(u4);
			UserManager.getInstance().createUser(u5);
		} catch (BusinessException be) {
			System.out.println("ERROR WHEN INITIALIZING USERS");
			be.printStackTrace();
		}
		
		// Initialization of items
		
		
		
	}
	
	

}
