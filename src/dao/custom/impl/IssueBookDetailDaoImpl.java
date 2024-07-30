package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.custom.IssueBookDetailDao;
import db.DBConnection;
import dto.IssueDetailDto;

public class IssueBookDetailDaoImpl implements IssueBookDetailDao {

    private Connection connection;

      public IssueBookDetailDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createIssueDetail(IssueDetailDto issueDetailDTO) throws SQLException {
        String sql = "INSERT INTO issue_detail (issue_detail_id, issue_id, book_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, issueDetailDTO.getIssueDetailId());
            stmt.setString(2, issueDetailDTO.getIssueId());
            stmt.setString(3, issueDetailDTO.getBookId());
            return stmt.executeUpdate() > 0;
        }    }

    @Override
    public IssueDetailDto getIssueDetailById(String issueDetailId) throws SQLException {
        String sql = "SELECT * FROM issue_detail WHERE issue_detail_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, issueDetailId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new IssueDetailDto(
                    rs.getString("issue_detail_id"),
                    rs.getString("issue_id"),
                    rs.getString("book_id")
                );
            }
        }
        return null;
    }   
 }


