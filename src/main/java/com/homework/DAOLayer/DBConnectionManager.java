package com.homework.DAOLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager implements AutoCloseable {
    private Connection connection;

    public DBConnectionManager(String dbURL, String user, String password)
                                                    throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public Connection getConnection(){
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
