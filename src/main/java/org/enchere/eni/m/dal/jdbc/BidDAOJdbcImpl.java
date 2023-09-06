package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.dal.BidDAO;

public class BidDAOJdbcImpl implements BidDAO {
	
	private static final String INSERT = """
			INSERT INTO BIDS (bidDate, bidAmount, idItem, idUser)
			VALUES(?, ?, ?, ?);
			""";

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
