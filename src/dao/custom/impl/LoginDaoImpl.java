package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.custom.LoginDao;
import db.DBConnection;
import entity.LoginEntity;

public class LoginDaoImpl implements LoginDao {

    private Connection connection;

    public LoginDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);  // Ensure manual commit control
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(LoginEntity entity) throws Exception {
        String query = "INSERT INTO user (userName, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, entity.getUserName());
            preparedStatement.setString(2, entity.getPassword());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new Exception("Error while adding user", e);
        }
      }

    @Override
    public boolean delete(String id) throws Exception {
        return false;   
     }

    @Override
    public boolean update(LoginEntity entity) throws Exception {
        return false;   
     }

    @Override
    public LoginEntity find(String id) throws Exception {
        String query = "SELECT * FROM user WHERE userName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new LoginEntity(resultSet.getString("userName"), resultSet.getString("password"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while finding user", e);
        }
    }

    @Override
    public List<LoginEntity> findAll() throws Exception {
        return null;
    }
     

     
}
