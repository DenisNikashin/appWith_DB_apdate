package com.homework.DAOLayer;

import com.homework.userAccount.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InteractionWithDB implements DAO {

    private final Connection connection;

    public InteractionWithDB(final Connection connection) {
        this.connection = connection;
    }

    public  void addNewUserDataInDB(User userAccount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLUserAccount.INSERT.QUERY)) {
            preparedStatement.setString(1, userAccount.getName());
            preparedStatement.setString(2, userAccount.getEmail());
            preparedStatement.setString(3, userAccount.getCountry());
            preparedStatement.setString(4, userAccount.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User checkingTheExistenceOfTheUser(String email, String password) {
        User userAccount = new User();
        userAccount.setEmail(email);
        userAccount.setPassword(password);

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLUserAccount.SELECT.QUERY)) {
            preparedStatement.setString(1, userAccount.getEmail());
            preparedStatement.setString(2, userAccount.getPassword());

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                userAccount = new User(resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("country"),
                                resultSet.getInt("id"));
            } else {
                userAccount = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccount;
    }

    enum SQLUserAccount {
        INSERT("INSERT INTO user_account(name,email,country, password) VALUES (?,?,?,?)"),
        SELECT("SELECT id, name, email, country FROM user_account WHERE email=? AND password=? LIMIT 1");

        String QUERY;
        SQLUserAccount(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}






