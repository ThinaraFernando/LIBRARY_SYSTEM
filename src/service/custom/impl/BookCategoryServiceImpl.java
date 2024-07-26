package service.custom.impl;

import java.util.ArrayList;
import dao.DaoFactory;
import dao.DaoType;
import dao.custom.BookCategoryDao;
import dto.BookCategoryDto;
import entity.BookCategoryEntity;
import service.custom.BookCategoryService;

public class BookCategoryServiceImpl implements BookCategoryService {

    BookCategoryDao bookCategoryDao = DaoFactory.getInstance().getDAO(DaoType.BookCategory);

    @Override
    public boolean add(BookCategoryDto dto) throws Exception {
        return bookCategoryDao.add(new BookCategoryEntity(dto.getCategoryId(), dto.getCategoryName()));
    }

    @Override
    public boolean update(BookCategoryDto dto) throws Exception {
        return bookCategoryDao.update(new BookCategoryEntity(dto.getCategoryId(), dto.getCategoryName()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return bookCategoryDao.delete(id);
    }

    @Override
    public BookCategoryDto get(String id) throws Exception {
        BookCategoryEntity entity = bookCategoryDao.getBookCategoryEntity(id);
        return entity != null ? new BookCategoryDto(entity.getCategoryId(), entity.getCategoryName()) : null;
    }

    @Override
    public ArrayList<BookCategoryDto> getAll() throws Exception {
        ArrayList<BookCategoryEntity> all = bookCategoryDao.getAll();
        ArrayList<BookCategoryDto> dtos = new ArrayList<>();
        for (BookCategoryEntity entity : all) {
            dtos.add(new BookCategoryDto(entity.getCategoryId(), entity.getCategoryName()));
        }
        return dtos;
    }
}
