package service.custom.impl;

import java.math.BigDecimal;
import java.util.List;

import dao.DaoFactory;
import dao.DaoType;
import dao.custom.ReturnBookDao;
import dto.ReturnBookDto;
import entity.ReturnBookEntity;
import service.custom.ReturnBookService;

public class ReturnBookServiceImpl implements ReturnBookService {

        ReturnBookDao returnBookDao = DaoFactory.getInstance().getDAO(DaoType.ReturnBook);


    @Override
    public void addReturnBook(ReturnBookDto returnBookDto) throws Exception {
            ReturnBookEntity returnBookEntity = new ReturnBookEntity(
            returnBookDto.getReturnId(),
            returnBookDto.getMemberId(),
            returnBookDto.getMemberName(),
            returnBookDto.getBookId(),
            returnBookDto.getReturnDate(),
            returnBookDto.getDateElapsed(),
            returnBookDto.getFine()
        );
        returnBookDao.addReturnBook(returnBookEntity);  
      }

    @Override
    public ReturnBookDto getReturnBook(int returnId) throws Exception {
        ReturnBookEntity returnBookEntity = returnBookDao.getReturnBook(returnId);
        return new ReturnBookDto(
            returnBookEntity.getReturnId(),
            returnBookEntity.getMemberId(),
            returnBookEntity.getMemberName(),
            returnBookEntity.getBookId(),
            returnBookEntity.getReturnDate(),
            returnBookEntity.getDateElapsed(),
            returnBookEntity.getFine()
        );   
     }

    @Override
    public List<ReturnBookDto> getAllReturnBooks() throws Exception {
        List<ReturnBookEntity> returnBookEntities = returnBookDao.getAllReturnBooks();
        return returnBookEntities.stream().map(entity -> new ReturnBookDto(
            entity.getReturnId(),
            entity.getMemberId(),
            entity.getMemberName(),
            entity.getBookId(),
            entity.getReturnDate(),
            entity.getDateElapsed(),
            entity.getFine() )).toList();   
     }

    @Override
    public void updateReturnBook(ReturnBookDto returnBookDto) throws Exception {
        ReturnBookEntity returnBookEntity = new ReturnBookEntity(
            returnBookDto.getReturnId(),
            returnBookDto.getMemberId(),
            returnBookDto.getMemberName(),
            returnBookDto.getBookId(),
            returnBookDto.getReturnDate(),
            returnBookDto.getDateElapsed(),
            returnBookDto.getFine()
        );
        returnBookDao.updateReturnBook(returnBookEntity);   
     }

    @Override
    public void deleteReturnBook(int returnId) throws Exception {
        returnBookDao.deleteReturnBook(returnId);
    }

    @Override
    public void calculateFine(ReturnBookDto returnBookDto) throws Exception {
        BigDecimal fine = BigDecimal.valueOf(returnBookDto.getDateElapsed() * 100);
        returnBookDto.setFine(fine);
        ReturnBookEntity returnBookEntity = new ReturnBookEntity(
            returnBookDto.getReturnId(),
            returnBookDto.getMemberId(),
            returnBookDto.getMemberName(),
            returnBookDto.getBookId(),
            returnBookDto.getReturnDate(),
            returnBookDto.getDateElapsed(),
            returnBookDto.getFine()
        );
        returnBookDao.updateReturnBook(returnBookEntity);   
     }

    @Override
    public ReturnBookDto getReturnBookDetailsByMemberId(int memberId) throws Exception {
        ReturnBookEntity returnBookEntity = returnBookDao.findByMemberId(memberId);
        
        if (returnBookEntity == null) {
            throw new IllegalArgumentException("No return book details found for memberId: " + memberId);
        }
        
        ReturnBookDto returnBookDto = new ReturnBookDto();
        returnBookDto.setReturnId(returnBookEntity.getReturnId());
        // Set other properties of returnBookDto as needed
        return returnBookDto;
    }
 }

   




