package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.DaoType;
import dao.custom.BookDao;
import dto.BookDto;
import entity.BookEntity;
import service.custom.BookService;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = DaoFactory.getInstance().getDAO(DaoType.Books);

    @Override
    public boolean add(BookDto dto) throws Exception {
        BookEntity entity = new BookEntity(dto.getBookID(), dto.getTitle(), dto.getAuthor(), dto.getCategoryID(), dto.getPublisher(), dto.getYear());
        return bookDao.add(entity);    }

    @Override
    public boolean delete(String id) throws Exception {
        return bookDao.delete(id);
    }

    @Override
    public boolean update(BookDto dto) throws Exception {
        BookEntity entity = new BookEntity(dto.getBookID(), dto.getTitle(), dto.getAuthor(), dto.getCategoryID(), dto.getPublisher(), dto.getYear());
        return bookDao.update(entity);    }

    @Override
    public BookDto get(String id) throws Exception {
        BookEntity entity = bookDao.getBookEntity(id);
        if (entity != null) {
            return new BookDto(entity.getBookID(), entity.getTitle(), entity.getAuthor(), entity.getCategoryID(), entity.getPublisher(), entity.getYear());
        }
        return null;    }

    @Override
    public ArrayList<BookDto> getAll() throws Exception {
        ArrayList<BookEntity> entities = bookDao.getAll();
        ArrayList<BookDto> dtoList = new ArrayList<>();
        for (BookEntity entity : entities) {
            dtoList.add(new BookDto(entity.getBookID(), entity.getTitle(), entity.getAuthor(), entity.getCategoryID(), entity.getPublisher(), entity.getYear()));
        }
        return dtoList;
    }   
 }


