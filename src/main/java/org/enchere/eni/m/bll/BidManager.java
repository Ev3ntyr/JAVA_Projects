package org.enchere.eni.m.bll;

import java.util.List;

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

	private BidManager() {
	}

}