package com.homework.appContext;

import com.homework.DAOLayer.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Listener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        //initialize DB connection
        String dbURL = servletContext.getInitParameter("dbURL");
        String user = servletContext.getInitParameter("dbUser");
        String password = servletContext.getInitParameter("dbPassword");

        try {
            DBConnectionManager connectionManager = new DBConnectionManager(dbURL, user, password);
            servletContext.setAttribute("DBConnection", connectionManager.getConnection());

            System.out.println("Database connection initialized successfully");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection connection = (Connection)servletContextEvent.getServletContext().getAttribute("DBConnection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
