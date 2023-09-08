USE MASTER

CREATE DATABASE AUCTION_DB

USE AUCTION_DB
-- Script Database creation
--   type :      SQL Server 2012
--


-- Creating Table Categories

DROP TABLE IF EXISTS WITHDRAW
DROP TABLE IF EXISTS BIDS
DROP TABLE IF EXISTS USERS
DROP TABLE IF EXISTS SOLD_ITEMS
DROP TABLE IF EXISTS CATEGORIES

CREATE TABLE CATEGORIES (
    idCategory   INTEGER IDENTITY(1,1) NOT NULL,
    wording        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint category_pk PRIMARY KEY (idCategory)

  
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
)

ALTER TABLE USERS ADD constraint user_pk PRIMARY KEY (idUser)

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
)

ALTER TABLE SOLD_ITEMS ADD constraint sold_items_pk PRIMARY KEY (idItem)
ALTER TABLE SOLD_ITEMS ADD constraint ck_state_item CHECK (stateItem BETWEEN 0 AND 2)

-- Creating Table Withdraw

CREATE TABLE WITHDRAW (
	idItem        INTEGER NOT NULL,
    street              VARCHAR(30) NOT NULL,
    zipCode    VARCHAR(15) NOT NULL,
    city            VARCHAR(30) NOT NULL
)

ALTER TABLE WITHDRAW ADD constraint withdraw_pk PRIMARY KEY  (idItem)
ALTER TABLE WITHDRAW
    ADD CONSTRAINT withdraw_item_fk FOREIGN KEY (idItem) REFERENCES  SOLD_ITEMS (idItem)
ON DELETE NO ACTION 
    ON UPDATE no action 

-- Creating Table Bids

CREATE TABLE BIDS(	
	idBid  INTEGER IDENTITY(1,1) NOT NULL,
	bidDate datetime NOT NULL,
	bidAmount INTEGER NOT NULL,
	idItem INTEGER NOT NULL,
	idUser INTEGER NOT NULL
 )

ALTER TABLE BIDS ADD constraint bids_pk PRIMARY KEY (idBid)
 
ALTER TABLE BIDS
    ADD CONSTRAINT bids_users_fk FOREIGN KEY (idUser) REFERENCES USERS (idUser)
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE BIDS
    ADD CONSTRAINT bids_item_no_fk FOREIGN KEY (idItem) REFERENCES SOLD_ITEMS (idItem)
ON DELETE NO ACTION 
    ON UPDATE no action 
	
-- Finally adding Foreign Keys

ALTER TABLE SOLD_ITEMS
    ADD CONSTRAINT sold_items_categories_fk FOREIGN KEY (idCategory)
        REFERENCES categories (idCategory)
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE SOLD_ITEMS
    ADD CONSTRAINT sold_user_fk FOREIGN KEY (idUser)
        REFERENCES USERS (idUser)
ON DELETE NO ACTION
    ON UPDATE no action 
