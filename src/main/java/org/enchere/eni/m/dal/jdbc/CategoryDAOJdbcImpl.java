package org.enchere.eni.m.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.enchere.eni.m.bo.Category;
import org.enchere.eni.m.dal.CategoryDAO;

public class CategoryDAOJdbcImpl implements CategoryDAO {
	
	private static final String INIT_TABLE = """
			DROP TABLE IF EXISTS Categories;
			
			CREATE TABLE Categories (
			    idCategory INTEGER IDENTITY(1,1) NOT NULL,
			    wording VARCHAR(30) NOT NULL
			);

			ALTER TABLE Categories ADD constraint category_pk PRIMARY KEY (idCategory);
			""";
	
	private static final String INSERT_CATEGORY = """
			INSERT INTO CATEGORIES (wording) VALUES (?);
			""";

	@Override
	public void initDataset() {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			stmt.executeUpdate(INIT_TABLE);
			
		} catch (SQLException sqle) {
			System.out.println("ERROR WHEN CREATING Categories TABLE");
			sqle.printStackTrace();
		}
		
		Category c1 = new Category("Informatique");
		Category c2 = new Category("Ameublement");
		Category c3 = new Category("Sport Loisirs");
		Category c4 = new Category("Vetements");
		
		insert(c1);
		insert(c2);
		insert(c3);
		insert(c4);
		
	}

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
	
	
	
	

}
