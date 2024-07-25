package service.custom;

import java.util.ArrayList;

import dto.BookCategoryDto;
import service.SuperService;

public interface BookCategoryService  extends SuperService{
    
    public String save(BookCategoryDto dto) throws Exception;
    public String update(BookCategoryDto dto) throws Exception;
    public String delete(String code) throws Exception;
    public BookCategoryDto get(String code) throws Exception;
    public ArrayList<BookCategoryDto> getAll() throws Exception;

}
