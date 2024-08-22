package com.restaurante.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private static final String DB_URL = "jdbc:h2:file:" 
	        + System.getProperty("user.dir").replace("\\", "/") 
	        + "/restauranteDoZe/src/main/java/resources/restauranteZe";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private DatabaseConnector() { }

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}