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
		SELECT idCategory, wording FROM CATEGORIES;
		""";
	
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
	
	public static final String SELECT_CATEGORY_BY_ID = """
			SELECT wording FROM CATEGORIES WHERE idCategory = ?;
			""";
	@Override
	public Category selectById(int idCategory) {
		
		Category category = new Category();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEGORY_BY_ID);
			pStmt.setInt(1, idCategory);
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				category.setIdCategory(idCategory);
				category.setWording(rs.getString("wording"));
			}
			 
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN SELECTING CATGORY WITH ID=" + idCategory);
			sqle.printStackTrace();
		}
		
		return category;
		
	}
	
	public static final String SELECT_CATEGORY_BY_WORDING = """
			SELECT idCategory, wording FROM Categories WHERE wording = ?;
			""";
	
	@Override
	public Category selectByWording(String wording) {
		
		Category category = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEGORY_BY_WORDING);
			pStmt.setString(1, wording);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				int idCategory = rs.getInt("idCategory");
				category = new Category(idCategory, wording);
			}
			
		} catch (SQLException sqle) {
			System.out.println("ERROR SELECT CATEGORY BY WORDING");
			sqle.printStackTrace();
		}
		return category;
	}
	
	public static final String UPDATE_CATEGORY = """
			UPDATE CATEGORIES SET wording = ? WHERE idCategory = ?;
			""";
	@Override
	public void update(Category category) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_CATEGORY);
			pStmt.setString(1, category.wording);
			pStmt.setInt(2, category.getIdCategory());
			
			pStmt.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("ERROR - CANNOT UPDATE CATEGORY");
			sqle.printStackTrace();
		}
		
	}
	
	public static final String DELETE = """
			DELETE FROM CATEGORIES WHERE idCategory = ?;
			""";
	
	public void deleteById(int idCategory) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, idCategory);
			
			pStmt.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN DELETING CATEGORY");
			sqle.printStackTrace();
		}
		
		
	}
	
	
	
}