package com.human.userchat.impl;


import com.human.userchat.model.User;
import com.human.userchat.util.DataSource;
import com.human.userchat.util.DatabaseConnectionFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepoSql implements UserRepo {
    @Override
    public boolean exits(String email) throws SQLException {
        String query = "select count(*) from users_chat where email = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }


    private static User toUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setImageURL(resultSet.getString("image_url"));
        return user;
    }

    @Override
    public User insert(User user) throws SQLException {
        String query = "insert into users_chat(name,surname,email,password,image_url) values (?,?,?,?,?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getImageURL());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            }
        }
        return   user;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws SQLException {
        String query = "select * from users_chat where email = ? and password = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if(resultSet.next()){
                   return Optional.of(UserRepoSql.toUser(resultSet));
               }
            }
        }
        return  Optional.empty();
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new LinkedList<>();
        String query = "SELECT * FROM users_chat;";
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add(toUser(resultSet));
            }
        }
        return users;
    }
    }
