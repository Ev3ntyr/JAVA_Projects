package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.enchere.eni.m.bll.BidManager;
import org.enchere.eni.m.bll.CategoryManager;
import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;
import org.enchere.eni.m.dal.ItemDAO;

public class ItemDAOJdbcImpl implements ItemDAO {
	
	public static final String SELECT_ALL = """
			SELECT * FROM SOLD_ITEMS
			""";

	

	@Override
	public List<Item> selectAll() {
		
		List<Item> items = new ArrayList<Item>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {
				Item currentItem = new Item();
				
				int idItem = rs.getInt("idItem");
				currentItem.setIdItem(idItem);
				
				String nameItem = rs.getString("nameItem");
				currentItem.setNameItem(nameItem);
				
				String descriptionItem = rs.getString("descriptionItem");
				currentItem.setDescriptionItem(descriptionItem);
				
				LocalDateTime bidStartDate = rs.getObject("bidStartDate",LocalDateTime.class);
				currentItem.setBidStartDate(bidStartDate);
				
				LocalDateTime bidEndDate = rs.getObject("bidEndDate",LocalDateTime.class);
				currentItem.setBidEndDate(bidEndDate);
				
				int initialPrice = rs.getInt("initialPrice");
				currentItem.setInitialPrice(initialPrice);
				
				int sellingPrice = rs.getInt("sellingPrice");
				currentItem.setSellingPrice(sellingPrice);
				
				int stateItem = rs.getInt("stateItem");
				currentItem.setStateItem(stateItem);

			
				User user = UserManager.getInstance().selectById(rs.getInt("idUser"));
				currentItem.setUser(user);
				
				Withdraw withdraw = new Withdraw();
				if (ItemManager.getInstance().hasWithdraw(currentItem)) {
					withdraw = ItemManager.getInstance().selectWithdraw(currentItem);
					currentItem.setWithdraw(withdraw);
				}

				Category category = CategoryManager.getInstance().selectById(rs.getInt("idCategory"));
				currentItem.setCategory(category);
				
				List<Bid> bids = BidManager.getInstance().selectAllByItem(currentItem);
				if (!bids.isEmpty()) {
					currentItem.setBids(bids);
				}
				
				items.add(currentItem);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public static final String INSERT = """
			INSERT INTO SOLD_ITEMS (nameItem, descriptionItem, bidStartDate, bidEndDate, initialPrice, sellingPrice, stateItem, idUser, idCategory)
			VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);
			""";
	@Override
	public void insert(Item item) {
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, item.getNameItem());
			pStmt.setString(2, item.getDescriptionItem());
			pStmt.setTimestamp(3, Timestamp.valueOf(item.getBidStartDate()));
			pStmt.setTimestamp(4, Timestamp.valueOf(item.getBidEndDate()));
			pStmt.setInt(5, item.getInitialPrice());
			pStmt.setInt(6, item.getSellingPrice());
			pStmt.setInt(7, item.getStateItem());
			pStmt.setInt(8, item.getUser().getIdUser());
			pStmt.setInt(9, 1);
			//TODO Select Category 
//			pStmt.setInt(9, item.getCategory().getIdCategory());
			
			pStmt.executeUpdate();
			
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if (rs.next()) {
				item.setIdItem(rs.getInt(1));
			}
						
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN INSERTING ITEM");
			sqle.printStackTrace();
		}
		
	}

	public static final String INSERT_WITHDRAW = """
			INSERT INTO WITHDRAW (idItem, street, zipCode, city)
			VALUES(?, ?, ?, ?);
			""";
	
	@Override	
	public void insertWithdraw(Withdraw withdraw) {
				
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT_WITHDRAW);
			pStmt.setInt(1, withdraw.getItemSold().getIdItem());
			pStmt.setString(2, withdraw.getStreet());
			pStmt.setString(3, withdraw.getZipCode());
			pStmt.setString(4, withdraw.getCity());
			
			pStmt.executeUpdate();
						
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN INSERTING WITHDRAW");
			sqle.printStackTrace();
		}
		
	}
	
	public static final String SELECT_WITHDRAW_BY_ID = """
			SELECT * FROM WITHDRAW WHERE idItem = ?;
			""";
	@Override
	public Withdraw selectWithdraw(Item item) {
		
		Withdraw withdraw = new Withdraw();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_WITHDRAW_BY_ID);
			pStmt.setInt(1, item.getIdItem());
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				withdraw.setItemSold(item);
				withdraw.setCity(rs.getString("city"));
				withdraw.setStreet(rs.getString("street"));
				withdraw.setZipCode(rs.getString("zipCode"));
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING WITHDRAW id=" + item.getIdItem());
			sqle.printStackTrace();
		}
		
		return withdraw;
		
	}
	
	@Override
	public boolean hasWithdraw(Item item) {
		return selectWithdraw(item) != null;
	}
}
