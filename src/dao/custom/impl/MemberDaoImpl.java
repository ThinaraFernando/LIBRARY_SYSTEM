package dao.custom.impl;

import dao.custom.MemberDao;
import db.DBConnection;
import entity.MemberEntity;

import java.sql.*;
import java.util.ArrayList;

public class MemberDaoImpl implements MemberDao {
    private Connection connection;

    public MemberDaoImpl() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(MemberEntity entity) throws Exception {
        String query = "INSERT INTO member (MemberID, Name, Address, Phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, entity.getMemberId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getAddress());
            stmt.setString(4, entity.getPhone());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(MemberEntity entity) throws Exception {
        String query = "UPDATE member SET Name = ?, Address = ?, Phone = ? WHERE MemberID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getAddress());
            stmt.setString(3, entity.getPhone());
            stmt.setInt(4, entity.getMemberId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(String code) throws Exception {
        String query = "DELETE FROM member WHERE MemberID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(code));
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public MemberEntity getMemberEntity(String code) throws Exception {
        String query = "SELECT * FROM member WHERE MemberID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(code));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MemberEntity(
                    rs.getInt("MemberID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
                );
            }
        }
        return null;
    }

    @Override
    public ArrayList<MemberEntity> getAll() throws Exception {
        ArrayList<MemberEntity> members = new ArrayList<>();
        String query = "SELECT * FROM member";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                members.add(new MemberEntity(
                    rs.getInt("MemberID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
                ));
            }
        }
        return members;
    }
}
