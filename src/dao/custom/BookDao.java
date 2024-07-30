package dao.custom;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.SuperDao;
import entity.BookEntity;

public interface BookDao extends SuperDao {

    boolean add(BookEntity entity) throws Exception;

    boolean update(BookEntity entity) throws Exception;

    boolean delete(String code) throws Exception;

    BookEntity getBookEntity(String code) throws Exception;

    ArrayList<BookEntity> getAll() throws Exception;

    boolean updateBookQty(String bookId, int qty) throws SQLException;
}
