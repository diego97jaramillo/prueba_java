package com.rentamachine.persistance.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    final static String URL = "jdbc:mysql://urqhihwsar2t7jxt:WHLFCIN5NJxEFABOQvqW@biei9nveuu1l3cmigfgr-mysql.services.clever-cloud.com:3306/biei9nveuu1l3cmigfgr";
    final static String USER = "urqhihwsar2t7jxt";
    final static String PASSWORD = "WHLFCIN5NJxEFABOQvqW";


    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

