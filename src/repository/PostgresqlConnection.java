package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection{
    private static Connection connection;
    final public static String DB_URL = System.getenv("DB_URL");
    final public static String DB_PASSWORD = System.getenv("DB_PASSWORD");
    final public static String DB_USERNAME = System.getenv("DB_USERNAME");

    public static Connection getConnection(){
        if(connection != null)
            return connection;
        try{
            connection = DriverManager.getConnection(
                DB_URL,
                DB_USERNAME,
                DB_PASSWORD
            );
            return connection;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            throw new RuntimeException("Connection failed");
        }
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}