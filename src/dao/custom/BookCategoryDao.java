package dao.custom;

import java.util.ArrayList;


import dao.SuperDao;
import entity.BookCategoryEntity;

public interface BookCategoryDao extends SuperDao{

    String add(BookCategoryEntity entity) throws Exception;

    String update(BookCategoryEntity entity) throws Exception;

    String delete(String code) throws Exception;

    String gBookCategoryEntity(String code) throws Exception;

    ArrayList<BookCategoryEntity> getAll() throws Exception;
 

}
