package dao.custom.impl;

import java.sql.*;
import java.util.ArrayList;
import dao.custom.BookCategoryDao;
import entity.BookCategoryEntity;
import db.DBConnection;

public class BookCategoryDaoImpl implements BookCategoryDao {
    private Connection connection;

    public BookCategoryDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(BookCategoryEntity entity) throws Exception {
        String query = "INSERT INTO BookCategory (categoryName) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getCategoryName());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean update(BookCategoryEntity entity) throws Exception {
        String query = "UPDATE BookCategory SET categoryName = ? WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getCategoryName());
            stmt.setInt(2, entity.getCategoryId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(String code) throws Exception {
        String query = "DELETE FROM BookCategory WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(code));
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public BookCategoryEntity getBookCategoryEntity(String code) throws Exception {
        String query = "SELECT * FROM BookCategory WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(code));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BookCategoryEntity(rs.getInt("categoryId"), rs.getString("categoryName"));
            }
        }
        return null;
    }

    @Override
    public ArrayList<BookCategoryEntity> getAll() throws Exception {
        ArrayList<BookCategoryEntity> categories = new ArrayList<>();
        String query = "SELECT * FROM BookCategory";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                categories.add(new BookCategoryEntity(rs.getInt("categoryId"), rs.getString("categoryName")));
            }
        }
        return categories;
    }
}
