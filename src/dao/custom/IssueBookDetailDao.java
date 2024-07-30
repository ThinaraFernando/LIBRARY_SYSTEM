package dao.custom;

import java.sql.SQLException;

import dao.SuperDao;
import dto.IssueDetailDto;

public interface IssueBookDetailDao extends SuperDao{

    boolean createIssueDetail(IssueDetailDto issueDetailDTO) throws SQLException;


    IssueDetailDto getIssueDetailById(String issueDetailId) throws SQLException;

}
