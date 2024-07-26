package service.custom;

import java.util.ArrayList;

import dto.BookCategoryDto;
import service.SuperService;

public interface BookCategoryService  extends SuperService{

    public boolean add(BookCategoryDto dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(BookCategoryDto dto) throws Exception;

    public BookCategoryDto get(String id) throws Exception;

    public ArrayList<BookCategoryDto> getAll() throws Exception;
    
    

}
