package dao.custom;

import java.util.ArrayList;
import dao.SuperDao;
import entity.BookCategoryEntity;

public interface BookCategoryDao extends SuperDao {

    boolean add(BookCategoryEntity entity) throws Exception;

    boolean update(BookCategoryEntity entity) throws Exception;

    boolean delete(String code) throws Exception;

    BookCategoryEntity getBookCategoryEntity(String code) throws Exception;

    ArrayList<BookCategoryEntity> getAll() throws Exception;
    
}
