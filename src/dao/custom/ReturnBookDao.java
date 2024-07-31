package dao.custom;

import java.util.List;

import dao.SuperDao;
import entity.ReturnBookEntity;

public interface ReturnBookDao extends SuperDao {

    void addReturnBook(ReturnBookEntity returnBookEntity) throws Exception;

    ReturnBookEntity getReturnBook(int returnId) throws Exception;

    List<ReturnBookEntity> getAllReturnBooks() throws Exception;

    void updateReturnBook(ReturnBookEntity returnBookEntity) throws Exception;

    void deleteReturnBook(int returnId) throws Exception;

    void calculateFine(ReturnBookEntity returnBookEntity) throws Exception;

    ReturnBookEntity getReturnBookDetailsByMemberId(int memberId) throws Exception;

}
