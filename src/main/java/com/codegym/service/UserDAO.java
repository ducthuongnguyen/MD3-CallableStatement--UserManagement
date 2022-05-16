package com.codegym.service;

import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insert(User user) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name,email,country) values (?,?,?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public User select(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id,name,email,country from users where id =?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from users")) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
        }
        return users;
    }

    @Override
    public boolean delete(int id) throws SQLDataException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=?")) {
            preparedStatement.setInt(1, id);
            rowDeleted= preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
        }
        return rowDeleted;
    }

    @Override
    public boolean update(User user) throws SQLDataException {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update users set name=?,email=?,country=? where id=?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());
            rowUpdated= preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
        }
        return rowUpdated;
    }
}

