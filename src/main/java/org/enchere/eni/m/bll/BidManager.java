package org.enchere.eni.m.bll;

import org.enchere.eni.m.bo.Bid;
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
	
	public void initDataset() {
		DAOFactory.getBidDAO().initDataset();
	}
	
	public void insert(Bid bid) {
		DAOFactory.getBidDAO().insert(bid);
	}
	

}