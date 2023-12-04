package src;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public DBConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String var0 = System.getenv("DB_URL");
                String var1 = System.getenv("DB_USER");
                String var2 = System.getenv("DB_PASSWORD");
                connection = DriverManager.getConnection(var0, var1, var2);
                System.out.println("Connection Succes");
            } catch (SQLException var3) {
                throw new RuntimeException("Erreur lors de la connexion à la base de données", var3);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
