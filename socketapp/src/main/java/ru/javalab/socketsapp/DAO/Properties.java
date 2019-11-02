package ru.javalab.socketsapp.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Properties {
    private static String url = "jdbc:postgresql://localhost:5432/test_db";
    private static String username = "postgres";
    private static String password = "dthbr";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

}
