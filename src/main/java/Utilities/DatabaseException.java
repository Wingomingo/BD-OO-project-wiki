package Utilities;

import java.sql.SQLException;

public class DatabaseException {
    public static void handleSQLException(SQLException e) {
        String sqlState = e.getSQLState();
        String errorMessage = e.getMessage();

        switch (sqlState) {
            case "45000":
                System.out.println("Errore dal database: " + errorMessage);
                break;
            case "23000":
                System.out.println("Errore di integrità dei dati: " + errorMessage);
                break;
            default:
                System.out.println("Errore SQL generico: " + errorMessage);
        }
    }
}

