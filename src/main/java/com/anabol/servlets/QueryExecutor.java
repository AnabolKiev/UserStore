package com.anabol.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {
    public static ResultSet execute(String query) throws SQLException {
        String url = "jdbc:sqlite:test.db"; // db parameters
        Connection conn = DriverManager.getConnection(url);
        return conn.createStatement().executeQuery(query);

    }
}
