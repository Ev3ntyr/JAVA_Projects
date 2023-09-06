package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.ItemSold;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.BidDAO;

public class BidDAOJdbcImpl implements BidDAO {
	
	private static final String INIT_TABLE = """
			DROP TABLE IF EXISTS Bids;
			
			CREATE TABLE BIDS(	
				idBid INTEGER IDENTITY(1,1) NOT NULL,
				bidDate datetime NOT NULL,
				bidAmount INTEGER NOT NULL,
				idItem INTEGER NOT NULL,
				idUser INTEGER NOT NULL
			 );
			 
			ALTER TABLE BIDS ADD constraint bids_pk PRIMARY KEY (idBid);
 
			ALTER TABLE BIDS
			    ADD CONSTRAINT bids_users_fk FOREIGN KEY (idUser) REFERENCES USERS (idUser)
				ON DELETE NO ACTION 
			    ON UPDATE no action; 

			ALTER TABLE BIDS
			    ADD CONSTRAINT bids_item_no_fk FOREIGN KEY (idItem) REFERENCES SOLD_ITEMS (idItem)
				ON DELETE NO ACTION 
			    ON UPDATE no action;
			""";
	
	private static final String INSERT = """
			INSERT INTO BIDS (bidDate, bidAmount, idItem, idUser)
			VALUES(?, ?, ?, ?);
			""";

	@Override
	public void initDataset() {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			stmt.executeUpdate(INIT_TABLE);
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN CREATING Bids TABLE");
			sqle.printStackTrace();
		}
		
		User u1 = new User();
		u1.setIdUser(1);
		User u2 = new User();
		u2.setIdUser(2);
		
		ItemSold item1 = new ItemSold();
		item1.setIdItem(1);
		ItemSold item7 = new ItemSold();
		item7.setIdItem(7);
		ItemSold item9 = new ItemSold();
		item9.setIdItem(9);
		Bid b1 = new Bid(LocalDate.now().minusMonths(3), 620, item1, u2);
		Bid b2 = new Bid(LocalDate.now().minusMonths(3), 370, item7, u1);
		Bid b3 = new Bid(LocalDate.now(), 32, item9, u1);
		
		insert(b1);
		insert(b2);
		insert(b3);
		
	}

	@Override
	public void insert(Bid bid) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStmt.setDate(1, Date.valueOf(bid.getBidDate()));
			pStmt.setInt(2, bid.getBidAmount());
			pStmt.setInt(3, bid.getItemSold().getIdItem());
			pStmt.setInt(4, bid.getUser().getIdUser());
			
			pStmt.executeUpdate();
			
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if (rs.next()) {
				bid.setIdBid(rs.getInt(1));
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN INSERTING Bid IN DATABASE");
			sqle.printStackTrace();
		}
		
	}
	

}
