package dao;

import dao.custom.impl.BookCategoryDaoImpl;
import dao.custom.impl.BookDaoImpl;


public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DaoFactory() : daoFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperDao> T getDAO(DaoType daoType) {
        switch (daoType) {
            case BookCategory:
                return (T) new BookCategoryDaoImpl();
            case Books:
                return (T) new BookDaoImpl();
            default:
                return null;
        }
    }
}
