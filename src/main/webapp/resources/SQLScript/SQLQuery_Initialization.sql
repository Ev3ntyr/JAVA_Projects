-- Database initialization 
USE AUCTION_DB

-- Adding categories
INSERT INTO CATEGORIES (wording) VALUES ('Informatique'),('Ameublement'),('Sport Loisirs'),('Vetement');

-- Adding users

INSERT INTO USERS (alias, surname, firstName, email, phone, street, zipCode, city, passwordUser, credit, isAdmin, isActive)
VALUES
    ('RogerTheRoro', 'Smith', 'Roger', 'roger.smith@email.com', '01234567890', '123 Main Street', '12345', 'New York', 'IL0vNY123', 312, 0, 1),
    ('AliceInBorderlands', 'Johnson', 'Alice', 'alice.johnson@email.com', '02345678901', '456 Elm Street', '23456', 'Los Angeles', 'lAV!bes', 153, 0, 1),
    ('DoudouDavid', 'Fortin', 'David', 'david.fortin@email.com', '0761115598', '78 Avenue Montaigne', '75007', 'Paris', 'monMotdePasseEstTop', 1200, 0, 1),
    ('Soso', 'Wilson', 'Sophia', 'sophia.wilson@email.com', '4567890123', '1010 Pine Road', '45678', 'Houston', '123456', 0, 0, 1),
	('admin', 'admin', 'admin', 'admin@email.com', '0761144598', 'admin street', '75000', 'Paris', 'admin', 600, 1, 1);

-- Adding IT items

INSERT INTO SOLD_ITEMS (nameItem, descriptionItem, bidStartDate, bidEndDate, initialPrice, sellingPrice, stateItem, idUser, idCategory)
VALUES
    ('Ordinateur Portable AlienWare', 'Un ordinateur portable haut de gamme AlienWare avec 32 GO de RAM !!', '20230904 10:34:09 AM', '20230912 10:34:09 AM', 600, NULL, 1, 1, 1),
    ('Tablette Android', 'Une tablette Android de derniere generation de 10 pouces, pas de chargeur cependant, prevoir de racheter', '20230910 11:34:09 PM', '20230918 09:34:09 PM', 320, NULL, 0, 1, 1),
    ('Imprimante Laser Canon', 'Une imprimante laser rapide et silencieuse', '20230801 10:34:09 AM', '20230820 10:34:09 AM', 150, 223, 2, 3, 1),
    ('Moniteur 27 pouces Dell', 'Un moniteur IPS de 27 pouces de la marque DELL, TBE', '20230916 10:34:09 AM', '20230922 10:34:09 AM', 250, NULL, 0, 2, 1);

-- Adding furnishings items
INSERT INTO SOLD_ITEMS (nameItem, descriptionItem, bidStartDate, bidEndDate, initialPrice, sellingPrice, stateItem, idUser, idCategory)
VALUES
    ('Canapé en Cuir', 'Un canape en cuir veritable pleine fleur de couleur beige. 6 places assises, tres confortable, legerement abime', '20230904 10:34:09 AM', '20230920 10:34:09 AM', 500, NULL, 1, 1, 2),
    ('Table à Manger', 'Une table à manger en bois massif', '20230905 10:34:09 AM', '20230919 10:34:09 AM', 200, NULL, 1, 4, 2);

-- Adding sports items
INSERT INTO SOLD_ITEMS (nameItem, descriptionItem, bidStartDate, bidEndDate, initialPrice, sellingPrice, stateItem, idUser, idCategory)
VALUES
    ('Vélo de Montagne', 'Un velo de montagne tout-terrain qui roule super bien et a des gros pneus. 16 vitesses, sonette et antivol compris', '20230607 10:34:09 AM', '20230622 10:34:09 AM', 350, 370, 2, 4, 3),
    ('Raquette de Tennis Decathlon', 'Une raquette de tennis professionnelle pour les enfants', '20230917 10:34:09 AM', '20230923 10:34:09 AM', 100, NULL, 0, 3, 3);

-- Adding clothing items
INSERT INTO SOLD_ITEMS (nameItem, descriptionItem, bidStartDate, bidEndDate, initialPrice, sellingPrice, stateItem, idUser, idCategory)
VALUES
    ('Manteau Hiver T38 Kaporal', 'Un manteau hiver chaud et élégant pour homme', '20230901 10:34:09 AM', '20230926 10:34:09 AM', 30, NULL, 1, 4, 4);


-- Adding Withdraw points 
USE AUCTION_DB
INSERT INTO WITHDRAW (idItem, street, zipCode, city)
VALUES
	(1, '123 Main Street', '12345', 'New York'),
	(2, '123 Main Street', '12345', 'New York'),
	(3, '2 Rue St Georges', '75002', 'Paris'),
	(4, '456 Elm Street', '23456', 'Los Angeles'),
	(5, '123 Main Street', '12345', 'New York'),
	(6, '2b Street Road', '45678', 'Houston'),
	(7, '2b Street Road', '45678', 'Houston'),
	(8, '2 Rue St Georges', '75002', 'Paris'),
	(9, '2b Street Road', '45678', 'Houston');

-- Adding bids

INSERT INTO BIDS (bidDate, bidAmount, idItem, idUser)
VALUES
	('20230608 10:34:09 AM', 620, 1, 2),
	('20230608 10:34:09 AM', 370, 7, 1), 
	('20230903 10:34:09 AM', 32, 9, 1);


-- Testing
SELECT * FROM CATEGORIES;
USE AUCTION_DB
SELECT * FROM USERS;
SELECT * FROM SOLD_ITEMS;
SELECT * FROM SOLD_ITEMS WHERE idUser = 1;
SELECT * FROM WITHDRAW;
SELECT * FROM USERS INNER JOIN SOLD_ITEMS ON users.idUser= sold_items.idUser;
SELECT * FROM USERS JOIN SOLD_ITEMS ON users.idUser= sold_items.idUser AND Users.idUser = 1;
SELECT * FROM BIDS;