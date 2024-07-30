package dao.custom.impl;

import dao.custom.BookDao;
import db.DBConnection;
import entity.BookEntity;

import java.sql.*;
import java.util.ArrayList;

public class BookDaoImpl implements BookDao {
    private Connection connection;

    public BookDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(BookEntity entity) throws Exception {
        String query = "INSERT INTO book (BookID, Title, Author, CategoryID, Publisher, Year) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, entity.getBookID());
            stmt.setString(2, entity.getTitle());
            stmt.setString(3, entity.getAuthor());
            stmt.setInt(4, entity.getCategoryID());
            stmt.setString(5, entity.getPublisher());
            stmt.setInt(6, entity.getYear());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(BookEntity entity) throws Exception {
        String query = "UPDATE book SET Title = ?, Author = ?, CategoryID = ?, Publisher = ?, Year = ? WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getTitle());
            stmt.setString(2, entity.getAuthor());
            stmt.setInt(3, entity.getCategoryID());
            stmt.setString(4, entity.getPublisher());
            stmt.setInt(5, entity.getYear());
            stmt.setInt(6, entity.getBookID());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String code) throws Exception {
        String query = "DELETE FROM book WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(code));
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public BookEntity getBookEntity(String code) throws Exception {
        String query = "SELECT * FROM book WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(code));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BookEntity(
                    rs.getInt("BookID"),
                    rs.getString("Title"),
                    rs.getString("Author"),
                    rs.getInt("CategoryID"),
                    rs.getString("Publisher"),
                    rs.getInt("Year")
                );
            }
        }
        return null;
    }

    @Override
    public ArrayList<BookEntity> getAll() throws Exception {
        ArrayList<BookEntity> books = new ArrayList<>();
        String query = "SELECT * FROM book";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                books.add(new BookEntity(
                    rs.getInt("BookID"),
                    rs.getString("Title"),
                    rs.getString("Author"),
                    rs.getInt("CategoryID"),
                    rs.getString("Publisher"),
                    rs.getInt("Year")
                ));
            }
        }
        return books;
    }

    @Override
    public boolean updateBookQty(String bookId, int qty) throws SQLException {
        String sql = "UPDATE book SET quantity = quantity + ? WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, qty);
            stmt.setString(2, bookId);
            return stmt.executeUpdate() > 0;
        }
    }
}
