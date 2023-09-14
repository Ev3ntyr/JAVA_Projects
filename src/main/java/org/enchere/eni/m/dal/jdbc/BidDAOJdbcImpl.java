package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.enchere.eni.m.bll.BidManager;
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
			
			pStmt.setTimestamp(1, Timestamp.valueOf(bid.getBidDate()));
			pStmt.setInt(2, bid.getBidAmount());
			pStmt.setInt(3, bid.getItemSold().getIdItem());
			pStmt.setInt(4, bid.getUser().getIdUser());
			
			pStmt.executeUpdate();
			
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if (rs.next()) {
				bid.setIdBid(rs.getInt(1));
			}
			
			bid.getItemSold().setSellingPrice(bid.getBidAmount());
			ItemManager.getInstance().updateSellingPrice(bid.getItemSold());
			
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
	
	public static final String SELECT_MAX_BID = """		
		SELECT idBid, bidDate, bidAmount as HighestBid, idItem, idUser FROM BIDS 
		WHERE (SELECT MAX(bidAmount) FROM BIDS WHERE idItem = ?) = bidAmount AND idItem = ?;
		""";
	
	@Override
	public Bid selectMaxBid(Item item) {
	
	
		Bid maxBid = new Bid();
		
		try ( Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MAX_BID);
	
			pStmt.setInt(1, item.getIdItem());
			pStmt.setInt(2, item.getIdItem());
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				
				int idBid = rs.getInt("idBid");
				
				LocalDateTime bidDate = rs.getObject("bidDate", LocalDateTime.class);
				
				int bidAmount = rs.getInt("HighestBid");
				
				User user = UserManager.getInstance().selectById(rs.getInt("idUser"));
				
				maxBid.setIdBid(idBid);
	            maxBid.setBidDate(bidDate);
	            maxBid.setBidAmount(bidAmount);
	            maxBid.setUser(user);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return maxBid;
	}
	
	public static final String DELETE_USER_BIDS = """
			DELETE FROM BIDS WHERE idUser = ?;
			""";
	@Override
	public void deleteUserBids(User user) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(DELETE_USER_BIDS);
			pStmt.setInt(1, user.getIdUser());
			
			pStmt.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN DELETING USER BIDS");
			sqle.printStackTrace();
		}
	}
	
	private static final String SELECT_WINNING_BIDS = """
			SELECT idItem, MAX(bidAmount) as highestBid FROM BIDS GROUP BY idItem;
			""";
	@Override
	public List<Bid> selectWinningBids() {
		List<Bid> winningBids = new ArrayList<Bid>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_WINNING_BIDS);
			
			while (rs.next()) {
				
				Bid bid = new Bid();
				Item item = new Item();
				
				int idItem = rs.getInt("idItem");
				item.setIdItem(idItem);
				
				int highestBid = rs.getInt("highestBid");
				
				bid.setItemSold(item);
				bid.setBidAmount(highestBid);
				
				winningBids.add(bid);
				
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING WINNING BIDS");
			sqle.printStackTrace();
		}
		
		return winningBids;
	}
	
	public static final String SELECT_BY_ID = "SELECT * FROM BIDS WHERE idBid = ?;";
	
	@Override
	public Bid select(int idBid) {
		
		Bid bid = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, idBid);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				bid = new Bid();
				
				LocalDateTime bidDate = rs.getObject("bidDate", LocalDateTime.class);
				int bidAmount = rs.getInt("bidAmount");
				Item item = ItemManager.getInstance().selectById(rs.getInt("idItem"));
				User user = UserManager.getInstance().selectById(rs.getInt("idUser"));
				
				bid.setIdBid(idBid);
				bid.setBidDate(bidDate);
				bid.setBidAmount(bidAmount);
				bid.setUser(user);
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECT BID id=" + idBid);
			sqle.printStackTrace();
		}
		
		return bid;
		
	}
	
	private static final String SELECT_USER_WINNING_BIDS = """
			SELECT idUser, idBid FROM BIDS WHERE bidAmount = ? AND idItem = ?;
			""";
	@Override
	public List<Bid> selectUserWinningBids(User user) {
		
		List<Bid> userWinningBids = new ArrayList<Bid>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			List<Bid> winningBids = BidManager.getInstance().selectWinningBids();
			
			PreparedStatement pStmt = null;
			
			for (Bid bid : winningBids) {
				
				pStmt = cnx.prepareStatement(SELECT_USER_WINNING_BIDS);
				pStmt.setInt(1, bid.getBidAmount());
				pStmt.setInt(2, bid.getItemSold().getIdItem());
				
				ResultSet rs = pStmt.executeQuery();
				if (rs.next()) {
					int idWinningUser = rs.getInt("idUser");
					int idBid = rs.getInt("idBid");
					
					if (idWinningUser == user.getIdUser()) {
						Bid b = BidManager.getInstance().select(idBid);
						userWinningBids.add(b);
					}
					
				}
			}
			
		} catch (SQLException sqle) {
			
			System.out.println("ERROR WHEN SELECT USER'S WINNING BIDS");
			sqle.printStackTrace();
			
		}
		
		return userWinningBids;
		
	}
	
}
