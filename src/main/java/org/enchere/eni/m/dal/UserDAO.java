package org.enchere.eni.m.dal;

import java.util.List;

import org.enchere.eni.m.bo.User;

public interface UserDAO {
	
	void createUser(User newUser);
	User selectById(int idUser);
	boolean checkPassword (String password, String encryptedPassword);
	boolean checkEmail (String email);
	boolean checkAlias (String alias);
	User selectByAlias(String alias);
	void update(User user);
	void delete(int idUser);
	void deactivate(int idUser);
	List<User> selectAll();
	void adminDelete(int idUser);
	
}
