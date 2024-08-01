package service.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DaoFactory;
import dao.DaoType;
import dao.custom.BookDao;
import dao.custom.IssueBookDao;
import dao.custom.IssueBookDetailDao;
import db.DBConnection;
import dto.IssueDetailDto;
import dto.IssueDto;
import service.custom.IssueBookService;

public class IssueBookServiceImpl implements IssueBookService{

    private IssueBookDao issueBookDao = (IssueBookDao) DaoFactory.getInstance().getDAO(DaoType.IssueBooks);
    private IssueBookDetailDao issueBookDetailDao = (IssueBookDetailDao) DaoFactory.getInstance().getDAO(DaoType.IssueBooksDetails);
    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDAO(DaoType.Books);

    @Override
    public String placeIssue(IssueDto issueDto) throws Exception {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean issueCreated = issueBookDao.createIssue(issueDto);

            if (!issueCreated) {
                connection.rollback();
                throw new SQLException("Failed to create issue record.");
            }

            List<IssueDetailDto> issueDetails = issueDto.getIssueDetails();
            for (IssueDetailDto detail : issueDetails) {
                boolean detailCreated = issueBookDetailDao.createIssueDetail(detail);

                if (!detailCreated) {
                    connection.rollback();
                    throw new SQLException("Failed to create issue detail record.");
                }

                boolean bookUpdated = bookDao.updateBookQty(detail.getBookId(), -1); // Assuming -1 for decrementing the quantity

                if (!bookUpdated) {
                    connection.rollback();
                    throw new SQLException("Failed to update book quantity.");
                }
            }

            connection.commit();
            return issueDto.getIssueId();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public String getMemberNameById(String memberId) throws SQLException {
        return issueBookDao.getMemberNameById(memberId);
    }

    

    
}
