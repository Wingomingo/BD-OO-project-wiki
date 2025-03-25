package Dao;

import Modello.Notifica;
import Utilities.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificaDAO {
    private Connection conn;

    public NotificaDAO() {
        try {
            conn = DBconnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione al database in NotificaDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Notifica> getAllNotifiche() {
        List<Notifica> notifiche = new ArrayList<>();
        String query = "SELECT * FROM notifiche ORDER BY Data DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Notifica notifica = new Notifica(
                        rs.getInt("ID_notifica"),
                        rs.getTimestamp("Data"),
                        rs.getBoolean("Letta"),
                        rs.getInt("ID_modificatore"),
                        rs.getInt("ID_autore"),
                        rs.getInt("ID_creatore_pagina")
                );
                notifiche.add(notifica);
            }
            System.out.println("Trovate " + notifiche.size() + " notifiche totali.");
        } catch (SQLException e) {
            System.out.println("Errore nel recupero delle notifiche: " + e.getMessage());
            e.printStackTrace();
        }
        return notifiche;
    }

    public boolean markNotificaAsLetta(int idNotifica, String username, String password) {
        try {
            String query = "SELECT * FROM notifiche WHERE ID_notifica = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idNotifica);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Notifica non trovata.");
                return false;
            }
            int idCreatore = rs.getInt("ID_creatore_pagina");

            // verifica per il creatore della pagina
            String queryUtente = "SELECT * FROM utente WHERE ID_utente = ?";
            PreparedStatement stmtUtente = conn.prepareStatement(queryUtente);
            stmtUtente.setInt(1, idCreatore);
            ResultSet rsUtente = stmtUtente.executeQuery();
            if (rsUtente.next()) {
                String dbUsername = rsUtente.getString("username");
                String dbPassword = rsUtente.getString("password");
                if (!dbUsername.equals(username) || !dbPassword.equals(password)) {
                    System.out.println("Credenziali non corrispondono al creatore della pagina.");
                    return false;
                }
            } else {
                System.out.println("Creatore della pagina non trovato.");
                return false;
            }

            String updateQuery = "UPDATE notifiche SET Letta = TRUE WHERE ID_notifica = ?";
            PreparedStatement stmtUpdate = conn.prepareStatement(updateQuery);
            stmtUpdate.setInt(1, idNotifica);
            int rowsAffected = stmtUpdate.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Notifica segnata come letta.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento della notifica: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}


