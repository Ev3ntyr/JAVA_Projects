package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Item;

public interface BidDAO {
	
	void insert(Bid bid);
	List<Bid> selectAllByItem(Item item);
	Bid selectMaxBid(Item item);
}
