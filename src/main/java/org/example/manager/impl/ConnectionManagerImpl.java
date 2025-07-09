package org.example.manager.impl;

import org.example.manager.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
    private String name="name";
    private String url="jdbc:postgresql://localhost:5432/order_service";
    private String password="password";
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,name,password);

    }
}
