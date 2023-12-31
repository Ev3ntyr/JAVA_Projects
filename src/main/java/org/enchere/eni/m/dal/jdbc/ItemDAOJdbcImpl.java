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
import org.enchere.eni.m.bll.CategoryManager;
import org.enchere.eni.m.bll.ItemManager;
import org.enchere.eni.m.bll.UserManager;
import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;
import org.enchere.eni.m.dal.ItemDAO;
import org.enchere.eni.m.security.BCrypt;

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

				LocalDateTime bidStartDate = rs.getObject("bidStartDate", LocalDateTime.class);
				currentItem.setBidStartDate(bidStartDate);

				LocalDateTime bidEndDate = rs.getObject("bidEndDate", LocalDateTime.class);
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
			INSERT INTO SOLD_ITEMS (nameItem, descriptionItem, bidStartDate, bidEndDate,
			initialPrice, sellingPrice, stateItem, idUser, idCategory)
			VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);
			""";

	@Override
	public void insert(Item item) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, item.getNameItem());
			pStmt.setString(2, item.getDescriptionItem());
			pStmt.setTimestamp(3, Timestamp.valueOf(item.getBidStartDate()));
			pStmt.setTimestamp(4, Timestamp.valueOf(item.getBidEndDate()));
			pStmt.setInt(5, item.getInitialPrice());
			pStmt.setInt(6, item.getInitialPrice());
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

	@Override
	public void insertWithdraw(Withdraw withdraw) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
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

		Withdraw withdraw = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_WITHDRAW_BY_ID);
			pStmt.setInt(1, item.getIdItem());

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				withdraw = new Withdraw();
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

	public static final String SELECT_ALL_BY_NAME = """
			SELECT * FROM SOLD_ITEMS WHERE nameItem LIKE ?;
			""";

	@Override
	public List<Item> selectAllByName(String itemName) {

		List<Item> items = new ArrayList<Item>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_BY_NAME);

			pStmt.setString(1, "%" + itemName + "%");

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				Item currentItem = new Item();

				int idItem = rs.getInt("idItem");
				currentItem.setIdItem(idItem);

				String nameItem = rs.getString("nameItem");
				currentItem.setNameItem(nameItem);

				String descriptionItem = rs.getString("descriptionItem");
				currentItem.setDescriptionItem(descriptionItem);

				LocalDateTime bidStartDate = rs.getObject("bidStartDate", LocalDateTime.class);
				currentItem.setBidStartDate(bidStartDate);

				LocalDateTime bidEndDate = rs.getObject("bidEndDate", LocalDateTime.class);
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
			System.out.println("ERROR WHEN SELECTING ALL ITEMS FROM DB");
			e.printStackTrace();
		}

		return items;

	}

	public static final String SELECT_ITEM_BY_ID = """
			SELECT idItem, nameItem, descriptionItem, bidStartDate, bidEndDate, initialPrice, sellingPrice,
			stateItem, idUser, idCategory FROM SOLD_ITEMS WHERE idItem = ?;
			""";

	@Override
	public Item selectById(int idItem) {

		Item currentItem = new Item();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ITEM_BY_ID);

			pStmt.setInt(1, idItem);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {

				currentItem.setIdItem(idItem);

				String nameItem = rs.getString("nameItem");
				currentItem.setNameItem(nameItem);

				String descriptionItem = rs.getString("descriptionItem");
				currentItem.setDescriptionItem(descriptionItem);

				LocalDateTime bidStartDate = rs.getObject("bidStartDate", LocalDateTime.class);
				currentItem.setBidStartDate(bidStartDate);

				LocalDateTime bidEndDate = rs.getObject("bidEndDate", LocalDateTime.class);
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
				} else {
					withdraw.setCity(user.getCity());
					withdraw.setStreet(user.getStreet());
					withdraw.setZipCode(user.getZipCode());
					currentItem.setWithdraw(withdraw);
				}

				Category category = CategoryManager.getInstance().selectById(rs.getInt("idCategory"));
				currentItem.setCategory(category);

				List<Bid> bids = BidManager.getInstance().selectAllByItem(currentItem);
				if (!bids.isEmpty()) {
					currentItem.setBids(bids);
				}

			}

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING ITEM WITH ID=" + idItem);
			sqle.printStackTrace();
		}

		return currentItem;
	}

	public static final String UPDATE_SELLING_PRICE = """
			UPDATE SOLD_ITEMS SET sellingPrice = ? WHERE idItem = ?;
			""";

	@Override
	public void updateSellingPrice(Item item) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_SELLING_PRICE);
			pStmt.setInt(1, item.getSellingPrice());
			pStmt.setInt(2, item.getIdItem());
			pStmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN UPDATING SELLING PRICE");
			sqle.printStackTrace();
		}

	}

	public static final String DELETE_WITHDRAW = "DELETE FROM WITHDRAW WHERE idItem = ?;";
	public static final String DELETE = "DELETE FROM SOLD_ITEMS WHERE idItem = ?;";

	@Override
	public void delete(int idItem) {
		Item item = selectById(idItem);
		PreparedStatement pStmt = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// If deleted item has a withdraw in db, this withdraw is also deleted
			// beforehand (FK constraint)
			if (hasWithdraw(item)) {
				pStmt = cnx.prepareStatement(DELETE_WITHDRAW);
				pStmt.setInt(1, idItem);

				pStmt.executeUpdate();
			}
			pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, idItem);

			pStmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN DELETING ITEM AND/OR WITHDRAW FROM DB");
			sqle.printStackTrace();
		}
	}

	public static final String UPDATE = """
			UPDATE SOLD_ITEMS SET
			nameItem = ?,
			descriptionItem = ?,
			bidStartDate = ?,
			bidEndDate = ?,
			initialPrice = ?,
			sellingPrice = ?,
			stateItem = ?,
			idCategory = ?
			WHERE idItem = ?;
			""";

	@Override
	public void update(Item item) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, item.getNameItem());
			pStmt.setString(2, item.getDescriptionItem());
			pStmt.setTimestamp(3, Timestamp.valueOf(item.getBidStartDate()));
			pStmt.setTimestamp(4, Timestamp.valueOf(item.getBidEndDate()));
			pStmt.setInt(5, item.getInitialPrice());
			pStmt.setInt(6, item.getInitialPrice());
			pStmt.setInt(7, item.getStateItem());
			pStmt.setInt(8, item.getCategory().getIdCategory());
			pStmt.setInt(9, item.getIdItem());

			pStmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN UPDATING ITEM");
			sqle.printStackTrace();
		}

	}

	public static final String UPDATE_WITHDRAW = """
			UPDATE WITHDRAW SET
			street = ?,
			zipCode = ?,
			city = ?
			WHERE idItem = ?;
			""";

	@Override
	public void updateWithdraw(Withdraw withdraw) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_WITHDRAW);
			pStmt.setString(1, withdraw.getStreet());
			pStmt.setString(2, withdraw.getZipCode());
			pStmt.setString(3, withdraw.getCity());
			pStmt.setInt(4, withdraw.getItemSold().getIdItem());

			pStmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN UPDATING WITHDRAW");
			sqle.printStackTrace();
		}
	}

	public static final String SELECT_ALL_OPEN_BY_USER = """
			SELECT idItem, nameItem FROM SOLD_ITEMS WHERE idUser = ? AND bidStartDate < GETDATE() AND bidEndDate > GETDATE();
			""";

	public List<Item> selectAllOpenByUser(User user) {

		List<Item> items = new ArrayList<Item>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_OPEN_BY_USER);
			pStmt.setInt(1, user.getIdUser());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				int idItem = rs.getInt("idItem");
				String nameItem = rs.getString("nameItem");

				item.setIdItem(idItem);
				item.setNameItem(nameItem);

				items.add(item);
			}

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING OPEN SELL FOR USER");
			sqle.printStackTrace();
		}

		return items;
	}

	public static final String SELECT_ALL_BY_USER = "SELECT idItem FROM SOLD_ITEMS WHERE idUser = ?;";

	@Override
	public List<Item> selectAllByUser(User user) {

		List<Item> items = new ArrayList<Item>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_BY_USER);
			pStmt.setInt(1, user.getIdUser());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				Item item = new Item();
				item.setIdItem(rs.getInt("idItem"));

				items.add(item);
			}

		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING ALL ITEMS OF USER id=" + user.getIdUser());
			sqle.printStackTrace();
		}

		return items;
	}
}
