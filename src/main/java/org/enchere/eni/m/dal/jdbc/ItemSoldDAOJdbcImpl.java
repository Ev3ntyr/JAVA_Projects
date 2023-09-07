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
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;
import org.enchere.eni.m.dal.ItemDAO;

public class ItemSoldDAOJdbcImpl implements ItemDAO {
	
	public static final String SELECT_ALL = """
			SELECT * FROM SOLD_ITEMS
			JOIN USERS ON SOLD_ITEMS.idUser = USERS.idUser
			JOIN CATEGORIES ON SOLD_ITEMS.idCategory = CATEGORIES.idCategory
			JOIN WITHDRAW ON SOLD_ITEMS.idItem = WITHDRAW.idItem
			LEFT JOIN BIDS ON SOLD_ITEMS.idItem = BIDS.idItem;
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
					LocalDate bidStartDate = rs.getDate("bidStartDate").toLocalDate();
					LocalDate bidEndDate = rs.getDate("bidEndDate").toLocalDate();
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

					u = new User(idUser, alias, surname, firstName, email, phone, street, zipCode, city, passwordUser,
							credit, isAdmin);

					String street1 = rs.getString("street");
					String zipCode1 = rs.getString("zipCode");
					String city1 = rs.getString("city");

					Withdraw w = new Withdraw(street1, zipCode1, city1);

					int idCategory = rs.getInt("idCategory");
					String wording = rs.getString("wording");

					Category c = new Category(idCategory, wording);

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
			pStmt.setDate(3, Date.valueOf(item.getBidStartDate()));
			pStmt.setDate(4, Date.valueOf(item.getBidEndDate()));
			pStmt.setInt(5, item.getInitialPrice());
			pStmt.setInt(6, item.getSellingPrice());
			pStmt.setInt(7, item.getStateItem());
			pStmt.setInt(8, item.getUser().getIdUser());
			pStmt.setInt(9, item.getCategory().getIdCategory());
			
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
	private void insertWithdraw(Withdraw withdraw) {
		
		// TODO Add this method when creating a new item
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);
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
