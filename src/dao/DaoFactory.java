package dao;

import dao.custom.impl.BookCategoryDaoImpl;
import dao.custom.impl.BookDaoImpl;
import dao.custom.impl.IssueBookDaoImpl;
import dao.custom.impl.IssueBookDetailDaoImpl;
import dao.custom.impl.MemberDaoImpl;


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
            case Members:
                return (T) new MemberDaoImpl();
            case IssueBooks:
                return (T) new IssueBookDaoImpl();
            case IssueBooksDetails:
                return (T) new IssueBookDetailDaoImpl();
            
            default:
                return null;
        }
    }
}
