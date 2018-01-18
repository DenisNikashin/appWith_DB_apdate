package com.homework.servlets;

import com.homework.DAOLayer.InteractionWithDB;
import com.homework.userAccount.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                                        throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMsg = null;

        if (email == null || email.equals("")) {
            errorMsg ="User Email can't be null or empty";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty";
        }

        if (errorMsg != null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<p align=\"center\"><font size=\"20\" color=red>"+errorMsg+"</font></p>");
            requestDispatcher.include(request, response);
        } else {
            Connection dbConnection = (Connection) getServletContext().getAttribute("DBConnection");

            User currentUser = new InteractionWithDB(dbConnection).checkingTheExistenceOfTheUser(email, password);

            if (currentUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("User", currentUser);
                response.sendRedirect("home.jsp");
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<p align=\"center\"><font size=\"20\" color=red>" +
                        "No user found with given email id, please register first." +
                        "</font></p>");
                requestDispatcher.include(request, response);
            }
        }
    }
}





