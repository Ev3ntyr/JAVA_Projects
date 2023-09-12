package org.enchere.eni.m.dal.jdbc;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
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
	
	public static final String SELECT_ALL_BY_ITEM = """
			SELECT * FROM BIDS WHERE idItem = ?;
			""";
	
	public List<Bid> selectAllByItem(Item item) {
		
		List<Bid> bids = new ArrayList<Bid>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_BY_ITEM);
			pStmt.setInt(1, item.getIdItem());
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				
				Bid bid = new Bid();
				
				bid.setIdBid(rs.getInt("idBid"));
				//bid.setBidDate(rs.getTimestamp("bidDate"));
				// TODO Modify bid object to handle new date / time format
				bid.setBidAmount(rs.getInt("bidAmount"));
				bid.setItemSold(item);
				User user = UserManager.getInstance().selectById(item.getUser().getIdUser());
				bid.setUser(user);
				
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING BIDS");
			sqle.printStackTrace();
		}
		
		return bids;
		
	}
	
	public static final String SELECT_MAX_BID = """		
	SELECT idBid, bidDate, MAX(bidAmount)
	as HighestBid, idItem, idUser 
	FROM BIDS WHERE idItem = ? GROUP BY idBid, bidDate, idItem, idUser;
	""";
	
	public Bid selectMaxBid(Item item) {
	
	
	Bid maxBid = new Bid();
	
	try ( Connection cnx = ConnectionProvider.getConnection()){
		
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_MAX_BID);

		pStmt.setInt(1, item.getIdItem());
		
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()) {
			
			int idBid = rs.getInt("idBid");
			
			
			LocalDateTime bidDate = rs.getObject("bidDate", LocalDateTime.class);
			
			int bidAmount = rs.getInt("bidAmount");
			
			User user = UserManager.getInstance().selectById(rs.getInt("idUser"));
			
			maxBid.setIdBid(idBid);
           // maxBid.setBidDate(bidDate);
            maxBid.setBidAmount(bidAmount);
            maxBid.setUser(user);
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	return maxBid;
}
	
}
