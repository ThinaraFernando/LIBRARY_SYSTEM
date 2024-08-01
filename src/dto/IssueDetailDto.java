package dto;

public class IssueDetailDto {

    private String IssueDetailId;
    private String IssueId;
    private String BookId;
    private IssueDto issueDto;


    public IssueDetailDto() {

    }

    public IssueDetailDto(String issueDetailId, String issueId, String bookId) {
        IssueDetailId = issueDetailId;
        IssueId = issueId;
        BookId = bookId;
    }

    public String getIssueDetailId() {
        return IssueDetailId;
    }

    public void setIssueDetailId(String issueDetailId) {
        IssueDetailId = issueDetailId;
    }

    public String getIssueId() {
        return IssueId;
    }

    public void setIssueId(String issueId) {
        IssueId = issueId;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public IssueDto getIssueDto() {
        return issueDto;
    }

    public void setIssueDto(IssueDto issueDto) {
        this.issueDto = issueDto;
    }

    @Override
    public String toString() {
        return "IssueDetailDto [IssueDetailId=" + IssueDetailId + ", IssueId=" + IssueId + ", BookId=" + BookId + "]"; }

    

}
