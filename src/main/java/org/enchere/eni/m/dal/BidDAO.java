package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.Bid;
import org.enchere.eni.m.bo.Item;
import org.enchere.eni.m.bo.User;

public interface BidDAO {

	void insert(Bid bid);

	List<Bid> selectAllByItem(Item item);

	Bid selectMaxBid(Item item);

	void deleteUserBids(User user);

	List<Bid> selectWinningBids();

	Bid select(int idBid);

	List<Bid> selectUserWinningBids(User user);
}
