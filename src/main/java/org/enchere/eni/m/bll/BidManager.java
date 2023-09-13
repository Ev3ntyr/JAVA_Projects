package org.enchere.eni.m.bll;

import java.util.List;

import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;
import org.enchere.eni.m.dal.DAOFactory;

public class BidManager {

	private static BidManager instance;

	public static BidManager getInstance() {
		if (instance == null) {
			instance = new BidManager();
		}
		return instance;
	}

	private BidManager() {}
	
	public void insert(Bid bid) {
		DAOFactory.getBidDAO().insert(bid);
	}
	
	public List<Bid> selectAllByItem(Item item) {
		return DAOFactory.getBidDAO().selectAllByItem(item);
	}

	public Bid selectMaxBid(Item item) {
		return DAOFactory.getBidDAO().selectMaxBid(item);
	}
	
	public void deleteUserBids(User user) {
		DAOFactory.getBidDAO().deleteUserBids(user);
	}

}