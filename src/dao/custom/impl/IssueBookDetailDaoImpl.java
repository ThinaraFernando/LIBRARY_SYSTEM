package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


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
        String sql = "INSERT INTO issuedetail (IssueDetailID, IssueID, BookID) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, issueDetailDTO.getIssueDetailId());
            stmt.setString(2, issueDetailDTO.getIssueId());
            stmt.setString(3, issueDetailDTO.getBookId());
            return stmt.executeUpdate() > 0;
        }    }

    @Override
    public IssueDetailDto getIssueDetailById(String issueDetailId) throws SQLException {
        String sql = "SELECT * FROM issuedetail WHERE IssueDetailID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, issueDetailId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new IssueDetailDto(
                    rs.getString("IssueDetailID"),
                    rs.getString("IssueID"),
                    rs.getString("BookID")
                );
            }
        }
        return null;
    }   

    @Override
    public List<IssueDetailDto> getAllIssueDetails() throws SQLException {
        String sql = "SELECT * FROM issuedetail";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<IssueDetailDto> issueDetails = new ArrayList<>();
            while (resultSet.next()) {
                issueDetails.add(new IssueDetailDto(
                        resultSet.getString("IssueDetailID"),
                        resultSet.getString("IssueID"),
                        resultSet.getString("BookID")
                ));
            }
            return issueDetails;
        }
    }
    }
 


