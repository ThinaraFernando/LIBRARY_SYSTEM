package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.QueryDao;
import db.DBConnection;

public class QueryDaoImpl implements QueryDao {

    private Connection connection;

    public QueryDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTotalBooks() throws Exception {
        String query = "SELECT COUNT(*) FROM book";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public int getTotalIssuedBooks() throws Exception {
        String query = "SELECT COUNT(*) FROM issuedetail";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public int getTotalMembers() throws Exception {
        String query = "SELECT COUNT(*) FROM member";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public int getTotalReturnBooks() throws Exception {
        String query = "SELECT COUNT(*) FROM bookreturn";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

}
