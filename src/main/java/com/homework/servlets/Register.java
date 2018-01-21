package com.homework.servlets;

import com.homework.DAOLayer.InteractionWithDB;
import com.homework.userAccount.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class Register extends HttpServlet {

    private Logger logger = Logger.getLogger(Register.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String errorMsg = null;
        User userAccount = new User(name, email, country, password);

        if (email == null || email.equals("")) {
            errorMsg = "Email ID can't be null or empty.";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty.";
        }
        if (name == null || name.equals("")) {
            errorMsg = "Name can't be null or empty.";
        }
        if (country == null || country.equals("")) {
            errorMsg = "Country can't be null or empty.";
        }

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
            PrintWriter out= response.getWriter();
            out.println("<p align=\"center\"><font size=\"20\" color=red>"+errorMsg+"</font></p>");
            rd.include(request, response);
        } else {
            try (Connection dbConnection = (Connection) getServletContext().getAttribute("DBConnection")) {

                new InteractionWithDB(dbConnection).addNewUserDataInDB(userAccount);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out= response.getWriter();
                out.println("<p align=\"center\"><font size=\"15\" color=green>" +
                        "Registration successful, please login below." +
                        "</font></p>");
                requestDispatcher.include(request, response);
            } catch (SQLException e) {
                logger.error("there is no connection in RegisterServlet" );
            }
        }
    }
}
