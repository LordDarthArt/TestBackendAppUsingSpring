package com.example.testBackend.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public java.sql.Connection connection;

    public java.sql.Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void setConnection(java.sql.Connection dataSource) {
        this.connection = dataSource;
    }
}
