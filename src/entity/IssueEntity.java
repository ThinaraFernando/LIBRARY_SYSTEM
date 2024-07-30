package entity;

import java.time.LocalDate;

public class IssueEntity {

    private String IssueId;

    private String MemberId;

    private LocalDate IssueDate;

    private LocalDate DueDate;

    public IssueEntity() {

    }

    public IssueEntity(String issueId, String memberId, LocalDate issueDate, LocalDate dueDate) {
        IssueId = issueId;
        MemberId = memberId;
        IssueDate = issueDate;
        DueDate = dueDate;
    }

    public String getIssueId() {
        return IssueId;
    }

    public void setIssueId(String issueId) {
        IssueId = issueId;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public LocalDate getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        IssueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }

    @Override
    public String toString() {
        return "IssueEntity [IssueId=" + IssueId + ", MemberId=" + MemberId + ", IssueDate=" + IssueDate + ", DueDate="  + DueDate + "]";
    }

    

}
