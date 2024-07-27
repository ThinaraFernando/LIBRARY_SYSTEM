package service.custom;

import java.util.ArrayList;

import dto.BookDto;
import service.SuperService;

public interface BookService extends SuperService {

    public boolean add(BookDto dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(BookDto dto) throws Exception;

    public BookDto get(String id) throws Exception;

    public ArrayList<BookDto> getAll() throws Exception;
}
