package service.custom.impl;

import dao.DaoFactory;
import dao.DaoType;
import dao.custom.impl.QueryDaoImpl;
import service.custom.DefaultService;

public class DefaultServiceImpl implements DefaultService {

    QueryDaoImpl queryDAO = DaoFactory.getInstance().getDAO(DaoType.QUERY);

    @Override
    public int getTotalBooks() throws Exception {
        return queryDAO.getTotalBooks();
    }

    @Override
    public int getTotalIssuedBooks() throws Exception {
        return queryDAO.getTotalIssuedBooks();
    }

    @Override
    public int getTotalMembers() throws Exception {
        return queryDAO.getTotalMembers();
    }

    @Override
    public int getTotalReturnBooks() throws Exception {
        return queryDAO.getTotalReturnBooks();
    }

}
