package service.custom;

import java.util.List;

import dto.ReturnBookDto;
import service.SuperService;

public interface ReturnBookService extends SuperService  {

    void addReturnBook(ReturnBookDto returnBookDto) throws Exception;

    ReturnBookDto getReturnBook(int returnId) throws Exception;

    List<ReturnBookDto> getAllReturnBooks() throws Exception;

    void updateReturnBook(ReturnBookDto returnBookDto) throws Exception;

    void deleteReturnBook(int returnId) throws Exception;

    void calculateFine(ReturnBookDto returnBookDto) throws Exception;

    ReturnBookDto getReturnBookDetailsByMemberId(int memberId) throws Exception;

}
