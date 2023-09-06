package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.bo.ItemSold;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.bo.Withdraw;
import org.enchere.eni.m.dal.ItemSoldDAO;

public class ItemSoldDAOJdbcImpl implements ItemSoldDAO {

	public final static String SELECT_ALL = """

			SELECT * FROM SOLD_ITEMS
			JOIN USERS ON SOLD_ITEMS.idUser = USERS.idUser
			JOIN CATEGORIES ON SOLD_ITEMS.idCategory = CATEGORIES.idCategory
			JOIN WITHDRAW ON SOLD_ITEMS.idItem = WITHDRAW.idItem
			LEFT JOIN BIDS ON SOLD_ITEMS.idItem = BIDS.idItem;
			""";

	@Override
	public List<ItemSold> selectAll() {

		List<ItemSold> itemSold = new ArrayList<ItemSold>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			int idPreviousItem = 0;
			ItemSold itemInProgress = null;
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
				
					

					// enchaine avec tous les champs de user puis crée un new User

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

					User u = new User(idUser, alias, surname, firstName, email, phone, street, zipCode, city,
							passwordUser, credit, isAdmin);
					
					

					String street1 = rs.getString("street");
					String zipCode1 = rs.getString("zipCode");
					String city1 = rs.getString("city");

					Withdraw w = new Withdraw(street1, zipCode1, city1);

					
					
					int idCategory = rs.getInt("idCategory");
					String wording = rs.getString("wording");

					Category c = new Category(idCategory, wording);

					itemInProgress = new ItemSold(idItem, nameItem, descriptionItem, bidStartDate,
							bidEndDate, initialPrice, sellingPrice, stateItem, u, w, c);	
				
					idPreviousItem = idItem;
					
				}
					
					int idBid = rs.getInt("idBid");
					LocalDate bidDate = rs.getDate("bidDate").toLocalDate();
					int bidAmount = rs.getInt("bidAmount");
					
					Bid bid = new Bid(idBid, bidDate, bidAmount);
					
					itemInProgress.addBid(bid);

			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemSold;
	}
}
