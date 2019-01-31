package com.anabol.servlets;

import com.anabol.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();// = createPageVariablesMap(request);

        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\DevTools\\test.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT id, firstName, lastName, salary, dateOfBirth FROM 'user'");

            System.out.println("Connection to SQLite has been established.");
            System.out.println(resultSet.getMetaData().getColumnName(2));
            pageVariables.put("message", resultSet.getMetaData().getColumnName(2));
            response.getWriter().println(PageGenerator.instance().getPage("users.html", pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }



    }
}
