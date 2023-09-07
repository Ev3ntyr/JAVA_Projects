package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.dal.CategoryDAO;

public class CategoryDAOJdbcImpl implements CategoryDAO {
	
	private static final String INSERT_CATEGORY = """
			INSERT INTO CATEGORIES (wording) VALUES (?);
			""";

	@Override
	public void insert(Category newCategory) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(INSERT_CATEGORY, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, newCategory.getWording());
			
			pStmt.executeUpdate();
			
			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				newCategory.setIdCategory(rs.getInt(1));
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN INSERTING Category IN DATABASE");
			sqle.printStackTrace();
		}
		
	}
	
	
	public static final String SELECT_CATEGORY =  """ 
			
		SELECT * FROM CATEGORIES; """;
	
	@Override
	public List<Category> select() {
		
		List<Category> categories = new ArrayList<Category>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_CATEGORY);
			
			while(rs.next()) {
				int idCategory = rs.getInt("idCategory");
				String wording = rs.getString("wording");
				
				Category c = new Category(idCategory, wording);
				categories.add(c);
				
			}
			
		} catch (SQLException exception) {
			exception.printStackTrace();
			System.out.println("ERROR WHEN SELECTING CATEGORY");
		}
		
		
	
return categories;
}
}