package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.ItemSold;
import org.enchere.eni.m.dal.ItemSoldDAO;

public class ItemSoldDAOJdbcImpl implements ItemSoldDAO {

	public final static String SELECT_ALL = """
			
			SELECT * FROM SOLD_ITEMS
			JOIN USERS ON SOLD_ITEMS.idUser = USERS.idUser
			JOIN CATEGORIES ON SOLD_ITEMS.idCategory = CATEGORIES.idCategory
			JOIN WITHDRAW ON SOLD_ITEMS.idItem = WITHDRAW.idItem
			JOIN BIDS ON SOLD_ITEMS.idItem = BIDS.idItem;	
			""";
	
	@Override
	public List<ItemSold> selectAll(){
	
	List <ItemSold> itemsSold = new ArrayList<ItemSold>();
	
	try(Connection cnx = ConnectionProvider.getConnection()){
		
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		
		int idPreviousItem = 0;
		ItemSold itemSold = null;
		while(rs.next()) {
			
			int itemInProgress = rs.getInt("idItemSold");
			if (itemInProgress != idPreviousItem) {
				String nameItem = rs.getString("nameItem");
				String descriptionItem = rs.getString("descriptionItem");
				LocalDate bidStartDate = rs.getDate("bidStartDate").toLocalDate();
				LocalDate bidEndDate = rs.getDate("bidEndDate").toLocalDate();
				int initialPrice = rs.getInt("initialPrice");
				int sellingPrice = rs.getInt("sellingPrice");
				int stateItem = rs.getInt("stateItem");
				
				
				itemSold = new ItemSold(itemInProgress, nameItem, descriptionItem,
						bidStartDate, bidEndDate, initialPrice, sellingPrice, stateItem);
				itemsSold.add(itemSold);
				
				idPreviousItem = itemInProgress;	
				
				int idBid = rs.getInt("idBid");
				LocalDate bidDate = rs.getDate("bidDate").toLocalDate();
				int bidAmount = rs.getInt("bidAmount");
				Bid bid = new Bid(idBid, bidDate, bidAmount);
				itemInProgress.addBid(bid);
			}
			catch(SQLException e ) {
				e.printStackTrace();
			}
			return itemSold;
				
}}}}
	