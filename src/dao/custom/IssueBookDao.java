package dao.custom;

import java.sql.SQLException;
import java.util.List;

import dao.SuperDao;
import dto.IssueDto;

public interface IssueBookDao extends SuperDao {

    boolean createIssue(IssueDto issueDTO) throws SQLException;

    IssueDto getIssueById(String issueId) throws SQLException;

    List<IssueDto> getAllIssues() throws SQLException;

    String getMemberNameById(String memberId) throws SQLException;


}
