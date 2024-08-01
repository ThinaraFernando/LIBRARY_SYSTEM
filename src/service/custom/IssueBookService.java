package service.custom;

import java.sql.SQLException;
import java.util.List;

import dto.IssueDetailDto;
import dto.IssueDto;
import service.SuperService;

public interface IssueBookService extends SuperService {

    public String placeIssue(IssueDto issueDto) throws Exception;

    String getMemberNameById(String memberId) throws SQLException; 


    List<IssueDetailDto> getAllIssueDetails() throws SQLException;

}
