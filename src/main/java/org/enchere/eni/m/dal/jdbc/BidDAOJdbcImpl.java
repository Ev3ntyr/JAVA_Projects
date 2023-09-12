package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
			
			pStmt.setTimestamp(1, Timestamp.valueOf(bid.getBidDate()));
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
				LocalDateTime bidDate = rs.getObject("bidDate", LocalDateTime.class);
				bid.setBidDate(bidDate);
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

}
