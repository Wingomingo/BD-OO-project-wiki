package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/wiki1";
    private static final String USER = "root";
    private static final String PASSWORD = "Wingomingo03";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connessione al database riuscita.");
            } catch (SQLException e) {
                System.out.println("Errore di connessione al database: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }
}


