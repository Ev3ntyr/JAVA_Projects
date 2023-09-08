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

import org.enchere.eni.m.bll.CategoryManager;
import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;
import org.enchere.eni.m.dal.ItemDAO;

public class ItemDAOJdbcImpl implements ItemDAO {
	
	public static final String SELECT_ALL = """
			SELECT * FROM SOLD_ITEMS
			JOIN USERS ON SOLD_ITEMS.idUser = USERS.idUser
			JOIN CATEGORIES ON SOLD_ITEMS.idCategory = CATEGORIES.idCategory
			LEFT JOIN WITHDRAW ON SOLD_ITEMS.idItem = WITHDRAW.idItem
			LEFT JOIN BIDS ON SOLD_ITEMS.idItem = BIDS.idItem;
			""";
	public static final String SELECT_CATEGORY_BY_IDITEM = """
			
			""";
	

	@Override
	public List<Item> selectAll() {
		
		List<Item> itemSold = new ArrayList<Item>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			int idPreviousItem = 0;
			Item itemInProgress = null;
			User u = null;
			while (rs.next()) {

				int idItem = rs.getInt("idItem");
				if (idPreviousItem != idItem) {
					String nameItem = rs.getString("nameItem");
					String descriptionItem = rs.getString("descriptionItem");
					LocalDateTime bidStartDate = rs.getObject("bidStartDate",LocalDateTime.class);
					LocalDateTime bidEndDate = rs.getObject("bidEndDate",LocalDateTime.class);
					int initialPrice = rs.getInt("initialPrice");
					int sellingPrice = rs.getInt("sellingPrice");
					int stateItem = rs.getInt("stateItem");

					int idUser = rs.getInt("idUser");
					String alias = rs.getString("alias");
					String surname = rs.getString("surname");
					String firstName = rs.getString("firstName");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String street = rs.getString("street");
					String zipCode = rs.getString("zipCode");
					String city = rs.getString("city");
					String passwordUser = rs.getString("passwordUser");
					int credit = rs.getInt("credit");
					boolean isAdmin = rs.getBoolean("isAdmin");
					boolean isActive = rs.getBoolean("isActive");

					u = new User(idUser, alias, surname, firstName, email, phone, street, zipCode, city, passwordUser,
							credit, isAdmin, isActive);

					String street1 = rs.getString("street");
					String zipCode1 = rs.getString("zipCode");
					String city1 = rs.getString("city");

					Withdraw w = new Withdraw(street1, zipCode1, city1);

					Category c = CategoryManager.getInstance().selectById(rs.getInt("idCategory"));

					itemInProgress = new Item(idItem, nameItem, descriptionItem, bidStartDate, bidEndDate,
							initialPrice, sellingPrice, stateItem, u, w, c);
					
					itemSold.add(itemInProgress);
					idPreviousItem = idItem;				
				}
				if (rs.getInt("idBid") != 0) {
					int idBid = rs.getInt("idBid");
					LocalDate bidDate = rs.getDate("bidDate").toLocalDate();
					int bidAmount = rs.getInt("bidAmount");
					Bid bid = new Bid(idBid, bidDate, bidAmount, itemInProgress, u);
				
					itemInProgress.addBid(bid);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemSold;
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
}
