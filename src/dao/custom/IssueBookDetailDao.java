package dao.custom;

import java.sql.SQLException;
import java.util.List;

import dao.SuperDao;
import dto.IssueDetailDto;

public interface IssueBookDetailDao extends SuperDao{

    boolean createIssueDetail(IssueDetailDto issueDetailDTO) throws SQLException;


    IssueDetailDto getIssueDetailById(String issueDetailId) throws SQLException;


    List<IssueDetailDto> getAllIssueDetails() throws SQLException;


}
