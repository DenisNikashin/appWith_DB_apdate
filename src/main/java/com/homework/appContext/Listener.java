package com.homework.appContext;

import com.homework.DAOLayer.DBConnectionManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Listener implements ServletContextListener {

    private Logger logger = Logger.getLogger(Listener.class);

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
            logger.info("Database connection initialized successfully");
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("that something was wrong when the connection to data base was initialized ");
            e.printStackTrace();
        }

    //initialize log4j
        String log4jConfig = servletContext.getInitParameter("log4j-config");
    	if(log4jConfig == null){
    		System.err.println("No log4j-config init param, initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
    	}else {
			String webAppPath = servletContext.getRealPath("/");
			String log4jProp = webAppPath + log4jConfig;
			File log4jConfigFile = new File(log4jProp);
			if (log4jConfigFile.exists()) {
				System.out.println("Initializing log4j with: " + log4jProp);
				DOMConfigurator.configure(log4jProp);
			} else {
				System.err.println(log4jProp + " file not found, initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
    	System.out.println("log4j configured properly");
    }

    public void contextDestroyed (ServletContextEvent servletContextEvent){
        Connection connection = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
        try {
            connection.close();
            logger.info("connection closed gracefully :)");
        } catch (SQLException e) {
            logger.error("something wrong happened at closed the connection");
            e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    logger.info("the connection is closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}
