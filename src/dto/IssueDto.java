package dto;

import java.time.LocalDate;
import java.util.List;

public class IssueDto {

    private String issueId;
    private String memberId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private List<IssueDetailDto> issueDetails;

    // Constructor with required fields
    public IssueDto(String issueId, String memberId, LocalDate issueDate, LocalDate dueDate) {
        this.issueId = issueId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    // Constructor with all fields
    public IssueDto(String issueId, String memberId, LocalDate issueDate, LocalDate dueDate, List<IssueDetailDto> issueDetails) {
        this.issueId = issueId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.issueDetails = issueDetails;
    }

    // Getters and Setters
    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public List<IssueDetailDto> getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(List<IssueDetailDto> issueDetails) {
        this.issueDetails = issueDetails;
    }

    @Override
    public String toString() {
        return "IssueDto [issueId=" + issueId + ", memberId=" + memberId + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", issueDetails=" + issueDetails + "]";
    }

    
}
