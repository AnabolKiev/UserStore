package com.anabol.servlets;

import com.anabol.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();// = createPageVariablesMap(request);

        ResultSet resultSet = null;
        try {
            resultSet = QueryExecutor.execute("SELECT id, firstName, lastName, salary, dateOfBirth FROM 'user'");
            List<User> users = ResultParser.parse(resultSet);
            pageVariables.put("users", users);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("We got SQLException", e);
        }

        response.getWriter().println(PageGenerator.instance().getPage("users.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);


    }
}
