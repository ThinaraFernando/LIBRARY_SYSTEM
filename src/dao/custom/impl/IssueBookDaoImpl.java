package dao.custom.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.custom.IssueBookDao;
import db.DBConnection;
import dto.IssueDto;

public class IssueBookDaoImpl implements IssueBookDao {

    private Connection connection;

     public IssueBookDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createIssue(IssueDto issueDTO) throws SQLException {
        String sql = "INSERT INTO issue (issue_id, member_id, issue_date, due_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, issueDTO.getIssueId());
            stmt.setString(2, issueDTO.getMemberId());
            stmt.setDate(3, Date.valueOf(issueDTO.getIssueDate()));
            stmt.setDate(4, Date.valueOf(issueDTO.getDueDate()));
            return stmt.executeUpdate() > 0;
        }    }

    @Override
    public IssueDto getIssueById(String issueId) throws SQLException {
        String sql = "SELECT * FROM issue WHERE issue_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, issueId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new IssueDto(
                    rs.getString("issueid"),
                    rs.getString("memberid"),
                    rs.getDate("issuedate").toLocalDate(),
                    rs.getDate("duedate").toLocalDate()
                );
            }
        }
        return null;    }

    @Override
    public List<IssueDto> getAllIssues() throws SQLException {
List<IssueDto> issues = new ArrayList<>();
        String sql = "SELECT * FROM issue";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                issues.add(new IssueDto(
                    rs.getString("issue_id"),
                    rs.getString("member_id"),
                    rs.getDate("issue_date").toLocalDate(),
                    rs.getDate("due_date").toLocalDate()
                ));
            }
        }
        return issues;    }

    @Override
    public String getMemberNameById(String memberId) throws SQLException {
        String sql = "SELECT name FROM member WHERE member_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, memberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        }
        return null;
    }   
 }


