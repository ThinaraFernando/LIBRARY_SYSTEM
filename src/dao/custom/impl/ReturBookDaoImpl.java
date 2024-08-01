package dao.custom.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import dao.custom.ReturnBookDao;
import db.DBConnection;
import entity.ReturnBookEntity;

public class ReturBookDaoImpl implements ReturnBookDao {

    private Connection connection;

    public ReturBookDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addReturnBook(ReturnBookEntity returnBookEntity) throws Exception {
 String sql = "INSERT INTO BookReturn (MemberID, MemberName, BookID, ReturnDate, DateElapsed, Fine) VALUES (?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, returnBookEntity.getMemberId());
            pstmt.setString(2, returnBookEntity.getMemberName());
            pstmt.setInt(3, returnBookEntity.getBookId());
            pstmt.setDate(4, Date.valueOf(returnBookEntity.getReturnDate()));
            pstmt.setInt(5, returnBookEntity.getDateElapsed());
            pstmt.setBigDecimal(6, returnBookEntity.getFine());
            pstmt.executeUpdate();
        }    }

    @Override
    public ReturnBookEntity getReturnBook(int returnId) throws Exception {
 String sql = "SELECT * FROM BookReturn WHERE ReturnID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
             
            pstmt.setInt(1, returnId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ReturnBookEntity(
                        rs.getInt("ReturnID"),
                        rs.getInt("MemberID"),
                        rs.getString("MemberName"),
                        rs.getInt("BookID"),
                        rs.getDate("ReturnDate").toLocalDate(),
                        rs.getInt("DateElapsed"),
                        rs.getBigDecimal("Fine")
                    );
                }
            }
        }
        return null;   
     }

    @Override
    public List<ReturnBookEntity> getAllReturnBooks() throws Exception {
        String sql = "SELECT * FROM BookReturn";
        List<ReturnBookEntity> returnBooks = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             
             ResultSet rs = pstmt.executeQuery(sql) ){
            while (rs.next()) {
                returnBooks.add(new ReturnBookEntity(
                    rs.getInt("ReturnID"),
                    rs.getInt("MemberID"),
                    rs.getString("MemberName"),
                    rs.getInt("BookID"),
                    rs.getDate("ReturnDate").toLocalDate(),
                    rs.getInt("DateElapsed"),
                    rs.getBigDecimal("Fine")
                ));
            }
        }
        return returnBooks;  
      }

    @Override
    public void updateReturnBook(ReturnBookEntity returnBookEntity) throws Exception {
        String sql = "UPDATE BookReturn SET MemberID = ?, MemberName = ?, BookID = ?, ReturnDate = ?, DateElapsed = ?, Fine = ? WHERE ReturnID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            
            pstmt.setInt(1, returnBookEntity.getMemberId());
            pstmt.setString(2, returnBookEntity.getMemberName());
            pstmt.setInt(3, returnBookEntity.getBookId());
            pstmt.setDate(4, Date.valueOf(returnBookEntity.getReturnDate()));
            pstmt.setInt(5, returnBookEntity.getDateElapsed());
            pstmt.setBigDecimal(6, returnBookEntity.getFine());
            pstmt.setInt(7, returnBookEntity.getReturnId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteReturnBook(int returnId) throws Exception {
        String sql = "DELETE FROM BookReturn WHERE ReturnID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            
            pstmt.setInt(1, returnId);
            pstmt.executeUpdate();
        }  
      }

   

    @Override
    public void calculateFine(ReturnBookEntity returnBookEntity) throws Exception {
        final int finePerDay = 100;

        LocalDate returnDate = returnBookEntity.getReturnDate();
        LocalDate dueDate = returnDate.minusDays(returnBookEntity.getDateElapsed());

         if (returnDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            BigDecimal fine = BigDecimal.valueOf(daysOverdue * finePerDay);
            returnBookEntity.setFine(fine);
        } else {
            returnBookEntity.setFine(BigDecimal.ZERO); 
        }

        
        String sql = "UPDATE BookReturn SET Fine = ? WHERE ReturnID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBigDecimal(1, returnBookEntity.getFine());
            pstmt.setInt(2, returnBookEntity.getReturnId());
            pstmt.executeUpdate();
        }
    }
    
    

    @Override
    public ReturnBookEntity getReturnBookDetailsByMemberId(int memberId) throws Exception {
        String sql = "SELECT * FROM BookReturn WHERE MemberID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ReturnBookEntity(
                        rs.getInt("ReturnID"),
                        rs.getInt("MemberID"),
                        rs.getString("MemberName"),
                        rs.getInt("BookID"),
                        rs.getDate("ReturnDate").toLocalDate(),
                        rs.getInt("DateElapsed"),
                        rs.getBigDecimal("Fine")
                    );
                }
            }
        }
        return null;
    }   

    @Override
    public ReturnBookEntity findByMemberId(int memberId) throws SQLException {
        String sql = "SELECT * FROM Bookreturn WHERE MemberID = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, memberId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    ReturnBookEntity returnBookEntity = new ReturnBookEntity();
                    returnBookEntity.setReturnId(rs.getInt("ReturnID"));
                    // Set other properties of returnBookEntity as needed
                    return returnBookEntity;
                } else {
                    return null;
                }
            }
        }
    }
 }


