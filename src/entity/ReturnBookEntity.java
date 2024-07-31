package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReturnBookEntity {

     private int returnId;
    private int memberId;
    private String memberName;
    private int bookId;
    private LocalDate returnDate;
    private int dateElapsed;
    private BigDecimal fine;

    public ReturnBookEntity() {

    }

    public ReturnBookEntity(int returnId, int memberId, String memberName, int bookId, LocalDate returnDate,
            int dateElapsed, BigDecimal fine) {
        this.returnId = returnId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.returnDate = returnDate;
        this.dateElapsed = dateElapsed;
        this.fine = fine;
    }

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getDateElapsed() {
        return dateElapsed;
    }

    public void setDateElapsed(int dateElapsed) {
        this.dateElapsed = dateElapsed;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "ReturnBookEntity [returnId=" + returnId + ", memberId=" + memberId + ", memberName=" + memberName + ", bookId=" + bookId + ", returnDate=" + returnDate + ", dateElapsed=" + dateElapsed + ", fine=" + fine + "]";
    }

    

}
